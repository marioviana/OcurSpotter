package com.ocurspotter.dao;

import com.ocurspotter.model.User;

public interface UserDao {

	/**
	 * Save.
	 *
	 * @param user the user
	 */
	void save(User user);

	/**
	 * Find by username.
	 *
	 * @param username the username
	 * @return the user
	 */
	User findByUsername(String username);

}