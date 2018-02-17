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
	 * Get by id.
	 *
	 * @param id the occurrence id
	 */
	Occurrence getById(Long id);

	/**
	 * Gets the all.
	 *
	 * @return the all
	 */
	List<Occurrence> getAll(Long[] type, Long[] userId, Integer suggestion, String keyWord);

	/**
	 * Find by solution.
	 *
	 * @param id the solution id
	 * @return the occurrence
	 */
	Occurrence getBySolution(Long id);
}