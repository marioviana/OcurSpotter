package com.ocurspotter.rest.controller;

import com.ocurspotter.dao.*;
import com.ocurspotter.model.Type;
import com.ocurspotter.model.User;
import com.ocurspotter.model.UserRole;
import com.ocurspotter.rest.dto.StatsBean;
import com.ocurspotter.rest.dto.UserBean;
import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by marioferreira on 17/02/2018.
 */
@RestController
public class StatsController {

    private static Logger logger = Logger.getLogger(TypeController.class);

    /** The user dao. */
    @Autowired
    private UserDao userDao;

    /** The user dao. */
    @Autowired
    private UserRoleDao userRoleDao;

    /** The type dao. */
    @Autowired
    private TypeDao typeDao;

    /** The soionlut dao. */
    @Autowired
    private SolutionDao solutionDao;

    /** The solution vote dao. */
    @Autowired
    private SolutionVoteDao solutionVoteDao;

    /** The occurrence dao. */
    @Autowired
    private OccurrenceDao occurrenceDao;

    /** The occurrence vote dao. */
    @Autowired
    private OccurrenceVoteDao occurrenceVoteDao;

    @RequestMapping(path="/stats", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StatsBean> getStats() {
        logger.info("REST - Getting stats");
        try {
            Long users = this.userDao.count();
            Long types = this.typeDao.count();
            Long occurrences = this.occurrenceDao.count();
            Long occurrenceVotes = this.occurrenceVoteDao.count();
            Long solutions = this.solutionDao.count();
            Long solutionVotes = this.solutionVoteDao.count();
            StatsBean statsBean = new StatsBean(occurrences, solutions, users, types, occurrenceVotes, solutionVotes);
            return new ResponseEntity<>(statsBean, HttpStatus.OK);
        } catch (Exception e) {
            logger.info("REST - Error getting stats", e);
        } finally {
            logger.info("REST - End of getting stats");
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
