package com.ocurspotter.dao.impl;

import com.ocurspotter.dao.SolutionVoteDao;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class SolutionVoteDaoImpl implements SolutionVoteDao {

	private static Log logger = LogFactory.getLog(SolutionVoteDaoImpl.class);

	@Autowired
	private SessionFactory sessionFactory;

}