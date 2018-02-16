package com.ocurspotter.dao.impl;

import com.ocurspotter.dao.SolutionDao;
import com.ocurspotter.model.Solution;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
public class SolutionDaoImpl implements SolutionDao {

	private static Logger logger = Logger.getLogger(UserDaoImpl.class);

	@Autowired
	private SessionFactory sessionFactory;

	/**
	 * Save.
	 *
	 * @param solution the solution
	 */
	public void save(Solution solution) {
		logger.info("Start saving the solution");
		try {
			sessionFactory.getCurrentSession().save(solution);
		} catch (Exception e) {
			logger.error("An error has occurred while saving a solution", e);
		} finally {
			logger.info("End saving solution");
		}
	}

	/**
	 * Get by id.
	 *
	 * @param id the solution id
	 */
	public Solution getById(Long id) {
		logger.info("Start getting the solution by id: " + id);
		try {
			final Criteria criteria = sessionFactory.getCurrentSession()
				.createCriteria(Solution.class).add(Restrictions.eq("id", id));
			return (Solution) criteria.uniqueResult();
		} catch (Exception e) {
			logger.error("An error has occurred while getting a solution", e);
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
	public List<Solution> getAll() {
		logger.info("Start get all solutions");
		try {
			final Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Solution.class);
			return (List<Solution>) criteria.list();
		} catch (Exception e) {
			logger.error("An error has occurred while getting all the solutions", e);
		} finally {
			logger.info("End get all solutions");
		}
		return new ArrayList<Solution>(0);
	}

	/**
	 * Get solutions by occurrence.
	 *
	 * @paramm id the occurrence id
	 * @return the solutions
	 */
	@SuppressWarnings("unchecked")
	public List<Solution> getByOccurrence(Long id) {
		logger.info("Start get all solutions");
		try {
			final List<Solution> result = (List<Solution>) sessionFactory.getCurrentSession()
				.createCriteria(Solution.class).add(Restrictions.eq("occurrence.id", id))
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
			return result;
		} catch (Exception e) {
			logger.error("An error has occurred while getting all the solutions", e);
		} finally {
			logger.info("End get all solutions");
		}
		return new ArrayList<Solution>(0);
	}

}