package org.paingan.oauth2.controller;

import java.security.Principal;
import java.util.List;

import org.paingan.oauth2.model.User;
import org.paingan.oauth2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value="/users")
    public @ResponseBody List<User> listUser(){
        return userService.findAll();
    }

    @PostMapping(value = "/user")
    public User create(@RequestBody User user){
        return userService.save(user);
    }

    @DeleteMapping(value = "/user/{id}")
    public String delete(@PathVariable(value = "id") Long id){
        userService.delete(id);
        return "success";
    }
    
    @GetMapping(value = "/user/authenticate")
    public ResponseEntity<Principal> user(Principal user) {
     return ResponseEntity.<Principal>ok(user);
    }
    
    @GetMapping(value = "/user/me")
    public Principal me(Principal principal) {
     return principal;
    }

}
