package com.finalgo.application.dao;

import com.finalgo.application.entity.User;
import org.hibernate.query.Query;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;

@Service
public class UserDao extends AbstractGenericDao<User> {

    public UserDao() {
        super(User.class);
    }

    /**
     * Récupèrer l'utilisateur correspondant aux paramètres suivant:
     * @param username
     * @param password
     * @return User
     *
     * TODO : Implémenter la requête Hibernate/SQL
     */
    public User findWithCredentials(String username, String password) {
        String query = "FROM User u WHERE u.username = :username AND u.password = :password";
        try {
            Query<User> hqlQuery = getCurrentSession().createQuery(query, User.class);
            hqlQuery.setParameter("username", username);
            hqlQuery.setParameter("password", password);
            return hqlQuery.uniqueResult();
        } catch (NoResultException e) {
            return null;
        }
        //return createOneItemSelectQuery(query);
    }

    public User findWithUsernameAndEmail(String username, String email) {
        String query = "FROM User u WHERE u.username = :username OR u.email = :email";
        try {
            Query<User> hqlQuery = getCurrentSession().createQuery(query, User.class);
            hqlQuery.setParameter("username", username);
            hqlQuery.setParameter("email", email);
            return hqlQuery.uniqueResult();
        } catch (NoResultException e) {
            return null;
        }
        //return createOneItemSelectQuery(query);
    }
}
