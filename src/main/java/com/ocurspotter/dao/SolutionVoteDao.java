package com.ocurspotter.dao;

import com.ocurspotter.model.SolutionVote;

import java.util.List;

public interface SolutionVoteDao {
        /**
         * Save.
         *
         * @param solution the solution
         */
        void save(SolutionVote solution);

        /**
         * Get by id.
         *
         * @param id the solution vote id
         */
        SolutionVote getById(Long id);

        /**
         * Gets the all.
         *
         * @return the all
         */
        List<SolutionVote> getAll();

        /**
         * Find downvotes by solution.
         *
         * @param id the solution id
         * @return the solution
         */
        Long getDownvotesBySolution(Long id);

        /**
         * Find upvotes by solution.
         *
         * @param id the solution id
         * @return the solution
         */
        Long getUpvotesBySolution(Long id);
}