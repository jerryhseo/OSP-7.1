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
 * The extended model interface for the LogPorts service. Represents a row in the &quot;SPYGLASS_LogPorts&quot; database table, with each column mapped to a property of this class.
 *
 * @author Jerry H. Seo
 * @see LogPortsModel
 * @see com.osp.spyglass.model.impl.LogPortsImpl
 * @see com.osp.spyglass.model.impl.LogPortsModelImpl
 * @generated
 */
@ImplementationClassName("com.osp.spyglass.model.impl.LogPortsImpl")
@ProviderType
public interface LogPorts extends LogPortsModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.osp.spyglass.model.impl.LogPortsImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<LogPorts, Long> SCIENCE_APP_ID_ACCESSOR = new Accessor<LogPorts, Long>() {
			@Override
			public Long get(LogPorts logPorts) {
				return logPorts.getScienceAppId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<LogPorts> getTypeClass() {
				return LogPorts.class;
			}
		};
}