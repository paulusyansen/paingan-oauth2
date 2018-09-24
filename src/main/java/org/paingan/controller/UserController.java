package org.paingan.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.paingan.model.User;
import org.paingan.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;



import java.security.Principal;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value="/user")
    public @ResponseBody List<User> listUser(){
        return userService.findAll();
    }
    
    @GetMapping("/user32")
    public  ModelAndView listUser32(){
    	List<User> userList = userService.findAll();
    	ModelAndView mv = new ModelAndView();
    	mv.addObject("docs", userList);
    	mv.setViewName("user");
        return mv;
    }

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public User create(@RequestBody User user){
        return userService.save(user);
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
    public String delete(@PathVariable(value = "id") Long id){
        userService.delete(id);
        return "success";
    }
    
    @GetMapping("/authenticate")
    public ResponseEntity<Principal> user(Principal user) {
     return ResponseEntity.<Principal>ok(user);
    }
    
    @GetMapping("/ok")
    public ResponseEntity<List<JSONObject>> listData() {
    	List<User> userList = userService.findAll();
    	
    	List<JSONObject> list = new ArrayList<JSONObject>();
    	for (User user : userList) {
			JSONObject obj = new JSONObject();
			try {
				obj.put("user", user);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			list.add(obj);
		}
    	
     return new ResponseEntity<List<JSONObject>>(list, HttpStatus.OK);
    }

}
