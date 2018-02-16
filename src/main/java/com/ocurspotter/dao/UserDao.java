package com.ocurspotter.dao;

import com.ocurspotter.model.User;

import java.util.List;

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

	/**
	 * Get by id.
	 *
	 * @param id the user id
	 */
	User getById(Long id);

	/**
	 * Gets the all.
	 *
	 * @return the all
	 */
	List<User> getAll();

	/**
	 * Find by solution.
	 *
	 * @param id the solution id
	 * @return the user
	 */
	User getBySolution(Long id);

	/**
	 * Find by occurrence.
	 *
	 * @param id the occurrence id
	 * @return the user
	 */
	User getByOccurrence(Long id);
}