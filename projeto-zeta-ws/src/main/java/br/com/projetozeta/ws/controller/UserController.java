package br.com.projetozeta.ws.controller;

import br.com.projetozeta.ws.domain.User;
import br.com.projetozeta.ws.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * Created by dekkz on 16/05/17.
 */

@RestController
@RequestMapping("/api/users")
public class UserController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<User>> findAll() {
        logger.info("UserController - Started method findAll");
        Collection<User> users = userService.findAll();
        return new ResponseEntity<Collection<User>>(users, HttpStatus.OK);
    }

    @RequestMapping(value = "/places/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<User>> findUsersByPlace(@PathVariable long id) {
        logger.info("UserController - Started method findUsersByPlace");
        Collection<User> users = userService.findUsersByPlace(id);
        return new ResponseEntity<Collection<User>>(users, HttpStatus.OK);
    }

    @RequestMapping(value = "/tags/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<User>> findUsersByTag(@PathVariable long id) {
        logger.info("UserController - Started method findUsersByTag");
        Collection<User> users = userService.findUsersByTag(id);
        return new ResponseEntity<Collection<User>>(users, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<User> findById(@PathVariable long id) {
        logger.info("UserController - Started method findById");
        User user = userService.findById(id);
        if (user == null)
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<User> save(@RequestBody User user) {
        logger.info("UserController - Started method save");
        User saveUser = userService.save(user);
        return new ResponseEntity<User>(saveUser, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<User> delete(@PathVariable long id) {
        logger.info("UserController - Started method delete");
        userService.delete(id);
        return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<User> update(@PathVariable long id, @RequestBody User user) {
        logger.info("UserController - Started method update with id: " + id);
        User userUpdate = userService.update(user);
        return new ResponseEntity<User>(userUpdate, HttpStatus.OK);
    }
}
