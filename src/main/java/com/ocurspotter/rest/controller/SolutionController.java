package com.ocurspotter.rest.controller;

import com.ocurspotter.dao.*;
import com.ocurspotter.model.Solution;
import com.ocurspotter.model.User;
import com.ocurspotter.rest.dto.SolutionBean;
import com.ocurspotter.rest.dto.UserBean;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by marioferreira on 13/02/2018.
 */
@RestController
public class SolutionController {

    private static Logger logger = Logger.getLogger(SolutionController.class);

    /** The solution dao. */
    @Autowired
    private SolutionDao solutionDao;

    /** The user dao. */
    @Autowired
    private UserDao userDao;

    /** The occurrence dao. */
    @Autowired
    private OccurrenceDao occurrenceDao;

    /** The type dao. */
    @Autowired
    private TypeDao typeDao;

    /** The solution vote dao. */
    @Autowired
    private SolutionVoteDao solutionVoteDao;

    @RequestMapping(path="/solutions", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<SolutionBean>> getAllSolutions() {
        logger.info("REST - Getting all the solutions:");
        try {
            List<Solution> solutions = this.solutionDao.getAll();
            List<SolutionBean> restSolutions = new ArrayList<>();
            for (Solution solution : solutions) {
                User user = this.userDao.getBySolution(solution.getId());
                UserBean userBean = new UserBean(user.getId(), user.getUsername(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getAvatar());
                Long upvotes = this.solutionVoteDao.getUpvotesBySolution(solution.getId());
                Long downvotes = this.solutionVoteDao.getDownvotesBySolution(solution.getId());
                SolutionBean solutionBean = new SolutionBean(solution.getId(), solution.getDescription(), solution.getOpenDate(), solution.getDeadline(), solution.getValue(), solution.getChoosed(), solution.getStatus(), userBean, upvotes, downvotes);
                restSolutions.add(solutionBean);
            }
            return new ResponseEntity<List<SolutionBean>>(restSolutions, HttpStatus.OK);
        } catch (Exception e) {
            logger.info("REST - Error getting all the solutions", e);
        } finally {
            logger.info("REST - End of getting all the solutions");
        }
        return new ResponseEntity<List<SolutionBean>>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @RequestMapping(path="/solutions/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SolutionBean> getSolutionById(@PathVariable(value = "id") Long id) {
        logger.info("REST - Getting solution by id: " + id);
        try {
            Solution solution = this.solutionDao.getById(id);
            User user = this.userDao.getBySolution(id);
            UserBean userBean = new UserBean(user.getId(), user.getUsername(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getAvatar());
            Long upvotes = this.solutionVoteDao.getUpvotesBySolution(id);
            Long downvotes = this.solutionVoteDao.getDownvotesBySolution(id);
            SolutionBean solutionBean = new SolutionBean(solution.getId(), solution.getDescription(), solution.getOpenDate(), solution.getDeadline(), solution.getValue(), solution.getChoosed(), solution.getStatus(), userBean, upvotes, downvotes);
            return new ResponseEntity<SolutionBean>(solutionBean, HttpStatus.OK);
        } catch (Exception e) {
            logger.info("REST - Error getting the solution", e);
        } finally {
            logger.info("REST - End of getting the solution");
        }
        return new ResponseEntity<SolutionBean>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @RequestMapping(path="/solutions/new", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Integer> newSolution(@RequestParam("description") String description,
                                                 @RequestParam("author") Long author,
                                                 @RequestParam("value") Double value,
                                                 @RequestParam("deadline") Long deadLine,
                                                 @RequestParam("occurrence") Long occurrence) {
        logger.info("REST - Post solution: ");
        try {
            Solution solution = new Solution(description, this.userDao.getById(author), value, new Date(deadLine), this.occurrenceDao.getById(occurrence));
            this.solutionDao.save(solution);
            return new ResponseEntity<Integer>(200, HttpStatus.OK);
        } catch (Exception e) {
            logger.info("REST - Error post the solution", e);
        } finally {
            logger.info("REST - End of post the solution");
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
