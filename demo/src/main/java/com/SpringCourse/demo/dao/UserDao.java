package com.SpringCourse.demo.dao;

import com.SpringCourse.demo.models.User;

import java.util.List;



// una es aquella que define métodos abstractos únicamente, no se declara alguna funcionalidad, solo el qué debe hacer dicho método

public interface UserDao {
    List<User> getUsers();

    void deleteUser(Long id);

    void createUser(User user);

    Boolean isUsernameNotExist(User user);

    User getCredentials(User user);

    List<User> getUser(Long id);

    Integer updateUser(User user, Long id);
}
