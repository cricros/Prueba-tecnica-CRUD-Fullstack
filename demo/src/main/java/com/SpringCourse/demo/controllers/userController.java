package com.SpringCourse.demo.controllers;

import com.SpringCourse.demo.models.User;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class userController {
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

    @RequestMapping(value = "editUser")
    public User editUser(){
        User user = new User();
        user.setName("Pollito");
        user.setLastName("En fuga");
        user.setEmail("pollitoenfuga@gmail.com");
        user.setTelephone(667687565);
        return user;
    }

    @RequestMapping(value = "deleteUser")
    public User deleteUser(){
        User user = new User();
        user.setName("Pollito");
        user.setLastName("En fuga");
        user.setEmail("pollitoenfuga@gmail.com");
        user.setTelephone(667687565);
        return user;
    }

    @RequestMapping(value = "searchUser")
    public User searchUser(){
        User user = new User();
        user.setName("Pollito");
        user.setLastName("En fuga");
        user.setEmail("pollitoenfuga@gmail.com");
        user.setTelephone(667687565);
        return user;
    }
}
