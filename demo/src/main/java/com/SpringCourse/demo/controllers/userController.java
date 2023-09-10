package com.SpringCourse.demo.controllers;

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
}
