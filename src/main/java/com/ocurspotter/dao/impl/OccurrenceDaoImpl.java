package com.ocurspotter.dao.impl;

import com.ocurspotter.dao.OccurrenceDao;
import com.ocurspotter.model.Occurrence;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class OccurrenceDaoImpl implements OccurrenceDao {

	private static Log logger = LogFactory.getLog(OccurrenceDaoImpl.class);

	@Autowired
	private SessionFactory sessionFactory;

	/**
	 * Save.
	 *
	 * @param occurrence the occurrence
	 */
	public void save(Occurrence occurrence) {
		logger.debug("Start saving occurrence");
		try {
			sessionFactory.getCurrentSession().save(occurrence);
		} catch (Exception e) {
			logger.error("An error has occurred while saving an occurrence", e);
		} finally {
			logger.debug("End saving occurrence");
		}
	}

	/**
	 * Gets the all.
	 *
	 * @return the all
	 */
	@SuppressWarnings("unchecked")
	public List<Occurrence> getAll() {
		logger.debug("Start get all occurrences");
		try {
			final Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Occurrence.class);
			return (List<Occurrence>) criteria.list();
		} catch (Exception e) {
			logger.error("An error has occurred while getting all the occurrences", e);
		} finally {
			logger.debug("End get all occurrences");
		}
		return new ArrayList<Occurrence>(0);
	}

}