package com.ocurspotter.dao;

import com.ocurspotter.model.UserRole;

import java.util.List;

public interface UserRoleDao {

	/**
	 * Save.
	 *
	 * @param userRole the user role
	 */
	void save(UserRole userRole);

	/**
	 * Find by user id.
	 *
	 * @param userId the user id
	 * @return the list
	 */
	List<UserRole> findByUser(Integer userId);

}