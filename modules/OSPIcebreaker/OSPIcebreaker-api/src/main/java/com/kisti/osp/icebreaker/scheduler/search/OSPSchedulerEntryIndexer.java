package com.kisti.osp.icebreaker.scheduler.search;

import java.util.Locale;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.kisti.osp.icebreaker.model.OSPSchedulerEntry;
import com.kisti.osp.icebreaker.permissions.SchedulerPermission;
import com.kisti.osp.icebreaker.service.OSPSchedulerEntryLocalService;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.BaseIndexer;
import com.liferay.portal.kernel.search.BooleanQuery;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.IndexWriterHelper;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.Summary;
import com.liferay.portal.kernel.search.filter.BooleanFilter;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.util.PortalUtil;

@Component(
		immediate = true,
		service = Indexer.class
)
public class OSPSchedulerEntryIndexer extends BaseIndexer<OSPSchedulerEntry> {
	private static final Log _log = LogFactoryUtil.getLog(OSPSchedulerEntryIndexer.class);
	public static final String CLASS_NAME = OSPSchedulerEntry.class.getName();

	@Reference
	protected IndexWriterHelper indexWriterHelper;
	
	@Reference
	private OSPSchedulerEntryLocalService _OSPSchedulerEntryLocalService;

	public OSPSchedulerEntryIndexer() {
		System.out.println("OSPSchedulerEntryIndexer ---------------------");
		super.setDefaultSelectedFieldNames(
				 Field.ASSET_CATEGORY_IDS,
				 Field.ASSET_TAG_NAMES, 
				 Field.COMPANY_ID, 
				 Field.NAME,
				 Field.VERSION,
				 Field.ENTRY_CLASS_NAME, 
				 Field.ENTRY_CLASS_PK, 
				 Field.GROUP_ID,
				 Field.CREATE_DATE,
				 Field.MODIFIED_DATE,
				 Field.DESCRIPTION, 
				 Field.UID
		);
		 
		super.setDefaultSelectedLocalizedFieldNames(
				 Field.DESCRIPTION
		);
		
		super.setFilterSearch(true);
		super.setPermissionAware(true);
	}
	
	@Override
	public String getClassName() {
		System.out.println("getClassName(): "+CLASS_NAME);
		return CLASS_NAME;
	}

	@Override
	public boolean hasPermission(
			PermissionChecker permissionChecker, String entryClassName,
			long entryClassPK, String actionId) throws Exception {
		System.out.println("hasPermission(): "+entryClassPK);
		System.out.println("actionId: "+actionId);
		return SchedulerPermission.contains(permissionChecker, entryClassPK, ActionKeys.VIEW);
	}
	
	@Override
	public void postProcessContextBooleanFilter(
			BooleanFilter contextBooleanFilter, SearchContext searchContext)
					throws Exception {
		System.out.println("postProcessContextBooleanFilter(): ");
		super.addStatus(contextBooleanFilter, searchContext);
	}
	
	@Override
	public void postProcessSearchQuery(
			BooleanQuery searchQuery, 
			BooleanFilter fullQueryBooleanFilter,
			SearchContext searchContext)
					throws Exception {

		super.addSearchLocalizedTerm(searchQuery, searchContext, Field.NAME, false);
		super.addSearchLocalizedTerm(searchQuery, searchContext, Field.VERSION, false);
		super.addSearchLocalizedTerm(searchQuery, searchContext, Field.DESCRIPTION, false);
	}
	
	@Override
	protected void doDelete(OSPSchedulerEntry object) throws Exception {
		System.out.println("doDelete(): "+object.getPrimaryKey());
		super.deleteDocument(object.getCompanyId(), object.getPrimaryKey());
	}
	
	@Override
	protected Document doGetDocument(OSPSchedulerEntry scheduler) throws Exception {
		System.out.println("doGetDocument(): ");
		Document document = getBaseModelDocument(CLASS_NAME, scheduler);

		document.addNumber(Field.COMPANY_ID, scheduler.getCompanyId());
		document.addNumber(Field.GROUP_ID, scheduler.getGroupId());
		document.addNumber(Field.USER_ID, scheduler.getUserId());
		document.addNumber(Field.ENTRY_CLASS_PK, scheduler.getSchedulerEntryId());
		document.addText(Field.NAME, scheduler.getName());
		document.addText(Field.VERSION, scheduler.getVersion());
		document.addText(Field.ENTRY_CLASS_NAME, OSPSchedulerEntry.class.getName());
		document.addDate(Field.CREATE_DATE, scheduler.getCreateDate());
		document.addDate(Field.MODIFIED_DATE, scheduler.getModifiedDate());

		Locale defaultLocale =
				PortalUtil.getSiteDefaultLocale(scheduler.getGroupId());
		String localizedField = LocalizationUtil.getLocalizedName(
				Field.DESCRIPTION, defaultLocale.toString());

		document.addText(localizedField, scheduler.getDescription());
		
		return document;
	}
	
	@Override
	protected Summary doGetSummary(Document document, Locale locale, String snippet, PortletRequest portletRequest,
			PortletResponse portletResponse) throws Exception {
		System.out.println("doGetSummary(): ");
		Summary summary = createSummary(document);
		
		summary.setMaxContentLength(200);
		
		return summary;
	}
	
	@Override
	protected void doReindex(OSPSchedulerEntry scheduler) throws Exception {
		System.out.println("doReindex(object): ");
		Document document = getDocument(scheduler);
		
		indexWriterHelper.updateDocument(
				super.getSearchEngineId(), 
				scheduler.getCompanyId(), 
				document,
				super.isCommitImmediately());
	}
	
	@Override
	protected void doReindex(String className, long classPK) throws Exception {
		System.out.println("doReindex(className): "+classPK);
		OSPSchedulerEntry entry = this._OSPSchedulerEntryLocalService.getOSPSchedulerEntry(classPK);
		
		this.doReindex(entry);
	}
	
	@Override
	protected void doReindex(String[] ids) throws Exception {
		long companyId = GetterUtil.getLong(ids[0]);
		System.out.println("doReindex(companyId): "+companyId);
		this.reindexSchedulers(companyId);
	}
	
	
	@Override
	public boolean isVisible(long classPK, int status) throws Exception {
		System.out.println("isVisible(): "+classPK);
		return true;
	}
	
	protected void reindexSchedulers(long companyId) throws PortalException {
		final IndexableActionableDynamicQuery indexableActionableDynamicQuery =
				this._OSPSchedulerEntryLocalService.getIndexableActionableDynamicQuery();
		
		indexableActionableDynamicQuery.setCompanyId(companyId);
		indexableActionableDynamicQuery.setPerformActionMethod(
				new ActionableDynamicQuery.PerformActionMethod<OSPSchedulerEntry>() {
		
					@Override
					public void performAction(OSPSchedulerEntry entry) {
						System.out.println("performAction(): "+ entry.getName());
						try {
							Document document = getDocument(entry);
		
							indexableActionableDynamicQuery.addDocuments(document);
						}
						catch (PortalException pe) {
							if (_log.isWarnEnabled()) {
								_log.warn(
										"Unable to index OSPSchedulerEntry " +
												entry.getSchedulerEntryId()+1,
												pe);
							}
						}
					}
		
				}
		);
		
		indexableActionableDynamicQuery.setSearchEngineId(super.getSearchEngineId());
		
		indexableActionableDynamicQuery.performActions();
	}

	@Reference(unbind = "-")
	protected void setOSPSchedulerEntryLocalService(
			OSPSchedulerEntryLocalService ospSchedulerEntryLocalService) {
		this._OSPSchedulerEntryLocalService = ospSchedulerEntryLocalService;
	}
}
