package com.ocurspotter.rest.controller;

import com.ocurspotter.dao.UserDao;
import com.ocurspotter.model.User;
import com.ocurspotter.rest.dto.UserBean;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.codec.binary.Base64;

/**
 * Created by marioferreira on 17/02/2018.
 */
@RestController
public class UserController {

    private static Logger logger = Logger.getLogger(TypeController.class);

    /** The user dao. */
    @Autowired
    private UserDao userDao;

    @RequestMapping(path="/login/{auth}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserBean> getUserInAuth(@PathVariable(value = "auth") String auth) {
        logger.info("REST - Getting user");
        try {
            String field = new String(Base64.decodeBase64(auth), StandardCharsets.UTF_8);
            String username = field.substring(0, field.indexOf(':'));
            PasswordEncoder encoder = new BCryptPasswordEncoder();
            String password = field.substring(field.indexOf(':')+1, field.length());
            User user = this.userDao.getByUsername(username);
            if (encoder.matches(password, user.getPassword())) return new ResponseEntity<UserBean>(new UserBean(user.getId(), user.getUsername(), user.getFirstName(), user.getLastName(), user.getAvatar()), HttpStatus.OK);
            else return null;
        } catch (Exception e) {
            logger.info("REST - Error getting the user", e);
        } finally {
            logger.info("REST - End of getting the user");
        }
        return new ResponseEntity<UserBean>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @RequestMapping(path="/users", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<UserBean>> getAllTypes() {
        logger.info("REST - Getting all users");
        try {
            List<User> users = this.userDao.getAll();
            List<UserBean> userBeans = new ArrayList<>();
            for (User user : users){
                userBeans.add(new UserBean(user.getId(), user.getUsername(), user.getFirstName(), user.getLastName(), user.getAvatar()));
            }
            return new ResponseEntity<List<UserBean>>(userBeans, HttpStatus.OK);
        } catch (Exception e) {
            logger.info("REST - Error getting all users", e);
        } finally {
            logger.info("REST - End of getting all users");
        }
        return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @RequestMapping(path="/users/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserBean> getUserById(@PathVariable(value = "id") Long id) {
        logger.info("REST - Getting user with id: " + id);
        try {
            User user = this.userDao.getById(id);
            return new ResponseEntity<UserBean>(new UserBean(user.getId(), user.getUsername(), user.getFirstName(), user.getLastName(), user.getAvatar()), HttpStatus.OK);
        } catch (Exception e) {
            logger.info("REST - Error getting the user", e);
        } finally {
            logger.info("REST - End of getting the user");
        }
        return new ResponseEntity<UserBean>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
