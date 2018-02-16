package com.ocurspotter.dao;

import com.ocurspotter.model.OccurrenceVote;

import java.util.List;

public interface OccurrenceVoteDao {

        /**
         * Save.
         *
         * @param occurrence the occurrence
         */
        void save(OccurrenceVote occurrence);

        /**
         * Get by id.
         *
         * @param id the occurrence id
         */
        OccurrenceVote getById(Long id);

        /**
         * Find downvotes by occurrence.
         *
         * @param id the occurrence id
         * @return the occurrence votes
         */
        Long getDownvotesBySolution(Long id);

        /**
         * Find upvotes by occurrence.
         *
         * @param id the occurrence id
         * @return the occurrence votes
         */
        Long getUpvotesBySolution(Long id);
}