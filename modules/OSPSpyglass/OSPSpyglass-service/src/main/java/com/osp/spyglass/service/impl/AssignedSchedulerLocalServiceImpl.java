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

package com.osp.spyglass.service.impl;

import com.osp.spyglass.model.AssignedScheduler;
import com.osp.spyglass.model.InputPorts;
import com.osp.spyglass.service.base.AssignedSchedulerLocalServiceBaseImpl;

/**
 * The implementation of the assigned scheduler local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.osp.spyglass.service.AssignedSchedulerLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Jerry H. Seo
 * @see AssignedSchedulerLocalServiceBaseImpl
 * @see com.osp.spyglass.service.AssignedSchedulerLocalServiceUtil
 */
public class AssignedSchedulerLocalServiceImpl
	extends AssignedSchedulerLocalServiceBaseImpl {
	public AssignedScheduler setSchedulers( long scienceAppId, String schedulers ){
		AssignedScheduler assignedSchedulers = super.createAssignedScheduler(scienceAppId);
		assignedSchedulers.setAssignedSchedulers(schedulers);
		
		return super.addAssignedScheduler(assignedSchedulers);
	}
}