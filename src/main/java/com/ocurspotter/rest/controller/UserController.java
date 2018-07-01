package com.ocurspotter.rest.controller;

import com.ocurspotter.dao.TypeDao;
import com.ocurspotter.dao.UserDao;
import com.ocurspotter.dao.UserRoleDao;
import com.ocurspotter.model.Type;
import com.ocurspotter.model.User;
import com.ocurspotter.model.UserRole;
import com.ocurspotter.rest.dto.UserBean;
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

    /** The user dao. */
    @Autowired
    private UserRoleDao userRoleDao;

    /** The type dao. */
    @Autowired
    private TypeDao typeDao;

    /** The b crypt password encoder. */
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @RequestMapping(path="/login/{auth}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserBean> getUserInAuth(@PathVariable(value = "auth") String auth) {
        logger.info("REST - Getting user");
        try {
            String field = new String(Base64.decodeBase64(auth), StandardCharsets.UTF_8);
            String username = field.substring(0, field.indexOf(':'));
            PasswordEncoder encoder = new BCryptPasswordEncoder();
            String password = field.substring(field.indexOf(':')+1, field.length());
            User user = this.userDao.getByUsername(username);
            if (encoder.matches(password, user.getPassword())) return new ResponseEntity<UserBean>(new UserBean(user.getId(), user.getUsername(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getAvatar()), HttpStatus.OK);
            else return null;
        } catch (Exception e) {
            logger.info("REST - Error getting the user", e);
        } finally {
            logger.info("REST - End of getting the user");
        }
        return new ResponseEntity<UserBean>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @RequestMapping(path="/signup", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Integer> postSignup(
            @RequestParam(value = "username") String username,
            @RequestParam(value = "email") String email,
            @RequestParam(value = "password") String password,
            @RequestParam(value = "firstName") String firstName,
            @RequestParam(value = "lastName") String lastName) {
        logger.info("REST - Creating user");
        try {
            final String avatar = "https://upload.wikimedia.org/wikipedia/commons/thumb/9/96/Creative-Tail-People-boy.svg/1024px-Creative-Tail-People-boy.svg.png";
            final String passwordEncoded = bCryptPasswordEncoder.encode(password);
            final Type type = this.typeDao.getById((long) 12);
            Set<Type> types = new HashSet<>();
            types.add(type);
            User user = new User(username, firstName, lastName, passwordEncoded, true, avatar, types, email);
            this.userDao.save(user);
            final UserRole userRole = new UserRole(user, "ROLE_USER");
            final Set<UserRole> userRoles = new HashSet<>();
            userRoles.add(userRole);
            user.setUserRole(userRoles);
            this.userRoleDao.save(userRole);
            return new ResponseEntity<Integer>(HttpStatus.OK);
        } catch (Exception e) {
            logger.info("REST - Error creating user", e);
        } finally {
            logger.info("REST - End of creating the user");
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @RequestMapping(path="/users", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<UserBean>> getAllTypes() {
        logger.info("REST - Getting all users");
        try {
            List<User> users = this.userDao.getAll();
            List<UserBean> userBeans = new ArrayList<>();
            for (User user : users){
                userBeans.add(new UserBean(user.getId(), user.getUsername(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getAvatar()));
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
            return new ResponseEntity<UserBean>(new UserBean(user.getId(), user.getUsername(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getAvatar()), HttpStatus.OK);
        } catch (Exception e) {
            logger.info("REST - Error getting the user", e);
        } finally {
            logger.info("REST - End of getting the user");
        }
        return new ResponseEntity<UserBean>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @RequestMapping(path="/profile", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Integer> postProfile(
            @RequestParam(value = "user") Long userId,
            @RequestParam(value = "email") String email,
            @RequestParam(value = "firstName") String firstName,
            @RequestParam(value = "lastName") String lastName) {
        logger.info("REST - Updating user");
        try {
            User user = this.userDao.getById(userId);
            user.setEmail(email);
            user.setFirstName(firstName);
            user.setLastName(lastName);
            this.userDao.update(user);
            return new ResponseEntity<Integer>(HttpStatus.OK);
        } catch (Exception e) {
            logger.info("REST - Error updating user", e);
        } finally {
            logger.info("REST - End of updating the user");
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @RequestMapping(path="/password", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Integer> postProfile(
            @RequestParam(value = "user") Long userId,
            @RequestParam(value = "oldPassword") String oldPassword,
            @RequestParam(value = "newPassword") String newPassword) {
        logger.info("REST - Updating user");
        try {
            User user = this.userDao.getById(userId);
            String oldPw = new String(Base64.decodeBase64(oldPassword), StandardCharsets.UTF_8);
            String newPw = new String(Base64.decodeBase64(newPassword), StandardCharsets.UTF_8);
            PasswordEncoder encoder = new BCryptPasswordEncoder();
            if (encoder.matches(oldPw, user.getPassword())) {
                String password = encoder.encode(newPw);
                user.setPassword(password);
                this.userDao.update(user);
            }
            return new ResponseEntity<Integer>(HttpStatus.OK);
        } catch (Exception e) {
            logger.info("REST - Error updating user", e);
        } finally {
            logger.info("REST - End of updating the user");
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
