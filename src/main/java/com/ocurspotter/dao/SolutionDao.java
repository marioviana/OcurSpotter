package com.ocurspotter.dao;

import com.ocurspotter.model.Solution;

import java.util.List;

public interface SolutionDao {

    /**
     * Save.
     *
     * @param solution the solution
     */
    void save(Solution solution);

    /**
     * Get by id.
     *
     * @param id the solution id
     */
    Solution getById(Long id);

    /**
     * Gets the all.
     *
     * @return the all
     */
    List<Solution> getAll();

    /**
     * Get solutions by occurrence.
     *
     * @paramm id the occurrence id
     * @return the solutions
     */
    List<Solution> getByOccurrence(Long id);

    /** Count occurrences
	 *
	 * @return the count
     */
    Long count();
}