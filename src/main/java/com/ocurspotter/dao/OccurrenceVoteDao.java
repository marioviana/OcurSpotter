package com.ocurspotter.dao;

import com.ocurspotter.model.OccurrenceVote;
import com.ocurspotter.rest.dto.OccurrenceVoteBean;

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
         * Get by user id and occurrence id.
         *  @param userId the user id
         * @param occurrenceId the occurrence id
         */
        Boolean getByPair(Long userId, Long occurrenceId);

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

        /**
         * Delete occurrence vote
         *
         * @param userId the user id
         * @param occurrenceId the occurrence id
         */
        void delete(Long userId, Long occurrenceId);

        /** Count occurrence votes
	     *
	     * @return the count
	     */
        Long count();
}