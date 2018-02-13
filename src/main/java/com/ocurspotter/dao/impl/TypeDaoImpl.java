package com.ocurspotter.dao.impl;

import com.ocurspotter.dao.TypeDao;
import com.ocurspotter.model.Type;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TypeDaoImpl implements TypeDao {

	private static Log logger = LogFactory.getLog(TypeDaoImpl.class);

	@Autowired
	private SessionFactory sessionFactory;

	/**
	 * Save.
	 *
	 * @param type the type
	 */
	public void save(Type type) {
		logger.debug("Start saving the type");
		try {
			sessionFactory.getCurrentSession().save(type);
		} catch (Exception e) {
			logger.error("An error has occurred while saving a type", e);
		} finally {
			logger.debug("End saving type");
		}
	}

}