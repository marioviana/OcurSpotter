package com.ocurspotter.dao.impl;

import com.ocurspotter.dao.UserRoleDao;
import com.ocurspotter.model.UserRole;
import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
public class UserRoleDaoImpl implements UserRoleDao {

	private static Logger logger = Logger.getLogger(UserRoleDaoImpl.class);

	@Autowired
	private SessionFactory sessionFactory;

	/**
	 * Save.
	 *
	 * @param userRole the user role
	 */
	public void save(UserRole userRole) {
		logger.info("Start saving the user role");
		try {
			sessionFactory.getCurrentSession().save(userRole);
		} catch (Exception e) {
			logger.error("An error has occurred while saving an user role", e);
		} finally {
			logger.info("End saving user role");
		}
	}

	/**
	 * Find by user id.
	 *
	 * @param userId the user id
	 * @return the list
	 */
	@SuppressWarnings("unchecked")
	public List<UserRole> findByUser(Integer userId) {
		logger.info("Start getting the user roles of user: " + userId);
		try {
			List<UserRole> usersRole = new ArrayList<UserRole>();

			usersRole = sessionFactory.getCurrentSession().createQuery("from UserRole where userId=?")
				.setParameter(0, userId).list();

			if (usersRole.size() > 0) {
				return usersRole;
			} else {
				return null;
			}
		} catch (Exception e) {
			logger.error("An error has occurred while getting the user roles", e);
		} finally {
			logger.info("End of get the user roles");
		}
		return null;

	}

}