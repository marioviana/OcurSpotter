package com.ocurspotter.rest.controller;

import com.ocurspotter.dao.UserDao;
import com.ocurspotter.model.User;
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
 * Created by marioferreira on 17/02/2018.
 */
@RestController
public class UserController {

    private static Logger logger = Logger.getLogger(TypeController.class);

    /** The user dao. */
    @Autowired
    private UserDao userDao;

    @RequestMapping(path="/users", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserBean> getAllTypes() {
        logger.info("REST - Getting all users");
        try {
            List<User> users = this.userDao.getAll();
            List<UserBean> userBeans = new ArrayList<>();
            for (User user : users){
                userBeans.add(new UserBean(user.getId(), user.getUsername(), user.getFirstName(), user.getLastName(), user.getAvatar()));
            }
            return userBeans;
        } catch (Exception e) {
            logger.info("REST - Error getting all users", e);
        } finally {
            logger.info("REST - End of getting all users");
        }
        return null;
    }

    @RequestMapping(path="/users/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public UserBean getUserById(@PathVariable(value = "id") Long id) {
        logger.info("REST - Getting user with id: " + id);
        try {
            User user = this.userDao.getById(id);
            return new UserBean(user.getId(), user.getUsername(), user.getFirstName(), user.getLastName(), user.getAvatar());
        } catch (Exception e) {
            logger.info("REST - Error getting the user", e);
        } finally {
            logger.info("REST - End of getting the user");
        }
        return null;
    }
}
