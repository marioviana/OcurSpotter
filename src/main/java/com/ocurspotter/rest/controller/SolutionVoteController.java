package com.ocurspotter.rest.controller;

import com.ocurspotter.dao.*;
import com.ocurspotter.model.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by marioferreira on 17/02/2018.
 */
@RestController
public class SolutionVoteController {

    private static Logger logger = Logger.getLogger(SolutionVoteController.class);

    /** The occurrence vote dao. */
    @Autowired
    private SolutionVoteDao solutionVoteDao;

    /** The type dao. */
    @Autowired
    private SolutionDao solutionDao;

    /** The user dao. */
    @Autowired
    private UserDao userDao;

    @RequestMapping(path="/solutionVotes/new", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Integer> newVote(@RequestParam("user") Long userId,
                                           @RequestParam("solution") Long solutionId,
                                           @RequestParam("vote") Boolean vote,
                                           @RequestParam("exists") Boolean exists) {
        logger.info("REST - Post solution vote: ");
        try {
            if (exists) {
                this.solutionVoteDao.delete(userId, solutionId);
            }
            Solution solution = solutionDao.getById(solutionId);
            User user = userDao.getById(userId);
            SolutionVote solutionVote = new SolutionVote(solution, user, vote);
            this.solutionVoteDao.save(solutionVote);
            return new ResponseEntity<Integer>(200, HttpStatus.OK);
        } catch (Exception e) {
            logger.info("REST - Error post the solution vote", e);
        } finally {
            logger.info("REST - End of post the solution vote");
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @RequestMapping(path="/solutionVotes/pair", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> getPair(@RequestParam("user") Long userId,
                                           @RequestParam("solution") Long solutionId) {
        logger.info("REST - Get solution vote: ");
        try {
            Boolean solutionVote = this.solutionVoteDao.getByPair(userId, solutionId);
            return new ResponseEntity<>(solutionVote, HttpStatus.OK);
        } catch (Exception e) {
            logger.info("REST - Error get the solution vote", e);
        } finally {
            logger.info("REST - End of get the solution vote");
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
