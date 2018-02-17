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
	 * @param type the types
	 * @param userId the user id
	 * @param suggestion the suggestion
	 * @param keyWord the key word
	 * @return the all
	 */
	List<Occurrence> getAll(Long[] type, Long[] userId, Integer suggestion, String keyWord, Integer status);

	/**
	 * Find by solution.
	 *
	 * @param id the solution id
	 * @return the occurrence
	 */
	Occurrence getBySolution(Long id);
}