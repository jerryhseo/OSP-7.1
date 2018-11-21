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

package com.kisti.osp.spyglass.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link ScienceAppService}.
 *
 * @author Brian Wing Shun Chan
 * @see ScienceAppService
 * @generated
 */
@ProviderType
public class ScienceAppServiceWrapper implements ScienceAppService,
	ServiceWrapper<ScienceAppService> {
	public ScienceAppServiceWrapper(ScienceAppService scienceAppService) {
		_scienceAppService = scienceAppService;
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public String getOSGiServiceIdentifier() {
		return _scienceAppService.getOSGiServiceIdentifier();
	}

	@Override
	public ScienceAppService getWrappedService() {
		return _scienceAppService;
	}

	@Override
	public void setWrappedService(ScienceAppService scienceAppService) {
		_scienceAppService = scienceAppService;
	}

	private ScienceAppService _scienceAppService;
}