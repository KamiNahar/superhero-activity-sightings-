/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings;

import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import sg.dontdiejustkode.superherosightingsgroupwork.services.UserServiceLayer;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import sg.dontdiejustkode.superherosightingsgroupwork.dao.UserDao;
import sg.dontdiejustkode.superherosightingsgroupwork.dto.User;
/**
 *
 * @author kaminahar
 */
@Controller
public class UserController {

    @Inject
    UserDao userDao;
    
    private PasswordEncoder encoder;

    public UserController(UserDao userDao, PasswordEncoder encoder) {
        this.userDao = userDao;
        this.encoder = encoder;
    }

    

     // This endpoint retrieves all users from the database and puts the
    // List of users on the model
    @RequestMapping(value = "/displayUserList", method = RequestMethod.GET)
    public String displayUserList(Map<String, Object> model) {
        List <User> users = userDao.getUserList();
        model.put("users", users);
        return "displayUserList";
    }
    // This endpoint just displays the Add User form

    @RequestMapping(value = "/displayUserForm", method = RequestMethod.GET)
    public String displayUserForm(Map<String, Object> model) {
        return "addUserForm";
    }
    // This endpoint processes the form data and creates a new User

    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public String addUser(HttpServletRequest req) {
        User newUser = new User();
        // This example uses a plain HTML form so we must get values 
        // from the request
        newUser.setUserName(req.getParameter("username"));
        String pwd = req.getParameter("password");
        String encPwd = this.encoder.encode(pwd);
        newUser.setUserPassword(encPwd);
        // All users have ROLE_USER, only add ROLE_ADMIN if the isAdmin 
        // box is checked
        newUser.addAuthority("ROLE_USER");
        if (null != req.getParameter("isAdmin")) {
            newUser.addAuthority("ROLE_ADMIN");
        }

        userDao.addUser(newUser);
        return "redirect:displayUserList";
    }
    // This endpoint deletes the specified User

    @RequestMapping(value = "/deleteUser", method = RequestMethod.GET)
    public String deletUser(@RequestParam("username") String username,
            Map<String, Object> model) {
        
        
       userDao.deleteUser(username);
        return "redirect:displayUserList";
    }  
    
    
}
