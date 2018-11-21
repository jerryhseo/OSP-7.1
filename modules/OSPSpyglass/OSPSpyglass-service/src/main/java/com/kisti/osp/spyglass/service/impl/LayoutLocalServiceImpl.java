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

package com.kisti.osp.spyglass.service.impl;

import aQute.bnd.annotation.ProviderType;

import com.kisti.osp.spyglass.model.Layout;
import com.kisti.osp.spyglass.service.base.LayoutLocalServiceBaseImpl;

/**
 * The implementation of the layout local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.kisti.osp.spyglass.service.LayoutLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see LayoutLocalServiceBaseImpl
 * @see com.kisti.osp.spyglass.service.LayoutLocalServiceUtil
 */
@ProviderType
public class LayoutLocalServiceImpl extends LayoutLocalServiceBaseImpl {
	public Layout addLayout( long scienceAppId, String layout ){
		Layout model = super.createLayout(scienceAppId);
		model.setLayout(layout);
		
		return super.addLayout(model);
	}
}