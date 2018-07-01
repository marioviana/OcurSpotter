package com.ocurspotter.dao.impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import com.ocurspotter.dao.UserDao;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ocurspotter.model.User;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class UserDaoImpl implements UserDao {

	private static Logger logger = Logger.getLogger(UserDaoImpl.class);

	@Autowired
	private SessionFactory sessionFactory;

	/**
	 * Save.
	 *
	 * @param user the user
	 */
	public void save(User user) {
		logger.info("Start saving user");
		try {
			sessionFactory.getCurrentSession().save(user);
		} catch (Exception e) {
			logger.error("An error has occurred while saving an user", e);
		} finally {
			logger.info("End saving user");
		}
	}

	/**
	 * Update.
	 *
	 * @param user the user
	 */
	public void update(User user) {
		logger.info("Start saving user");
		try {
			sessionFactory.getCurrentSession().update(user);
		} catch (Exception e) {
			logger.error("An error has occurred while saving an user", e);
		} finally {
			logger.info("End saving user");
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
		logger.info("Start getting the user: " + username);
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
			logger.info("End of get the user");
		}
		return null;
	}

	/**
	 * Get by id.
	 *
	 * @param id the user id
	 * @return the user
	 */
	public User getById(Long id) {
		logger.info("Start getting the user by id: " + id);
		try {
			final Criteria criteria = sessionFactory.getCurrentSession()
				.createCriteria(User.class).add(Restrictions.eq("id", id));
			return (User) criteria.uniqueResult();
		} catch (Exception e) {
			logger.error("An error has occurred while getting a user", e);
		} finally {
			logger.info("End getting user");
		}
		return null;
	}

	/**
	 * Get by id.
	 *
	 * @param username the user name.
	 * @return the user
	 */
	public User getByUsername(String username) {
		logger.info("Start getting the user");
		try {
			final Criteria criteria = sessionFactory.getCurrentSession()
					.createCriteria(User.class)
					.add(Restrictions.eq("username", username));
			return (User) criteria.uniqueResult();
		} catch (Exception e) {
			logger.error("An error has occurred while getting a user", e);
		} finally {
			logger.info("End getting user");
		}
		return null;
	}

	/**
	 * Gets the all.
	 *
	 * @return the all
	 */
	@SuppressWarnings("unchecked")
	public List<User> getAll() {
		logger.info("Start get all occurrences");
		try {
			final Criteria criteria = sessionFactory.getCurrentSession().createCriteria(User.class);
			return (List<User>) criteria.list();
		} catch (Exception e) {
			logger.error("An error has occurred while getting all the occurrences", e);
		} finally {
			logger.info("End get all occurrences");
		}
		return new ArrayList<User>(0);
	}

	/**
	 * Find by solution.
	 *
	 * @param id the solution id
	 * @return the user
	 */
	@SuppressWarnings("unchecked")
	public User getBySolution(Long id) {
		logger.info("Start getting the user by solution id: " + id);
		try {
			List<BigInteger> users = sessionFactory.getCurrentSession().createSQLQuery("select U.id from User as U INNER JOIN Solution as S ON S.userId = U.id WHERE S.id = :id").setParameter("id", id)
				.list();
			if (users.size() > 0) {
				return getById(users.get(0).longValue());
			} else {
				return null;
			}
		} catch (Exception e) {
			logger.error("An error has occurred while getting the user", e);
		} finally {
			logger.info("End of get the user by solution");
		}
		return null;
	}

	/**
	 * Find by occurrence.
	 *
	 * @param id the occurrence id
	 * @return the user
	 */
	@SuppressWarnings("unchecked")
	public User getByOccurrence(Long id) {
		logger.info("Start getting the user by occurrence id: " + id);
		try {
			List<BigInteger> users = sessionFactory.getCurrentSession().createSQLQuery("select U.id from User as U INNER JOIN Occurrence as O ON O.userId = U.id WHERE O.id = :id").setParameter("id", id)
				.list();
			if (users.size() > 0) {
				return getById(users.get(0).longValue());
			} else {
				return null;
			}
		} catch (Exception e) {
			logger.error("An error has occurred while getting the user", e);
		} finally {
			logger.info("End of get the user by occurrence");
		}
		return null;
	}

	/* Count users
	 *
	 * @return the count
	 */
	@SuppressWarnings("unchecked")
	public Long count() {
		logger.info("Start getting the user count");
		try {
			return (Long) sessionFactory.getCurrentSession()
					.createCriteria(User.class)
					.setProjection(Projections.rowCount())
					.uniqueResult();
		} catch (Exception e) {
			logger.error("An error has occurred while getting the user count", e);
		} finally {
			logger.info("End of get the user count");
		}
		return null;
	}
}