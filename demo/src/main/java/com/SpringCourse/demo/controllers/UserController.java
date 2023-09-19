package com.SpringCourse.demo.controllers;

import com.SpringCourse.demo.dao.UserDao;
import com.SpringCourse.demo.models.User;
import com.SpringCourse.demo.utils.JWTUtil;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {

    @Autowired // instacia objetos pero comparten memoria
    private UserDao userDao;

    @Autowired
    private JWTUtil jwtUtil;

    private boolean validationToken(String token){
        String userID = jwtUtil.getKey(token);
        return userID != null;
    }
    @RequestMapping(value = "prueba")
    // para agregar un nuevo endpoint se necesita @requestMapping y el valor del endpoint
    public String test(){
        return "prueba";
    }

    @RequestMapping(value = "json")
    public List<String> json(){
        return List.of("Manzana", "Perrito", "Perro");
    }

    // declaring an endpoint with params
    @RequestMapping(value = "getUser/{id}")
    public User getUser(@PathVariable Long id){
        User user = new User();
        user.setId(id);
        user.setName("Pollito");
        user.setLastName("En fuga");
        user.setEmail("pollitoenfuga@gmail.com");
        user.setTelephone(667687565);
        return user;
    }
    // get all users
    @RequestMapping(value = "api/getUsers", method = RequestMethod.GET)
    public List<User> getUsers(@RequestHeader(value = "Authorization") String token){
        if (!validationToken(token)){ return null;}
        return userDao.getUsers();
    }

    @RequestMapping(value = "api/newUser", method = RequestMethod.POST)
    public void newUser(@RequestBody User user){
        // implementing hash to password and saving it in bd
        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        String hashPwd = argon2.hash(1,1024,1, user.getPassword());
        user.setPassword(hashPwd);
        userDao.createUser(user);
    }

    @RequestMapping(value = "api/deleteUser/{id}", method = RequestMethod.DELETE)
    public void deleteUser(@RequestHeader(value = "Authorization") String token,
                           @PathVariable Long id){
        if (!validationToken(token)){ return;}
        userDao.deleteUser(id);

    }
}
