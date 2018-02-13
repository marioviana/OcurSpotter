package com.ocurspotter.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.ocurspotter.dao.UserDao;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ocurspotter.model.User;

@Repository
public class UserDaoImpl implements UserDao {

	private static Log logger = LogFactory.getLog(UserDaoImpl.class);

	@Autowired
	private SessionFactory sessionFactory;

	/**
	 * Save.
	 *
	 * @param user the user
	 */
	public void save(User user) {
		logger.debug("Start saving user");
		try {
			sessionFactory.getCurrentSession().save(user);
		} catch (Exception e) {
			logger.error("An error has occurred while saving an user", e);
		} finally {
			logger.debug("End saving user");
		}
	}

	/**
	 * Find by username.
	 *
	 * @param username the username
	 * @return the user
	 */
	@SuppressWarnings("unchecked")
	public User findByUsername(String username) {
		logger.debug("Start getting the user: " + username);
		try {
			List<User> users = new ArrayList<User>();
			users = sessionFactory.getCurrentSession().createQuery("from User where username=?").setParameter(0, username)
				.list();
			if (users.size() > 0) {
				return users.get(0);
			} else {
				return null;
			}
		} catch (Exception e) {
			logger.error("An error has occurred while getting the user", e);
		} finally {
			logger.debug("End of get the user");
		}
		return null;
	}

}