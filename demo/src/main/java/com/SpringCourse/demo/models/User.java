package com.SpringCourse.demo.models;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

// declaring a model for a new user using name, lastName, email, telephone number and password as fields
@Entity
@Table(name="users")
@ToString @EqualsAndHashCode
public class User {

    @Id // declaring PK
    @Getter @Setter @Column(name = "id") //the column must be declared equal to the column name in the bd
    private Long id;

    @Getter @Setter @Column(name = "name")
    private String name;

    @Getter @Setter @Column(name = "last_name")
    private String lastName;

    @Getter @Setter @Column(name = "email")
    private String email;

    @Getter @Setter @Column(name = "telephone")
    private Integer telephone;

    @Getter @Setter @Column(name = "password")
    private String password;

}
