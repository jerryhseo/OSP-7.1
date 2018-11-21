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

package com.kisti.osp.spyglass.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

/**
 * The extended model interface for the ScienceApp service. Represents a row in the &quot;SPYGLASS_ScienceApp&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see ScienceAppModel
 * @see com.kisti.osp.spyglass.model.impl.ScienceAppImpl
 * @see com.kisti.osp.spyglass.model.impl.ScienceAppModelImpl
 * @generated
 */
@ImplementationClassName("com.kisti.osp.spyglass.model.impl.ScienceAppImpl")
@ProviderType
public interface ScienceApp extends ScienceAppModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.kisti.osp.spyglass.model.impl.ScienceAppImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<ScienceApp, Long> SCIENCE_APP_ID_ACCESSOR = new Accessor<ScienceApp, Long>() {
			@Override
			public Long get(ScienceApp scienceApp) {
				return scienceApp.getScienceAppId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<ScienceApp> getTypeClass() {
				return ScienceApp.class;
			}
		};
}