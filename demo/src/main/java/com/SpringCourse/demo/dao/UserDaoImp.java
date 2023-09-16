package com.SpringCourse.demo.dao;

import com.SpringCourse.demo.models.User;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
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
    public boolean checkCredentials(User user) {
        String query = "FROM User WHERE email = :email";
        List<User> listCheckEmail = entityManager.createQuery(query)
                .setParameter("email", user.getEmail())
                .getResultList();
        if (listCheckEmail.isEmpty()){
            return false;
        }

        // unhashing the pwd
        String pwdHash = listCheckEmail.get(0).getPassword();
        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        // comparing the pwd unhashing vs userpwd
        return argon2.verify(pwdHash,user.getPassword());
    }
}
