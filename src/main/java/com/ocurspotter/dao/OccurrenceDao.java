package com.ocurspotter.dao;

import com.ocurspotter.model.Occurrence;

import java.util.List;

public interface OccurrenceDao {

	/**
	 * Save.
	 *
	 * @param occurrence the occurrence
	 */
	void save(Occurrence occurrence);

	/**
	 * Gets the all.
	 *
	 * @return the all
	 */
	List<Occurrence> getAll();
}