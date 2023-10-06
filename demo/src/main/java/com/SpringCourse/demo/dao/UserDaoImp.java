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
    public Boolean isUsernameNotExist(User user) {
        String query = "FROM User WHERE username  = :username";
        List<User> isUsernameNotExist = entityManager.createQuery(query)
                .setParameter("username", user.getUsername())
                .getResultList();
        return isUsernameNotExist.isEmpty();
    }
    @Override
    public void createUser(User user) {
        entityManager.merge(user);
    }

    @Override
    public User getCredentials(User user) {
        // se valida que exista el usuario
        String query = "FROM User WHERE username  = :username";
        List<User> listCheckCredentials = entityManager.createQuery(query)
                .setParameter("username", user.getUsername())
                .getResultList();

        if (listCheckCredentials.isEmpty()){
            return null;
        }
        String pwdHashed = listCheckCredentials.get(0).getPassword();
        // unhashing pwd
        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        // comparing pwd from bd vs getPassword
        if (argon2.verify(pwdHashed, user.getPassword())){
            // usuario encontrado
            return listCheckCredentials.get(0);
        }
        // contrase;as no coinciden
        return null;
    }
    @Override
    public List<User> getUser(Long id) {
        String query = "FROM User Where id_user = :id_user";
        return entityManager.createQuery(query)
                .setParameter("id_user", id)
                .getResultList();
    }

    @Override
    public Integer updateUser(User user, Long id) {
        String query = "UPDATE User SET name = :name" + "," +
                "last_name = :last_name" + "," +
                "username = :username" + "," +
                "password = :password" + "," +
                "creation_date = :creation_date" + " " +
                "WHERE id_user = :id_user";
        return entityManager.createQuery(query)
                .setParameter("name",user.getName())
                .setParameter("last_name", user.getLastName())
                .setParameter("username", user.getUsername())
                .setParameter("password", user.getPassword())
                .setParameter("creation_date", user.getCreation_date())
                .setParameter("id_user", id)
                .executeUpdate();
    }
}
