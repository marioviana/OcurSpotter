package com.ocurspotter.dao.impl;

import com.ocurspotter.dao.OccurrenceVoteDao;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class OccurrenceVoteDaoImpl implements OccurrenceVoteDao {

	private static Log logger = LogFactory.getLog(OccurrenceVoteDaoImpl.class);

	@Autowired
	private SessionFactory sessionFactory;

}