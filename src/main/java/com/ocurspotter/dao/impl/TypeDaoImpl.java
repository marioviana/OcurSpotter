package com.ocurspotter.dao.impl;

import com.ocurspotter.dao.TypeDao;
import com.ocurspotter.model.Type;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
public class TypeDaoImpl implements TypeDao {

	private static Logger logger = Logger.getLogger(TypeDaoImpl.class);

	@Autowired
	private SessionFactory sessionFactory;

	/**
	 * Get by id.
	 *
	 * @param id the type id
	 */
	public Type getById(Long id) {
		logger.info("Start getting the type by id: " + id);
		try {
			final Criteria criteria = sessionFactory.getCurrentSession()
				.createCriteria(Type.class).add(Restrictions.eq("id", id));
			return (Type) criteria.uniqueResult();
		} catch (Exception e) {
			logger.error("An error has occurred while getting a type", e);
		} finally {
			logger.info("End getting type");
		}
		return null;
	}

	/**
	 * Gets the all.
	 *
	 * @return the all
	 */
	@SuppressWarnings("unchecked")
	public List<Type> getAll() {
		logger.info("Start get all types");
		try {
			final Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Type.class);
			return (List<Type>) criteria.list();
		} catch (Exception e) {
			logger.error("An error has occurred while getting all the types", e);
		} finally {
			logger.info("End get all occurrences");
		}
		return new ArrayList<Type>(0);
	}

	/**
	 * Save.
	 *
	 * @param type the type
	 */
	public void save(Type type) {
		logger.info("Start saving the type");
		try {
			sessionFactory.getCurrentSession().save(type);
		} catch (Exception e) {
			logger.error("An error has occurred while saving a type", e);
		} finally {
			logger.info("End saving type");
		}
	}

	/**
	 * Find by occurrence.
	 *
	 * @param id the occurrence id
	 * @return the user
	 */
	@SuppressWarnings("unchecked")
	public Type getByOccurrence(Long id) {
		logger.info("Start getting the type by occurrence id: " + id);
		try {
			List<BigInteger> types = sessionFactory.getCurrentSession().createSQLQuery("select T.id from Type as T INNER JOIN Occurrence as O ON O.typeId = T.id WHERE O.id = :id").setParameter("id", id)
				.list();
			if (types.size() > 0) {
				return getById(types.get(0).longValue());
			} else {
				return null;
			}
		} catch (Exception e) {
			logger.error("An error has occurred while getting the type", e);
		} finally {
			logger.info("End of get the type by occurrence");
		}
		return null;
	}

	/* Count types
	 *
	 * @return the count
	 */
	@SuppressWarnings("unchecked")
	public Long count() {
		logger.info("Start getting the type count");
		try {
			return (Long) sessionFactory.getCurrentSession()
					.createCriteria(Type.class)
					.setProjection(Projections.rowCount())
					.uniqueResult();
		} catch (Exception e) {
			logger.error("An error has occurred while getting the type count", e);
		} finally {
			logger.info("End of get the type count");
		}
		return null;
	}
}