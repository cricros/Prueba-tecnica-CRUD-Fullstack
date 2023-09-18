package com.SpringCourse.demo.dao;

import com.SpringCourse.demo.models.User;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Override
    public User getCredentials(User user) {
        String query = "FROM User WHERE email = :email";
        List<User> listCheckCredentials = entityManager.createQuery(query)
                .setParameter("email", user.getEmail())
                .getResultList();

        if (listCheckCredentials.isEmpty()){
            return null;
        }
        String pwdHashed = listCheckCredentials.get(0).getPassword();
        // unhashing pwd
        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        // comparing pwd from bd vs getPassword
        if (argon2.verify(pwdHashed, user.getPassword())){
            return listCheckCredentials.get(0);
        }
        return null;
    }
}
