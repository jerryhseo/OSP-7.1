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

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

/**
 * The extended model interface for the AssignedScheduler service. Represents a row in the &quot;SPYGLASS_AssignedScheduler&quot; database table, with each column mapped to a property of this class.
 *
 * @author Jerry H. Seo
 * @see AssignedSchedulerModel
 * @see com.osp.spyglass.model.impl.AssignedSchedulerImpl
 * @see com.osp.spyglass.model.impl.AssignedSchedulerModelImpl
 * @generated
 */
@ImplementationClassName("com.osp.spyglass.model.impl.AssignedSchedulerImpl")
@ProviderType
public interface AssignedScheduler extends AssignedSchedulerModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.osp.spyglass.model.impl.AssignedSchedulerImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<AssignedScheduler, Long> SCIENCE_APP_ID_ACCESSOR =
		new Accessor<AssignedScheduler, Long>() {
			@Override
			public Long get(AssignedScheduler assignedScheduler) {
				return assignedScheduler.getScienceAppId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<AssignedScheduler> getTypeClass() {
				return AssignedScheduler.class;
			}
		};
}