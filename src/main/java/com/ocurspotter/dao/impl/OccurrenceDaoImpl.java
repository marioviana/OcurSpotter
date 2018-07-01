package com.ocurspotter.dao.impl;

import com.ocurspotter.dao.OccurrenceDao;
import com.ocurspotter.model.Occurrence;
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
public class OccurrenceDaoImpl implements OccurrenceDao {

	private static Logger logger = Logger.getLogger(OccurrenceDaoImpl.class);

	@Autowired
	private SessionFactory sessionFactory;

	/**
	 * Save.
	 *
	 * @param occurrence the occurrence
	 */
	public void save(Occurrence occurrence) {
		logger.info("Start saving occurrence");
		try {
			sessionFactory.getCurrentSession().save(occurrence);
		} catch (Exception e) {
			logger.error("An error has occurred while saving an occurrence", e);
		} finally {
			logger.info("End saving occurrence");
		}
	}

	/**
	 * Get by id.
	 *
	 * @param id the occurrence id
	 */
	public Occurrence getById(Long id) {
		logger.info("Start getting the occurrence by id: " + id);
		try {
			final Criteria criteria = sessionFactory.getCurrentSession()
				.createCriteria(Occurrence.class).add(Restrictions.eq("id", id));
			return (Occurrence) criteria.uniqueResult();
		} catch (Exception e) {
			logger.error("An error has occurred while getting an occurrence", e);
		} finally {
			logger.info("End getting occurrence");
		}
		return null;
	}

	/**
	 * Gets the all.
	 *
	 * @param type the types
	 * @param userId the user id
	 * @param suggestion the suggestion
	 * @param keyWord the key word
	 * @return the all
	 */
	@SuppressWarnings("unchecked")
	public List<Occurrence> getAll(Long[] type, Long[] userId, Integer suggestion, String keyWord, Integer status) {
		logger.info("Start get all occurrences");
		try {
			final Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Occurrence.class);
			if (type != null && type.length > 0) {
				criteria.add(Restrictions.in("type.id", type));
			}
			if (userId != null){
				criteria.add(Restrictions.in("user.id", userId));
			}
			if (suggestion != null){
				if (suggestion == 0) {
					criteria.add(Restrictions.eq("suggestion", Boolean.FALSE));
				}
				if (suggestion == 1){
					criteria.add(Restrictions.eq("suggestion", Boolean.TRUE));
				}
			}
			if (status != null){
				if (status == 0) {
					criteria.add(Restrictions.eq("status", Boolean.FALSE));
				}
				if (status == 1){
					criteria.add(Restrictions.eq("status", Boolean.TRUE));
				}
			}
			if (keyWord != null) {
				criteria.add(Restrictions.or(Restrictions.like("title", "%" + keyWord + "%"),
						Restrictions.like("description", "%" + keyWord + "%")));
			}
			return (List<Occurrence>) criteria.list();
		} catch (Exception e) {
			logger.error("An error has occurred while getting all the occurrences", e);
		} finally {
			logger.info("End get all occurrences");
		}
		return new ArrayList<Occurrence>(0);
	}

	/**
	 * Find by solution.
	 *
	 * @param id the solution id
	 * @return the occurrence
	 */
	@SuppressWarnings("unchecked")
	public Occurrence getBySolution(Long id) {
		logger.info("Start getting the occurrence by solution id: " + id);
		try {
			List<BigInteger> occurrences = sessionFactory.getCurrentSession().createSQLQuery("select O.id from Occurrence as O INNER JOIN Solution as S ON S.occurrenceId = O.id WHERE S.id = :id").setParameter("id", id)
				.list();
			if (occurrences.size() > 0) {
				return getById(occurrences.get(0).longValue());
			} else {
				return null;
			}
		} catch (Exception e) {
			logger.error("An error has occurred while getting the occurrence", e);
		} finally {
			logger.info("End of get the occurrence");
		}
		return null;
	}

	/* Count occurrences
	 *
	 * @return the count
	 */
	@SuppressWarnings("unchecked")
	public Long count() {
		logger.info("Start getting the occurrence count");
		try {
			return (Long) sessionFactory.getCurrentSession()
					.createCriteria(Occurrence.class)
					.setProjection(Projections.rowCount())
					.uniqueResult();
		} catch (Exception e) {
			logger.error("An error has occurred while getting the occurrence count", e);
		} finally {
			logger.info("End of get the occurrence count");
		}
		return null;
	}

}