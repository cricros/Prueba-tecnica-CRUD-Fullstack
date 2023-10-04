package com.SpringCourse.demo.models;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.jpa.repository.Temporal;

import javax.persistence.*;
import java.util.Date;

// declaring a model for a new user using name, lastName, username, password and date as fields

@Entity
@Table(name="tbl_user")
@ToString @EqualsAndHashCode
public class User {

    @Id // declaring PK
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter @Column(name = "id_user") //the column must be declared equal to the column name in the bd
    private Long id_user;

    @Getter @Setter @Column(name = "name")
    private String name;

    @Getter @Setter @Column(name = "last_name")
    private String lastName;

    @Getter @Setter @Column(name = "username")
    private String username;

    @Getter @Setter @Column(name = "password")
    private String password;

    @Getter @Setter @Column(name = "creation_date", updatable = false)
    private Date creation_date = new Date();

}
