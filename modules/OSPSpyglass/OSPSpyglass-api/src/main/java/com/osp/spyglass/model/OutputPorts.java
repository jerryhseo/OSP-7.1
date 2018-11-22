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
 * The extended model interface for the OutputPorts service. Represents a row in the &quot;SPYGLASS_OutputPorts&quot; database table, with each column mapped to a property of this class.
 *
 * @author Jerry H. Seo
 * @see OutputPortsModel
 * @see com.osp.spyglass.model.impl.OutputPortsImpl
 * @see com.osp.spyglass.model.impl.OutputPortsModelImpl
 * @generated
 */
@ImplementationClassName("com.osp.spyglass.model.impl.OutputPortsImpl")
@ProviderType
public interface OutputPorts extends OutputPortsModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.osp.spyglass.model.impl.OutputPortsImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<OutputPorts, Long> SCIENCE_APP_ID_ACCESSOR = new Accessor<OutputPorts, Long>() {
			@Override
			public Long get(OutputPorts outputPorts) {
				return outputPorts.getScienceAppId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<OutputPorts> getTypeClass() {
				return OutputPorts.class;
			}
		};
}