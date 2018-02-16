package com.ocurspotter.rest.controller;

import com.ocurspotter.dao.SolutionDao;
import com.ocurspotter.dao.SolutionVoteDao;
import com.ocurspotter.dao.UserDao;
import com.ocurspotter.dao.impl.SolutionVoteDaoImpl;
import com.ocurspotter.model.Solution;
import com.ocurspotter.model.SolutionVote;
import com.ocurspotter.model.User;
import com.ocurspotter.rest.bean.SolutionBean;
import com.ocurspotter.rest.bean.UserBean;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
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

    /** The solution vote dao. */
    @Autowired
    private SolutionVoteDao solutionVoteDao;

    @RequestMapping(path="/solutions", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<SolutionBean> getAllSolutions() {
        logger.info("REST - Getting all the solutions:");
        try {
            List<Solution> solutions = this.solutionDao.getAll();
            List<SolutionBean> restSolutions = new ArrayList<>();
            for (Solution solution : solutions) {
                User user = this.userDao.getBySolution(solution.getId());
                UserBean userBean = new UserBean(user.getId(), user.getUsername(), user.getFirstName(), user.getLastName(), user.getAvatar());
                Long upvotes = this.solutionVoteDao.getUpvotesBySolution(solution.getId());
                Long downvotes = this.solutionVoteDao.getDownvotesBySolution(solution.getId());
                SolutionBean solutionBean = new SolutionBean(solution.getId(), solution.getDescription(), solution.getOpenDate(), solution.getDeadline(), solution.getValue(), solution.getChoosed(), solution.getStatus(), userBean, upvotes, downvotes);
                restSolutions.add(solutionBean);
            }
            return restSolutions;
        } catch (Exception e) {
            logger.info("REST - Error getting all the solutions", e);
        } finally {
            logger.info("REST - End of getting all the solutions");
        }
        return null;
    }

    @RequestMapping(path="/solutions/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public SolutionBean getSolutionById(@PathVariable(value = "id") Long id) {
        logger.info("REST - Getting solution by id:" + id);
        try {
            Solution solution = this.solutionDao.getById(id);
            User user = this.userDao.getBySolution(id);
            UserBean userBean = new UserBean(user.getId(), user.getUsername(), user.getFirstName(), user.getLastName(), user.getAvatar());
            Long upvotes = this.solutionVoteDao.getUpvotesBySolution(id);
            Long downvotes = this.solutionVoteDao.getDownvotesBySolution(id);
            SolutionBean solutionBean = new SolutionBean(solution.getId(), solution.getDescription(), solution.getOpenDate(), solution.getDeadline(), solution.getValue(), solution.getChoosed(), solution.getStatus(), userBean, upvotes, downvotes);
            return solutionBean;
        } catch (Exception e) {
            logger.info("REST - Error getting the solution", e);
        } finally {
            logger.info("REST - End of getting the solution");
        }
        return null;
    }
}
