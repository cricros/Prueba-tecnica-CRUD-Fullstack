package com.SpringCourse.demo.controllers;

import com.SpringCourse.demo.dao.UserDao;
import com.SpringCourse.demo.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthLoginController {
    @Autowired // instacia objetos pero comparten memoria
    private UserDao userDao;
    @RequestMapping(value = "api/loginUser", method = RequestMethod.POST)
    public String login(@RequestBody User user){
        if (userDao.checkCredentials(user)){
            return "Ok";
        }
        return "Fail";
    }
}
