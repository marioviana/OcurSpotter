package com.ocurspotter.dao.impl;

import com.ocurspotter.dao.SolutionVoteDao;
import com.ocurspotter.model.SolutionVote;
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
public class SolutionVoteDaoImpl implements SolutionVoteDao {

	private static Logger logger = Logger.getLogger(SolutionVoteDaoImpl.class);

	@Autowired
	private SessionFactory sessionFactory;

	/**
	 * Save.
	 *
	 * @param solution the solution
	 */
	public void save(SolutionVote solution) {
		logger.info("Start saving solution");
		try {
			sessionFactory.getCurrentSession().save(solution);
		} catch (Exception e) {
			logger.error("An error has occurred while saving an solution", e);
		} finally {
			logger.info("End saving solution");
		}
	}

	/**
	 * Get by id.
	 *
	 * @param id the solution vote id
	 */
	public SolutionVote getById(Long id) {
		logger.info("Start getting the solution vote by id: " + id);
		try {
			final Criteria criteria = sessionFactory.getCurrentSession()
				.createCriteria(SolutionVote.class).add(Restrictions.eq("id", id));
			return (SolutionVote) criteria.uniqueResult();
		} catch (Exception e) {
			logger.error("An error has occurred while getting an solution vote", e);
		} finally {
			logger.info("End getting solution");
		}
		return null;
	}

	/**
	 * Gets the all.
	 *
	 * @return the all
	 */
	@SuppressWarnings("unchecked")
	public List<SolutionVote> getAll() {
		logger.info("Start get all solution votes");
		try {
			final Criteria criteria = sessionFactory.getCurrentSession().createCriteria(SolutionVote.class);
			return (List<SolutionVote>) criteria.list();
		} catch (Exception e) {
			logger.error("An error has occurred while getting all the solution votes", e);
		} finally {
			logger.info("End get all solution votes");
		}
		return new ArrayList<SolutionVote>(0);
	}

	/**
	 * Find downvotes by solution.
	 *
	 * @param id the solution id
	 * @return the solution votes
	 */
	@SuppressWarnings("unchecked")
	public Long getDownvotesBySolution(Long id) {
		logger.info("Start getting the solution votes by solution id: " + id);
		try {
			List<BigInteger> solutionsVote = sessionFactory.getCurrentSession().createSQLQuery("select S.id from SolutionVote as SV INNER JOIN Solution as S ON SV.solutionId = S.id WHERE S.id = :id and vote=0").setParameter("id", id)
				.list();
			if (solutionsVote.size() > 0) {
				return (long) solutionsVote.size();
			} else {
				return (long) 0;
			}
		} catch (Exception e) {
			logger.error("An error has occurred while getting the solution", e);
		} finally {
			logger.info("End of get the solution");
		}
		return null;
	}

	/**
	 * Find upvotes by solution.
	 *
	 * @param id the solution id
	 * @return the solution votes
	 */
	@SuppressWarnings("unchecked")
	public Long getUpvotesBySolution(Long id) {
		logger.info("Start getting the solution votes by solution id: " + id);
		try {
			List<BigInteger> solutionsVote = sessionFactory.getCurrentSession().createSQLQuery("select S.id from SolutionVote as SV INNER JOIN Solution as S ON SV.solutionId = S.id WHERE S.id = :id and vote=1").setParameter("id", id)
				.list();
			if (solutionsVote.size() > 0) {
				return (long) solutionsVote.size();
			} else {
				return (long) 0;
			}
		} catch (Exception e) {
			logger.error("An error has occurred while getting the solution", e);
		} finally {
			logger.info("End of get the solution");
		}
		return null;
	}
}