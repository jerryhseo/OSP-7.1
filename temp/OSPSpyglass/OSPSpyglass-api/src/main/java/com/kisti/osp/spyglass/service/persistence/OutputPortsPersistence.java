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

package com.kisti.osp.spyglass.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.kisti.osp.spyglass.exception.NoSuchOutputPortsException;
import com.kisti.osp.spyglass.model.OutputPorts;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

/**
 * The persistence interface for the output ports service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.kisti.osp.spyglass.service.persistence.impl.OutputPortsPersistenceImpl
 * @see OutputPortsUtil
 * @generated
 */
@ProviderType
public interface OutputPortsPersistence extends BasePersistence<OutputPorts> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link OutputPortsUtil} to access the output ports persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the output ports in the entity cache if it is enabled.
	*
	* @param outputPorts the output ports
	*/
	public void cacheResult(OutputPorts outputPorts);

	/**
	* Caches the output portses in the entity cache if it is enabled.
	*
	* @param outputPortses the output portses
	*/
	public void cacheResult(java.util.List<OutputPorts> outputPortses);

	/**
	* Creates a new output ports with the primary key. Does not add the output ports to the database.
	*
	* @param scienceAppId the primary key for the new output ports
	* @return the new output ports
	*/
	public OutputPorts create(long scienceAppId);

	/**
	* Removes the output ports with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param scienceAppId the primary key of the output ports
	* @return the output ports that was removed
	* @throws NoSuchOutputPortsException if a output ports with the primary key could not be found
	*/
	public OutputPorts remove(long scienceAppId)
		throws NoSuchOutputPortsException;

	public OutputPorts updateImpl(OutputPorts outputPorts);

	/**
	* Returns the output ports with the primary key or throws a {@link NoSuchOutputPortsException} if it could not be found.
	*
	* @param scienceAppId the primary key of the output ports
	* @return the output ports
	* @throws NoSuchOutputPortsException if a output ports with the primary key could not be found
	*/
	public OutputPorts findByPrimaryKey(long scienceAppId)
		throws NoSuchOutputPortsException;

	/**
	* Returns the output ports with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param scienceAppId the primary key of the output ports
	* @return the output ports, or <code>null</code> if a output ports with the primary key could not be found
	*/
	public OutputPorts fetchByPrimaryKey(long scienceAppId);

	@Override
	public java.util.Map<java.io.Serializable, OutputPorts> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the output portses.
	*
	* @return the output portses
	*/
	public java.util.List<OutputPorts> findAll();

	/**
	* Returns a range of all the output portses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OutputPortsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of output portses
	* @param end the upper bound of the range of output portses (not inclusive)
	* @return the range of output portses
	*/
	public java.util.List<OutputPorts> findAll(int start, int end);

	/**
	* Returns an ordered range of all the output portses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OutputPortsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of output portses
	* @param end the upper bound of the range of output portses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of output portses
	*/
	public java.util.List<OutputPorts> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<OutputPorts> orderByComparator);

	/**
	* Returns an ordered range of all the output portses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OutputPortsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of output portses
	* @param end the upper bound of the range of output portses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of output portses
	*/
	public java.util.List<OutputPorts> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<OutputPorts> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the output portses from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of output portses.
	*
	* @return the number of output portses
	*/
	public int countAll();
}