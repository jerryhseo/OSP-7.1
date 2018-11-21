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

import com.kisti.osp.spyglass.exception.NoSuchLayoutException;
import com.kisti.osp.spyglass.model.Layout;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

/**
 * The persistence interface for the layout service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.kisti.osp.spyglass.service.persistence.impl.LayoutPersistenceImpl
 * @see LayoutUtil
 * @generated
 */
@ProviderType
public interface LayoutPersistence extends BasePersistence<Layout> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link LayoutUtil} to access the layout persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the layout in the entity cache if it is enabled.
	*
	* @param layout the layout
	*/
	public void cacheResult(Layout layout);

	/**
	* Caches the layouts in the entity cache if it is enabled.
	*
	* @param layouts the layouts
	*/
	public void cacheResult(java.util.List<Layout> layouts);

	/**
	* Creates a new layout with the primary key. Does not add the layout to the database.
	*
	* @param scienceAppId the primary key for the new layout
	* @return the new layout
	*/
	public Layout create(long scienceAppId);

	/**
	* Removes the layout with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param scienceAppId the primary key of the layout
	* @return the layout that was removed
	* @throws NoSuchLayoutException if a layout with the primary key could not be found
	*/
	public Layout remove(long scienceAppId) throws NoSuchLayoutException;

	public Layout updateImpl(Layout layout);

	/**
	* Returns the layout with the primary key or throws a {@link NoSuchLayoutException} if it could not be found.
	*
	* @param scienceAppId the primary key of the layout
	* @return the layout
	* @throws NoSuchLayoutException if a layout with the primary key could not be found
	*/
	public Layout findByPrimaryKey(long scienceAppId)
		throws NoSuchLayoutException;

	/**
	* Returns the layout with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param scienceAppId the primary key of the layout
	* @return the layout, or <code>null</code> if a layout with the primary key could not be found
	*/
	public Layout fetchByPrimaryKey(long scienceAppId);

	@Override
	public java.util.Map<java.io.Serializable, Layout> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the layouts.
	*
	* @return the layouts
	*/
	public java.util.List<Layout> findAll();

	/**
	* Returns a range of all the layouts.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LayoutModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of layouts
	* @param end the upper bound of the range of layouts (not inclusive)
	* @return the range of layouts
	*/
	public java.util.List<Layout> findAll(int start, int end);

	/**
	* Returns an ordered range of all the layouts.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LayoutModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of layouts
	* @param end the upper bound of the range of layouts (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of layouts
	*/
	public java.util.List<Layout> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Layout> orderByComparator);

	/**
	* Returns an ordered range of all the layouts.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LayoutModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of layouts
	* @param end the upper bound of the range of layouts (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of layouts
	*/
	public java.util.List<Layout> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Layout> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the layouts from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of layouts.
	*
	* @return the number of layouts
	*/
	public int countAll();
}