package com.SpringCourse.demo.controllers;

import com.SpringCourse.demo.models.User;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
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
    // get all users
    @RequestMapping(value = "getUsers")
    public List<User> getUsers(){

        // creating a userList
        List<User> userList = new ArrayList<>();

        User userOne = new User();
        userOne.setId(1L);
        userOne.setName("Pollito");
        userOne.setLastName("En fuga");
        userOne.setEmail("pollitoenfuga@gmail.com");
        userOne.setTelephone(667687565);

        User userTwo = new User();
        userTwo.setId(2L);
        userTwo.setName("Pollito");
        userTwo.setLastName("En fuga");
        userTwo.setEmail("pollitoenfuga@gmail.com");
        userTwo.setTelephone(667687565);

        User userThree = new User();
        userThree.setId(3L);
        userThree.setName("Pollito");
        userThree.setLastName("En fuga");
        userThree.setEmail("pollitoenfuga@gmail.com");
        userThree.setTelephone(667687565);

        User userFour = new User();
        userFour.setId(4L);
        userFour.setName("Pollito");
        userFour.setLastName("En fuga");
        userFour.setEmail("pollitoenfuga@gmail.com");
        userFour.setTelephone(667687565);

        // adding user to list
        userList.add(userOne);
        userList.add(userTwo);
        userList.add(userThree);
        userList.add(userFour);

        // returning userList
        return userList;
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
