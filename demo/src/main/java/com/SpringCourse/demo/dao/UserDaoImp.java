package com.SpringCourse.demo.dao;

import com.SpringCourse.demo.models.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional
public class UserDaoImp implements UserDao{

    @PersistenceContext
    // para la conexion a la base de datos
    EntityManager entityManager;
    @Override
    @Transactional
    public List<User> getUsers() {
        // sobreescribiendo el metodo abstracto para realice algo
        String query = "FROM User";
        return entityManager.createQuery(query).getResultList();
    }

    @Override
    public void deleteUser(Long id) {
        User user = entityManager.find(User.class, id);
        entityManager.remove(user);
    }

    @Override
    public void createUser(User user) {
        // merge es utilizado para mandar informacion a la bd
        entityManager.merge(user);
    }
}
