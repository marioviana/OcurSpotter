package com.ocurspotter.rest.controller;

import com.ocurspotter.dao.*;
import com.ocurspotter.model.*;
import com.ocurspotter.rest.dto.OccurrenceBean;
import com.ocurspotter.rest.dto.SolutionBean;
import com.ocurspotter.rest.dto.TypeBean;
import com.ocurspotter.rest.dto.UserBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

/**
 * Created by marioferreira on 13/02/2018.
 */
@RestController
public class OccurrenceController {

    private static Logger logger = Logger.getLogger(OccurrenceController.class);

    /** The occurrence dao. */
    @Autowired
    private OccurrenceDao occurrenceDao;

    /** The user dao. */
    @Autowired
    private UserDao userDao;

    /** The type dao. */
    @Autowired
    private TypeDao typeDao;

    /** The occurrence vote dao. */
    @Autowired
    private OccurrenceVoteDao occurrenceVoteDao;

    /** The solution dao. */
    @Autowired
    private SolutionDao solutionDao;

    /** The solution vote dao. */
    @Autowired
    private SolutionVoteDao solutionVoteDao;

    @RequestMapping(path="/occurrences", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<OccurrenceBean> getAllOccurrences(@RequestParam(required = false, value = "type") Long[] type,
                                                  @RequestParam(required = false, value = "user") Long[] userId,
                                                  @RequestParam(required = false, value = "sug") Integer suggestion,
                                                  @RequestParam(required = false, value = "keyWord") String keyWord,
                                                  @RequestParam(required = false, value = "status") Integer status,
                                                  @RequestParam(required = false, value = "page") String page) {
        logger.info("REST - Getting all the occurrences:");
        try {
            List<Occurrence> occurrences = this.occurrenceDao.getAll(type, userId, suggestion, keyWord, status);
            List<OccurrenceBean> restOccurrences = new ArrayList<>();
            for (Occurrence occurrence : occurrences) {
                User user = userDao.getByOccurrence(occurrence.getId());
                UserBean userBean =
                        new UserBean(user.getId(), user.getUsername(), user.getFirstName(), user.getLastName(), user.getAvatar());
                Type typeOcc = typeDao.getByOccurrence(occurrence.getId());
                TypeBean typeBean = new TypeBean(typeOcc.getId(), typeOcc.getName(), typeOcc.getDescription());
                Long upvotes = this.occurrenceVoteDao.getUpvotesBySolution(occurrence.getId());
                Long downvotes = this.occurrenceVoteDao.getDownvotesBySolution(occurrence.getId());
                List<Solution> solutions = this.solutionDao.getByOccurrence(occurrence.getId());
                List<SolutionBean> solutionBeans = new ArrayList<>();
                for (Solution solution : solutions) {
                    User userSolution = this.userDao.getBySolution(solution.getId());
                    UserBean userBeanSolution = new UserBean(userSolution.getId(), userSolution.getUsername(), userSolution.getFirstName(), userSolution.getLastName(), userSolution.getAvatar());
                    Long upvotesSolution = this.solutionVoteDao.getUpvotesBySolution(solution.getId());
                    Long downvotesSolution = this.solutionVoteDao.getDownvotesBySolution(solution.getId());
                    SolutionBean solutionBean = new SolutionBean(solution.getId(), solution.getDescription(), solution.getOpenDate(), solution.getDeadline(), solution.getValue(), solution.getChoosed(), solution.getStatus(), userBeanSolution, upvotesSolution, downvotesSolution);
                    solutionBeans.add(solutionBean);
                }
                OccurrenceBean occurrenceBean = new OccurrenceBean(occurrence.getId(), occurrence.getTitle(), occurrence.getDescription(),
                        occurrence.getStatus(), occurrence.getOpenDate(), occurrence.getCloseDate(), occurrence.getLatitude(), occurrence.getLongitude(), occurrence.getSuggestion(),
                        occurrence.getImage(), userBean, typeBean, upvotes, downvotes, solutionBeans);
                restOccurrences.add(occurrenceBean);
            }

            final int records = restOccurrences.size();

            if (page != null) {
                final List<OccurrenceBean> resPage = new ArrayList<>();
                final int p = Integer.parseInt(page);
                final int nTemps = p * 10;
                if (nTemps == 0 && records > 10) {
                    for (int i = nTemps; i < 10; i++) {
                        final OccurrenceBean temp = restOccurrences.get(i);
                        resPage.add(temp);
                    }
                    return resPage;
                } else if (records - nTemps > 10) {
                    for (int i = nTemps; i < nTemps + 10; i++) {
                        final OccurrenceBean temp = restOccurrences.get(i);
                        resPage.add(temp);
                    }
                    return resPage;
                } else {
                    for (int i = nTemps; i < records; i++) {
                        final OccurrenceBean temp = restOccurrences.get(i);
                        resPage.add(temp);
                    }
                    return resPage;
                }
            } else {
                return restOccurrences;
            }
        } catch (Exception e) {
            logger.info("REST - Error getting all the occurrences", e);
        } finally {
            logger.info("REST - End of getting all the occurrences");
        }
        return null;
    }

    @RequestMapping(path="/occurrences/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public OccurrenceBean getOccurrenceById(@PathVariable(value = "id") Long id) {
        logger.info("REST - Getting occurrence by id: " + id);
        try {
            Occurrence occurrence = this.occurrenceDao.getById(id);
            User user = this.userDao.getByOccurrence(occurrence.getId());
            UserBean userBean =
                    new UserBean(user.getId(), user.getUsername(), user.getFirstName(), user.getLastName(), user.getAvatar());
            Type type = this.typeDao.getByOccurrence(id);
            TypeBean typeBean = new TypeBean(type.getId(), type.getName(), type.getDescription());
            Long upvotes = this.occurrenceVoteDao.getUpvotesBySolution(occurrence.getId());
            Long downvotes = this.occurrenceVoteDao.getDownvotesBySolution(occurrence.getId());
            List<Solution> solutions = this.solutionDao.getByOccurrence(id);
            List<SolutionBean> solutionBeans = new ArrayList<>();
            for (Solution solution : solutions) {
                User userSolution = this.userDao.getBySolution(solution.getId());
                UserBean userBeanSolution = new UserBean(userSolution.getId(), userSolution.getUsername(), userSolution.getFirstName(), userSolution.getLastName(), userSolution.getAvatar());
                Long upvotesSolution = this.solutionVoteDao.getUpvotesBySolution(solution.getId());
                Long downvotesSolution = this.solutionVoteDao.getDownvotesBySolution(solution.getId());
                SolutionBean solutionBean = new SolutionBean(solution.getId(), solution.getDescription(), solution.getOpenDate(), solution.getDeadline(), solution.getValue(), solution.getChoosed(), solution.getStatus(), userBeanSolution, upvotesSolution, downvotesSolution);
                solutionBeans.add(solutionBean);
            }
            OccurrenceBean occurrenceBean = new OccurrenceBean(occurrence.getId(), occurrence.getTitle(), occurrence.getDescription(),
                    occurrence.getStatus(), occurrence.getOpenDate(), occurrence.getCloseDate(), occurrence.getLatitude(), occurrence.getLongitude(), occurrence.getSuggestion(),
                    occurrence.getImage(), userBean, typeBean, upvotes, downvotes, solutionBeans);
            return occurrenceBean;
        } catch (Exception e) {
            logger.info("REST - Error getting the occurrence", e);
        } finally {
            logger.info("REST - End of getting the occurrence");
        }
        return null;
    }

    @RequestMapping(path="/occurrences/{id}/solutions", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<SolutionBean> getSolutionsByOccurrence(@PathVariable(value = "id") Long id) {
        logger.info("REST - Getting solutions by occurrence: " + id);
        try {
            List<Solution> solutions = this.solutionDao.getByOccurrence(id);
            List<SolutionBean> solutionBeans = new ArrayList<>();
            for (Solution solution : solutions) {
                User userSolution = this.userDao.getBySolution(solution.getId());
                UserBean userBeanSolution = new UserBean(userSolution.getId(), userSolution.getUsername(), userSolution.getFirstName(), userSolution.getLastName(), userSolution.getAvatar());
                Long upvotesSolution = this.solutionVoteDao.getUpvotesBySolution(solution.getId());
                Long downvotesSolution = this.solutionVoteDao.getDownvotesBySolution(solution.getId());
                SolutionBean solutionBean = new SolutionBean(solution.getId(), solution.getDescription(), solution.getOpenDate(), solution.getDeadline(), solution.getValue(), solution.getChoosed(), solution.getStatus(), userBeanSolution, upvotesSolution, downvotesSolution);
                solutionBeans.add(solutionBean);
            }
            return solutionBeans;
        } catch (Exception e) {
            logger.info("REST - Error getting the solutions by occurrence", e);
        } finally {
            logger.info("REST - End of getting the solutions by occurrence");
        }
        return null;
    }
}
