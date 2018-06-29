package com.ocurspotter.rest.controller;

import com.ocurspotter.dao.OccurrenceDao;
import com.ocurspotter.dao.OccurrenceVoteDao;
import com.ocurspotter.dao.UserDao;
import com.ocurspotter.model.Occurrence;
import com.ocurspotter.model.OccurrenceVote;
import com.ocurspotter.model.User;
import com.ocurspotter.rest.dto.OccurrenceVoteBean;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Response;

/**
 * Created by marioferreira on 17/02/2018.
 */
@RestController
public class OccurrenceVoteController {

    private static Logger logger = Logger.getLogger(OccurrenceVoteController.class);

    /** The occurrence vote dao. */
    @Autowired
    private OccurrenceVoteDao occurrenceVoteDao;

    /** The type dao. */
    @Autowired
    private OccurrenceDao occurrenceDao;

    /** The user dao. */
    @Autowired
    private UserDao userDao;

    @RequestMapping(path="/occurrenceVotes/new", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Integer> newVote(@RequestParam("user") Long userId,
                                           @RequestParam("occurrence") Long occurrenceId,
                                           @RequestParam("vote") Boolean vote,
                                           @RequestParam("exists") Boolean exists) {
        logger.info("REST - Post occurrence vote: ");
        try {
            if (exists) {
                this.occurrenceVoteDao.delete(userId, occurrenceId);
            }
            Occurrence occurrence = occurrenceDao.getById(occurrenceId);
            User user = userDao.getById(userId);
            OccurrenceVote occurrenceVote = new OccurrenceVote(occurrence, user, vote);
            this.occurrenceVoteDao.save(occurrenceVote);
            return new ResponseEntity<Integer>(200, HttpStatus.OK);
        } catch (Exception e) {
            logger.info("REST - Error post the occurrence vote", e);
        } finally {
            logger.info("REST - End of post the occurrence vote");
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @RequestMapping(path="/occurrenceVotes/pair", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> getPair(@RequestParam("user") Long userId,
                                           @RequestParam("occurrence") Long occurrenceId) {
        logger.info("REST - Get occurrence vote: ");
        try {
            Boolean occurrenceVote = this.occurrenceVoteDao.getByPair(userId, occurrenceId);
            return new ResponseEntity<>(occurrenceVote, HttpStatus.OK);
        } catch (Exception e) {
            logger.info("REST - Error get the occurrence vote", e);
        } finally {
            logger.info("REST - End of get the occurrence vote");
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
