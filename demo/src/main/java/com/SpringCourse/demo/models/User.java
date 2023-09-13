package com.SpringCourse.demo.models;


import javax.persistence.Entity;
import javax.persistence.Table;

// declaring a model for a new user using name, lastName, email, telephone number and password as fields
@Entity
@Table(name="users")
public class User {

    private Long id;
    private String name;
    private String lastName;
    private String email;
    private Integer telephone;
    private String password;

    // creating setters and getters

    // getter
    public Long getId() {
        return id;
    }
    // setter
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getTelephone() {
        return telephone;
    }

    public void setTelephone(Integer telephone) {
        this.telephone = telephone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
