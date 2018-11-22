/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.osp.spyglass.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.expando.kernel.model.ExpandoBridge;

import com.liferay.exportimport.kernel.lar.StagedModelType;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * This class is a wrapper for {@link ScienceApp}.
 * </p>
 *
 * @author Jerry H. Seo
 * @see ScienceApp
 * @generated
 */
@ProviderType
public class ScienceAppWrapper implements ScienceApp, ModelWrapper<ScienceApp> {
	public ScienceAppWrapper(ScienceApp scienceApp) {
		_scienceApp = scienceApp;
	}

	@Override
	public Class<?> getModelClass() {
		return ScienceApp.class;
	}

	@Override
	public String getModelClassName() {
		return ScienceApp.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("scienceAppId", getScienceAppId());
		attributes.put("companyId", getCompanyId());
		attributes.put("groupId", getGroupId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("name", getName());
		attributes.put("version", getVersion());
		attributes.put("title", getTitle());
		attributes.put("descriptionId", getDescriptionId());
		attributes.put("previousVersionId", getPreviousVersionId());
		attributes.put("iconId", getIconId());
		attributes.put("manualId", getManualId());
		attributes.put("exeFileName", getExeFileName());
		attributes.put("appType", getAppType());
		attributes.put("runType", getRunType());
		attributes.put("authorId", getAuthorId());
		attributes.put("registerStage", getRegisterStage());
		attributes.put("status", getStatus());
		attributes.put("openLevel", getOpenLevel());
		attributes.put("license", getLicense());
		attributes.put("srcFileName", getSrcFileName());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long scienceAppId = (Long)attributes.get("scienceAppId");

		if (scienceAppId != null) {
			setScienceAppId(scienceAppId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		Long groupId = (Long)attributes.get("groupId");

		if (groupId != null) {
			setGroupId(groupId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		String userName = (String)attributes.get("userName");

		if (userName != null) {
			setUserName(userName);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}

		String version = (String)attributes.get("version");

		if (version != null) {
			setVersion(version);
		}

		String title = (String)attributes.get("title");

		if (title != null) {
			setTitle(title);
		}

		String descriptionId = (String)attributes.get("descriptionId");

		if (descriptionId != null) {
			setDescriptionId(descriptionId);
		}

		Long previousVersionId = (Long)attributes.get("previousVersionId");

		if (previousVersionId != null) {
			setPreviousVersionId(previousVersionId);
		}

		String iconId = (String)attributes.get("iconId");

		if (iconId != null) {
			setIconId(iconId);
		}

		String manualId = (String)attributes.get("manualId");

		if (manualId != null) {
			setManualId(manualId);
		}

		String exeFileName = (String)attributes.get("exeFileName");

		if (exeFileName != null) {
			setExeFileName(exeFileName);
		}

		String appType = (String)attributes.get("appType");

		if (appType != null) {
			setAppType(appType);
		}

		String runType = (String)attributes.get("runType");

		if (runType != null) {
			setRunType(runType);
		}

		Long authorId = (Long)attributes.get("authorId");

		if (authorId != null) {
			setAuthorId(authorId);
		}

		String registerStage = (String)attributes.get("registerStage");

		if (registerStage != null) {
			setRegisterStage(registerStage);
		}

		Integer status = (Integer)attributes.get("status");

		if (status != null) {
			setStatus(status);
		}

		String openLevel = (String)attributes.get("openLevel");

		if (openLevel != null) {
			setOpenLevel(openLevel);
		}

		String license = (String)attributes.get("license");

		if (license != null) {
			setLicense(license);
		}

		String srcFileName = (String)attributes.get("srcFileName");

		if (srcFileName != null) {
			setSrcFileName(srcFileName);
		}
	}

	@Override
	public Object clone() {
		return new ScienceAppWrapper((ScienceApp)_scienceApp.clone());
	}

	@Override
	public int compareTo(ScienceApp scienceApp) {
		return _scienceApp.compareTo(scienceApp);
	}

	/**
	* Returns the app type of this science app.
	*
	* @return the app type of this science app
	*/
	@Override
	public String getAppType() {
		return _scienceApp.getAppType();
	}

	/**
	* Returns the author ID of this science app.
	*
	* @return the author ID of this science app
	*/
	@Override
	public long getAuthorId() {
		return _scienceApp.getAuthorId();
	}

	@Override
	public String[] getAvailableLanguageIds() {
		return _scienceApp.getAvailableLanguageIds();
	}

	/**
	* Returns the company ID of this science app.
	*
	* @return the company ID of this science app
	*/
	@Override
	public long getCompanyId() {
		return _scienceApp.getCompanyId();
	}

	/**
	* Returns the create date of this science app.
	*
	* @return the create date of this science app
	*/
	@Override
	public Date getCreateDate() {
		return _scienceApp.getCreateDate();
	}

	@Override
	public String getDefaultLanguageId() {
		return _scienceApp.getDefaultLanguageId();
	}

	/**
	* Returns the description ID of this science app.
	*
	* @return the description ID of this science app
	*/
	@Override
	public String getDescriptionId() {
		return _scienceApp.getDescriptionId();
	}

	/**
	* Returns the localized description ID of this science app in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param locale the locale of the language
	* @return the localized description ID of this science app
	*/
	@Override
	public String getDescriptionId(java.util.Locale locale) {
		return _scienceApp.getDescriptionId(locale);
	}

	/**
	* Returns the localized description ID of this science app in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param locale the local of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized description ID of this science app. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	*/
	@Override
	public String getDescriptionId(java.util.Locale locale, boolean useDefault) {
		return _scienceApp.getDescriptionId(locale, useDefault);
	}

	/**
	* Returns the localized description ID of this science app in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @return the localized description ID of this science app
	*/
	@Override
	public String getDescriptionId(String languageId) {
		return _scienceApp.getDescriptionId(languageId);
	}

	/**
	* Returns the localized description ID of this science app in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized description ID of this science app
	*/
	@Override
	public String getDescriptionId(String languageId, boolean useDefault) {
		return _scienceApp.getDescriptionId(languageId, useDefault);
	}

	@Override
	public String getDescriptionIdCurrentLanguageId() {
		return _scienceApp.getDescriptionIdCurrentLanguageId();
	}

	@Override
	public String getDescriptionIdCurrentValue() {
		return _scienceApp.getDescriptionIdCurrentValue();
	}

	/**
	* Returns a map of the locales and localized description IDs of this science app.
	*
	* @return the locales and localized description IDs of this science app
	*/
	@Override
	public Map<java.util.Locale, String> getDescriptionIdMap() {
		return _scienceApp.getDescriptionIdMap();
	}

	/**
	* Returns the exe file name of this science app.
	*
	* @return the exe file name of this science app
	*/
	@Override
	public String getExeFileName() {
		return _scienceApp.getExeFileName();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _scienceApp.getExpandoBridge();
	}

	/**
	* Returns the group ID of this science app.
	*
	* @return the group ID of this science app
	*/
	@Override
	public long getGroupId() {
		return _scienceApp.getGroupId();
	}

	/**
	* Returns the icon ID of this science app.
	*
	* @return the icon ID of this science app
	*/
	@Override
	public String getIconId() {
		return _scienceApp.getIconId();
	}

	/**
	* Returns the localized icon ID of this science app in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param locale the locale of the language
	* @return the localized icon ID of this science app
	*/
	@Override
	public String getIconId(java.util.Locale locale) {
		return _scienceApp.getIconId(locale);
	}

	/**
	* Returns the localized icon ID of this science app in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param locale the local of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized icon ID of this science app. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	*/
	@Override
	public String getIconId(java.util.Locale locale, boolean useDefault) {
		return _scienceApp.getIconId(locale, useDefault);
	}

	/**
	* Returns the localized icon ID of this science app in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @return the localized icon ID of this science app
	*/
	@Override
	public String getIconId(String languageId) {
		return _scienceApp.getIconId(languageId);
	}

	/**
	* Returns the localized icon ID of this science app in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized icon ID of this science app
	*/
	@Override
	public String getIconId(String languageId, boolean useDefault) {
		return _scienceApp.getIconId(languageId, useDefault);
	}

	@Override
	public String getIconIdCurrentLanguageId() {
		return _scienceApp.getIconIdCurrentLanguageId();
	}

	@Override
	public String getIconIdCurrentValue() {
		return _scienceApp.getIconIdCurrentValue();
	}

	/**
	* Returns a map of the locales and localized icon IDs of this science app.
	*
	* @return the locales and localized icon IDs of this science app
	*/
	@Override
	public Map<java.util.Locale, String> getIconIdMap() {
		return _scienceApp.getIconIdMap();
	}

	/**
	* Returns the license of this science app.
	*
	* @return the license of this science app
	*/
	@Override
	public String getLicense() {
		return _scienceApp.getLicense();
	}

	/**
	* Returns the manual ID of this science app.
	*
	* @return the manual ID of this science app
	*/
	@Override
	public String getManualId() {
		return _scienceApp.getManualId();
	}

	/**
	* Returns the localized manual ID of this science app in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param locale the locale of the language
	* @return the localized manual ID of this science app
	*/
	@Override
	public String getManualId(java.util.Locale locale) {
		return _scienceApp.getManualId(locale);
	}

	/**
	* Returns the localized manual ID of this science app in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param locale the local of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized manual ID of this science app. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	*/
	@Override
	public String getManualId(java.util.Locale locale, boolean useDefault) {
		return _scienceApp.getManualId(locale, useDefault);
	}

	/**
	* Returns the localized manual ID of this science app in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @return the localized manual ID of this science app
	*/
	@Override
	public String getManualId(String languageId) {
		return _scienceApp.getManualId(languageId);
	}

	/**
	* Returns the localized manual ID of this science app in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized manual ID of this science app
	*/
	@Override
	public String getManualId(String languageId, boolean useDefault) {
		return _scienceApp.getManualId(languageId, useDefault);
	}

	@Override
	public String getManualIdCurrentLanguageId() {
		return _scienceApp.getManualIdCurrentLanguageId();
	}

	@Override
	public String getManualIdCurrentValue() {
		return _scienceApp.getManualIdCurrentValue();
	}

	/**
	* Returns a map of the locales and localized manual IDs of this science app.
	*
	* @return the locales and localized manual IDs of this science app
	*/
	@Override
	public Map<java.util.Locale, String> getManualIdMap() {
		return _scienceApp.getManualIdMap();
	}

	/**
	* Returns the modified date of this science app.
	*
	* @return the modified date of this science app
	*/
	@Override
	public Date getModifiedDate() {
		return _scienceApp.getModifiedDate();
	}

	/**
	* Returns the name of this science app.
	*
	* @return the name of this science app
	*/
	@Override
	public String getName() {
		return _scienceApp.getName();
	}

	/**
	* Returns the open level of this science app.
	*
	* @return the open level of this science app
	*/
	@Override
	public String getOpenLevel() {
		return _scienceApp.getOpenLevel();
	}

	/**
	* Returns the previous version ID of this science app.
	*
	* @return the previous version ID of this science app
	*/
	@Override
	public long getPreviousVersionId() {
		return _scienceApp.getPreviousVersionId();
	}

	/**
	* Returns the primary key of this science app.
	*
	* @return the primary key of this science app
	*/
	@Override
	public long getPrimaryKey() {
		return _scienceApp.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _scienceApp.getPrimaryKeyObj();
	}

	/**
	* Returns the register stage of this science app.
	*
	* @return the register stage of this science app
	*/
	@Override
	public String getRegisterStage() {
		return _scienceApp.getRegisterStage();
	}

	/**
	* Returns the run type of this science app.
	*
	* @return the run type of this science app
	*/
	@Override
	public String getRunType() {
		return _scienceApp.getRunType();
	}

	/**
	* Returns the science app ID of this science app.
	*
	* @return the science app ID of this science app
	*/
	@Override
	public long getScienceAppId() {
		return _scienceApp.getScienceAppId();
	}

	/**
	* Returns the src file name of this science app.
	*
	* @return the src file name of this science app
	*/
	@Override
	public String getSrcFileName() {
		return _scienceApp.getSrcFileName();
	}

	/**
	* Returns the status of this science app.
	*
	* @return the status of this science app
	*/
	@Override
	public int getStatus() {
		return _scienceApp.getStatus();
	}

	/**
	* Returns the title of this science app.
	*
	* @return the title of this science app
	*/
	@Override
	public String getTitle() {
		return _scienceApp.getTitle();
	}

	/**
	* Returns the localized title of this science app in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param locale the locale of the language
	* @return the localized title of this science app
	*/
	@Override
	public String getTitle(java.util.Locale locale) {
		return _scienceApp.getTitle(locale);
	}

	/**
	* Returns the localized title of this science app in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param locale the local of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized title of this science app. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	*/
	@Override
	public String getTitle(java.util.Locale locale, boolean useDefault) {
		return _scienceApp.getTitle(locale, useDefault);
	}

	/**
	* Returns the localized title of this science app in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @return the localized title of this science app
	*/
	@Override
	public String getTitle(String languageId) {
		return _scienceApp.getTitle(languageId);
	}

	/**
	* Returns the localized title of this science app in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized title of this science app
	*/
	@Override
	public String getTitle(String languageId, boolean useDefault) {
		return _scienceApp.getTitle(languageId, useDefault);
	}

	@Override
	public String getTitleCurrentLanguageId() {
		return _scienceApp.getTitleCurrentLanguageId();
	}

	@Override
	public String getTitleCurrentValue() {
		return _scienceApp.getTitleCurrentValue();
	}

	/**
	* Returns a map of the locales and localized titles of this science app.
	*
	* @return the locales and localized titles of this science app
	*/
	@Override
	public Map<java.util.Locale, String> getTitleMap() {
		return _scienceApp.getTitleMap();
	}

	/**
	* Returns the user ID of this science app.
	*
	* @return the user ID of this science app
	*/
	@Override
	public long getUserId() {
		return _scienceApp.getUserId();
	}

	/**
	* Returns the user name of this science app.
	*
	* @return the user name of this science app
	*/
	@Override
	public String getUserName() {
		return _scienceApp.getUserName();
	}

	/**
	* Returns the user uuid of this science app.
	*
	* @return the user uuid of this science app
	*/
	@Override
	public String getUserUuid() {
		return _scienceApp.getUserUuid();
	}

	/**
	* Returns the uuid of this science app.
	*
	* @return the uuid of this science app
	*/
	@Override
	public String getUuid() {
		return _scienceApp.getUuid();
	}

	/**
	* Returns the version of this science app.
	*
	* @return the version of this science app
	*/
	@Override
	public String getVersion() {
		return _scienceApp.getVersion();
	}

	@Override
	public int hashCode() {
		return _scienceApp.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _scienceApp.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _scienceApp.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _scienceApp.isNew();
	}

	@Override
	public void persist() {
		_scienceApp.persist();
	}

	@Override
	public void prepareLocalizedFieldsForImport()
		throws com.liferay.portal.kernel.exception.LocaleException {
		_scienceApp.prepareLocalizedFieldsForImport();
	}

	@Override
	public void prepareLocalizedFieldsForImport(
		java.util.Locale defaultImportLocale)
		throws com.liferay.portal.kernel.exception.LocaleException {
		_scienceApp.prepareLocalizedFieldsForImport(defaultImportLocale);
	}

	/**
	* Sets the app type of this science app.
	*
	* @param appType the app type of this science app
	*/
	@Override
	public void setAppType(String appType) {
		_scienceApp.setAppType(appType);
	}

	/**
	* Sets the author ID of this science app.
	*
	* @param authorId the author ID of this science app
	*/
	@Override
	public void setAuthorId(long authorId) {
		_scienceApp.setAuthorId(authorId);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_scienceApp.setCachedModel(cachedModel);
	}

	/**
	* Sets the company ID of this science app.
	*
	* @param companyId the company ID of this science app
	*/
	@Override
	public void setCompanyId(long companyId) {
		_scienceApp.setCompanyId(companyId);
	}

	/**
	* Sets the create date of this science app.
	*
	* @param createDate the create date of this science app
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_scienceApp.setCreateDate(createDate);
	}

	/**
	* Sets the description ID of this science app.
	*
	* @param descriptionId the description ID of this science app
	*/
	@Override
	public void setDescriptionId(String descriptionId) {
		_scienceApp.setDescriptionId(descriptionId);
	}

	/**
	* Sets the localized description ID of this science app in the language.
	*
	* @param descriptionId the localized description ID of this science app
	* @param locale the locale of the language
	*/
	@Override
	public void setDescriptionId(String descriptionId, java.util.Locale locale) {
		_scienceApp.setDescriptionId(descriptionId, locale);
	}

	/**
	* Sets the localized description ID of this science app in the language, and sets the default locale.
	*
	* @param descriptionId the localized description ID of this science app
	* @param locale the locale of the language
	* @param defaultLocale the default locale
	*/
	@Override
	public void setDescriptionId(String descriptionId, java.util.Locale locale,
		java.util.Locale defaultLocale) {
		_scienceApp.setDescriptionId(descriptionId, locale, defaultLocale);
	}

	@Override
	public void setDescriptionIdCurrentLanguageId(String languageId) {
		_scienceApp.setDescriptionIdCurrentLanguageId(languageId);
	}

	/**
	* Sets the localized description IDs of this science app from the map of locales and localized description IDs.
	*
	* @param descriptionIdMap the locales and localized description IDs of this science app
	*/
	@Override
	public void setDescriptionIdMap(
		Map<java.util.Locale, String> descriptionIdMap) {
		_scienceApp.setDescriptionIdMap(descriptionIdMap);
	}

	/**
	* Sets the localized description IDs of this science app from the map of locales and localized description IDs, and sets the default locale.
	*
	* @param descriptionIdMap the locales and localized description IDs of this science app
	* @param defaultLocale the default locale
	*/
	@Override
	public void setDescriptionIdMap(
		Map<java.util.Locale, String> descriptionIdMap,
		java.util.Locale defaultLocale) {
		_scienceApp.setDescriptionIdMap(descriptionIdMap, defaultLocale);
	}

	/**
	* Sets the exe file name of this science app.
	*
	* @param exeFileName the exe file name of this science app
	*/
	@Override
	public void setExeFileName(String exeFileName) {
		_scienceApp.setExeFileName(exeFileName);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_scienceApp.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_scienceApp.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_scienceApp.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the group ID of this science app.
	*
	* @param groupId the group ID of this science app
	*/
	@Override
	public void setGroupId(long groupId) {
		_scienceApp.setGroupId(groupId);
	}

	/**
	* Sets the icon ID of this science app.
	*
	* @param iconId the icon ID of this science app
	*/
	@Override
	public void setIconId(String iconId) {
		_scienceApp.setIconId(iconId);
	}

	/**
	* Sets the localized icon ID of this science app in the language.
	*
	* @param iconId the localized icon ID of this science app
	* @param locale the locale of the language
	*/
	@Override
	public void setIconId(String iconId, java.util.Locale locale) {
		_scienceApp.setIconId(iconId, locale);
	}

	/**
	* Sets the localized icon ID of this science app in the language, and sets the default locale.
	*
	* @param iconId the localized icon ID of this science app
	* @param locale the locale of the language
	* @param defaultLocale the default locale
	*/
	@Override
	public void setIconId(String iconId, java.util.Locale locale,
		java.util.Locale defaultLocale) {
		_scienceApp.setIconId(iconId, locale, defaultLocale);
	}

	@Override
	public void setIconIdCurrentLanguageId(String languageId) {
		_scienceApp.setIconIdCurrentLanguageId(languageId);
	}

	/**
	* Sets the localized icon IDs of this science app from the map of locales and localized icon IDs.
	*
	* @param iconIdMap the locales and localized icon IDs of this science app
	*/
	@Override
	public void setIconIdMap(Map<java.util.Locale, String> iconIdMap) {
		_scienceApp.setIconIdMap(iconIdMap);
	}

	/**
	* Sets the localized icon IDs of this science app from the map of locales and localized icon IDs, and sets the default locale.
	*
	* @param iconIdMap the locales and localized icon IDs of this science app
	* @param defaultLocale the default locale
	*/
	@Override
	public void setIconIdMap(Map<java.util.Locale, String> iconIdMap,
		java.util.Locale defaultLocale) {
		_scienceApp.setIconIdMap(iconIdMap, defaultLocale);
	}

	/**
	* Sets the license of this science app.
	*
	* @param license the license of this science app
	*/
	@Override
	public void setLicense(String license) {
		_scienceApp.setLicense(license);
	}

	/**
	* Sets the manual ID of this science app.
	*
	* @param manualId the manual ID of this science app
	*/
	@Override
	public void setManualId(String manualId) {
		_scienceApp.setManualId(manualId);
	}

	/**
	* Sets the localized manual ID of this science app in the language.
	*
	* @param manualId the localized manual ID of this science app
	* @param locale the locale of the language
	*/
	@Override
	public void setManualId(String manualId, java.util.Locale locale) {
		_scienceApp.setManualId(manualId, locale);
	}

	/**
	* Sets the localized manual ID of this science app in the language, and sets the default locale.
	*
	* @param manualId the localized manual ID of this science app
	* @param locale the locale of the language
	* @param defaultLocale the default locale
	*/
	@Override
	public void setManualId(String manualId, java.util.Locale locale,
		java.util.Locale defaultLocale) {
		_scienceApp.setManualId(manualId, locale, defaultLocale);
	}

	@Override
	public void setManualIdCurrentLanguageId(String languageId) {
		_scienceApp.setManualIdCurrentLanguageId(languageId);
	}

	/**
	* Sets the localized manual IDs of this science app from the map of locales and localized manual IDs.
	*
	* @param manualIdMap the locales and localized manual IDs of this science app
	*/
	@Override
	public void setManualIdMap(Map<java.util.Locale, String> manualIdMap) {
		_scienceApp.setManualIdMap(manualIdMap);
	}

	/**
	* Sets the localized manual IDs of this science app from the map of locales and localized manual IDs, and sets the default locale.
	*
	* @param manualIdMap the locales and localized manual IDs of this science app
	* @param defaultLocale the default locale
	*/
	@Override
	public void setManualIdMap(Map<java.util.Locale, String> manualIdMap,
		java.util.Locale defaultLocale) {
		_scienceApp.setManualIdMap(manualIdMap, defaultLocale);
	}

	/**
	* Sets the modified date of this science app.
	*
	* @param modifiedDate the modified date of this science app
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_scienceApp.setModifiedDate(modifiedDate);
	}

	/**
	* Sets the name of this science app.
	*
	* @param name the name of this science app
	*/
	@Override
	public void setName(String name) {
		_scienceApp.setName(name);
	}

	@Override
	public void setNew(boolean n) {
		_scienceApp.setNew(n);
	}

	/**
	* Sets the open level of this science app.
	*
	* @param openLevel the open level of this science app
	*/
	@Override
	public void setOpenLevel(String openLevel) {
		_scienceApp.setOpenLevel(openLevel);
	}

	/**
	* Sets the previous version ID of this science app.
	*
	* @param previousVersionId the previous version ID of this science app
	*/
	@Override
	public void setPreviousVersionId(long previousVersionId) {
		_scienceApp.setPreviousVersionId(previousVersionId);
	}

	/**
	* Sets the primary key of this science app.
	*
	* @param primaryKey the primary key of this science app
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_scienceApp.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_scienceApp.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the register stage of this science app.
	*
	* @param registerStage the register stage of this science app
	*/
	@Override
	public void setRegisterStage(String registerStage) {
		_scienceApp.setRegisterStage(registerStage);
	}

	/**
	* Sets the run type of this science app.
	*
	* @param runType the run type of this science app
	*/
	@Override
	public void setRunType(String runType) {
		_scienceApp.setRunType(runType);
	}

	/**
	* Sets the science app ID of this science app.
	*
	* @param scienceAppId the science app ID of this science app
	*/
	@Override
	public void setScienceAppId(long scienceAppId) {
		_scienceApp.setScienceAppId(scienceAppId);
	}

	/**
	* Sets the src file name of this science app.
	*
	* @param srcFileName the src file name of this science app
	*/
	@Override
	public void setSrcFileName(String srcFileName) {
		_scienceApp.setSrcFileName(srcFileName);
	}

	/**
	* Sets the status of this science app.
	*
	* @param status the status of this science app
	*/
	@Override
	public void setStatus(int status) {
		_scienceApp.setStatus(status);
	}

	/**
	* Sets the title of this science app.
	*
	* @param title the title of this science app
	*/
	@Override
	public void setTitle(String title) {
		_scienceApp.setTitle(title);
	}

	/**
	* Sets the localized title of this science app in the language.
	*
	* @param title the localized title of this science app
	* @param locale the locale of the language
	*/
	@Override
	public void setTitle(String title, java.util.Locale locale) {
		_scienceApp.setTitle(title, locale);
	}

	/**
	* Sets the localized title of this science app in the language, and sets the default locale.
	*
	* @param title the localized title of this science app
	* @param locale the locale of the language
	* @param defaultLocale the default locale
	*/
	@Override
	public void setTitle(String title, java.util.Locale locale,
		java.util.Locale defaultLocale) {
		_scienceApp.setTitle(title, locale, defaultLocale);
	}

	@Override
	public void setTitleCurrentLanguageId(String languageId) {
		_scienceApp.setTitleCurrentLanguageId(languageId);
	}

	/**
	* Sets the localized titles of this science app from the map of locales and localized titles.
	*
	* @param titleMap the locales and localized titles of this science app
	*/
	@Override
	public void setTitleMap(Map<java.util.Locale, String> titleMap) {
		_scienceApp.setTitleMap(titleMap);
	}

	/**
	* Sets the localized titles of this science app from the map of locales and localized titles, and sets the default locale.
	*
	* @param titleMap the locales and localized titles of this science app
	* @param defaultLocale the default locale
	*/
	@Override
	public void setTitleMap(Map<java.util.Locale, String> titleMap,
		java.util.Locale defaultLocale) {
		_scienceApp.setTitleMap(titleMap, defaultLocale);
	}

	/**
	* Sets the user ID of this science app.
	*
	* @param userId the user ID of this science app
	*/
	@Override
	public void setUserId(long userId) {
		_scienceApp.setUserId(userId);
	}

	/**
	* Sets the user name of this science app.
	*
	* @param userName the user name of this science app
	*/
	@Override
	public void setUserName(String userName) {
		_scienceApp.setUserName(userName);
	}

	/**
	* Sets the user uuid of this science app.
	*
	* @param userUuid the user uuid of this science app
	*/
	@Override
	public void setUserUuid(String userUuid) {
		_scienceApp.setUserUuid(userUuid);
	}

	/**
	* Sets the uuid of this science app.
	*
	* @param uuid the uuid of this science app
	*/
	@Override
	public void setUuid(String uuid) {
		_scienceApp.setUuid(uuid);
	}

	/**
	* Sets the version of this science app.
	*
	* @param version the version of this science app
	*/
	@Override
	public void setVersion(String version) {
		_scienceApp.setVersion(version);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<ScienceApp> toCacheModel() {
		return _scienceApp.toCacheModel();
	}

	@Override
	public ScienceApp toEscapedModel() {
		return new ScienceAppWrapper(_scienceApp.toEscapedModel());
	}

	@Override
	public String toString() {
		return _scienceApp.toString();
	}

	@Override
	public ScienceApp toUnescapedModel() {
		return new ScienceAppWrapper(_scienceApp.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _scienceApp.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ScienceAppWrapper)) {
			return false;
		}

		ScienceAppWrapper scienceAppWrapper = (ScienceAppWrapper)obj;

		if (Objects.equals(_scienceApp, scienceAppWrapper._scienceApp)) {
			return true;
		}

		return false;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return _scienceApp.getStagedModelType();
	}

	@Override
	public ScienceApp getWrappedModel() {
		return _scienceApp;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _scienceApp.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _scienceApp.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_scienceApp.resetOriginalValues();
	}

	private final ScienceApp _scienceApp;
}