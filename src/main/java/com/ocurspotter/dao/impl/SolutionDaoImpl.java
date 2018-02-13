package com.ocurspotter.dao.impl;

import com.ocurspotter.dao.SolutionDao;
import com.ocurspotter.model.Solution;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class SolutionDaoImpl implements SolutionDao {

	private static Log logger = LogFactory.getLog(UserDaoImpl.class);

	@Autowired
	private SessionFactory sessionFactory;

	/**
	 * Save.
	 *
	 * @param solution the solution
	 */
	public void save(Solution solution) {
		logger.debug("Start saving the solution");
		try {
			sessionFactory.getCurrentSession().save(solution);
		} catch (Exception e) {
			logger.error("An error has occurred while saving a solution", e);
		} finally {
			logger.debug("End saving solution");
		}
	}

}