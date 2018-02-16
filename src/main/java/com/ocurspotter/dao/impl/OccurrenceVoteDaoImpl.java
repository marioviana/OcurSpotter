package com.ocurspotter.dao.impl;

import com.ocurspotter.dao.OccurrenceVoteDao;
import com.ocurspotter.model.OccurrenceVote;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
public class OccurrenceVoteDaoImpl implements OccurrenceVoteDao {

	private static Logger logger = Logger.getLogger(OccurrenceVoteDaoImpl.class);

	@Autowired
	private SessionFactory sessionFactory;

	/**
	 * Save.
	 *
	 * @param occurrence the occurrence
	 */
	public void save(OccurrenceVote occurrence) {
		logger.info("Start saving occurrence vote");
		try {
			sessionFactory.getCurrentSession().save(occurrence);
		} catch (Exception e) {
			logger.error("An error has occurred while saving an occurrence vote", e);
		} finally {
			logger.info("End saving occurrence vote");
		}
	}

	/**
	 * Get by id.
	 *
	 * @param id the occurrence id
	 */
	public OccurrenceVote getById(Long id) {
		logger.info("Start getting the occurrence vot eby id: " + id);
		try {
			final Criteria criteria = sessionFactory.getCurrentSession()
				.createCriteria(OccurrenceVote.class).add(Restrictions.eq("id", id));
			return (OccurrenceVote) criteria.uniqueResult();
		} catch (Exception e) {
			logger.error("An error has occurred while getting a occurence vote", e);
		} finally {
			logger.info("End getting occurence vote");
		}
		return null;
	}

	/**
	 * Find downvotes by occurrence.
	 *
	 * @param id the occurrence id
	 * @return the occurrence votes
	 */
	@SuppressWarnings("unchecked")
	public Long getDownvotesBySolution(Long id) {
		logger.info("Start getting the occurrence votes by occurrence id: " + id);
		try {
			List<BigInteger> occurrencesVote = sessionFactory.getCurrentSession().createSQLQuery("select O.id from OccurrenceVote as OV INNER JOIN Occurrence as O ON OV.occurrenceId = O.id WHERE O.id = :id and vote=0").setParameter("id", id)
				.list();
			if (occurrencesVote.size() > 0) {
				return (long) occurrencesVote.size();
			} else {
				return (long) 0;
			}
		} catch (Exception e) {
			logger.error("An error has occurred while getting the occurrence votes", e);
		} finally {
			logger.info("End of get the occurrence votes");
		}
		return null;
	}

	/**
	 * Find upvotes by occurrence.
	 *
	 * @param id the occurrence id
	 * @return the occurrence votes
	 */
	@SuppressWarnings("unchecked")
	public Long getUpvotesBySolution(Long id) {
		logger.info("Start getting the occurrence votes by occurrence id: " + id);
		try {
			List<BigInteger> occurrencesVote = sessionFactory.getCurrentSession().createSQLQuery("select O.id from OccurrenceVote as OV INNER JOIN Occurrence as O ON OV.occurrenceId = O.id WHERE O.id = :id and vote=1").setParameter("id", id)
				.list();
			if (occurrencesVote.size() > 0) {
				return (long) occurrencesVote.size();
			} else {
				return (long) 0;
			}
		} catch (Exception e) {
			logger.error("An error has occurred while getting the occurrence votes", e);
		} finally {
			logger.info("End of get the occurrence votes");
		}
		return null;
	}

}