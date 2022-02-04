package org.lucanscr.cachedemo.controller;

import org.lucanscr.cachedemo.model.User;
import org.lucanscr.cachedemo.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserService userService;

    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id) {
        log.info(">> User Controller: get user by id: " + id);
        return userService.getById(id);
    }

    @PostMapping
    public User addUser(@RequestBody User user) {
        log.info(">> User Controller: save new user: " + user.toString());
        return userService.create(user);
    }

    @PutMapping
    public User updateUser(@RequestBody User user) {
        log.info(">> User Controller: update user: " + user.toString());
        return userService.update(user);
    }

    @DeleteMapping("/{id}")
    public void removeUser(@PathVariable Long id) {
        log.info(">> User Controller: delete user: " + id);
        userService.delete(id);
    }

}