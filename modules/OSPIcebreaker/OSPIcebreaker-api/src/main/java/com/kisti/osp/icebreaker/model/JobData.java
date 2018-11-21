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

package com.kisti.osp.icebreaker.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

/**
 * The extended model interface for the JobData service. Represents a row in the &quot;icebreaker_JobData&quot; database table, with each column mapped to a property of this class.
 *
 * @author Jerry H. Seo
 * @see JobDataModel
 * @see com.kisti.osp.icebreaker.model.impl.JobDataImpl
 * @see com.kisti.osp.icebreaker.model.impl.JobDataModelImpl
 * @generated
 */
@ImplementationClassName("com.kisti.osp.icebreaker.model.impl.JobDataImpl")
@ProviderType
public interface JobData extends JobDataModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.kisti.osp.icebreaker.model.impl.JobDataImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<JobData, Long> JOB_ID_ACCESSOR = new Accessor<JobData, Long>() {
			@Override
			public Long get(JobData jobData) {
				return jobData.getJobId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<JobData> getTypeClass() {
				return JobData.class;
			}
		};
}