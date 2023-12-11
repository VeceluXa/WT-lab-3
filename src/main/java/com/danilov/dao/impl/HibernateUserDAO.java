package com.danilov.dao.impl;

import com.danilov.dao.UserDAO;
import com.danilov.entity.orders.Orders;
import com.danilov.entity.user.User;
import com.danilov.utils.HibernateSessionFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

import javax.annotation.*;


@Component
public class HibernateUserDAO implements UserDAO {

    private SessionFactory sessionFactory;

    @Autowired
    private HibernateUserDAO() { sessionFactory = HibernateSessionFactory.getSessionFactory();}


    @Override
    public User findUserByLoginAndPass(String login, String password) {
        try(Session session = sessionFactory.openSession()) {
            String hql = "FROM User WHERE user_login = :user_login AND user_password = :user_password";
            User user = session.createQuery(hql, User.class).setParameter("user_login", login).setParameter("user_password", password).uniqueResult();
            return user;
        }
    }

    @Override
    public User findUserByLogin(String login) {
        return null;
    }

    @Override
    public void addUser(User user) {
        try(Session session = sessionFactory.openSession()) {
            session.getTransaction().begin();
            session.persist(user);
            session.getTransaction().commit();
        }
    }

    @Override
    public void setUserBan(long id) {
        try(Session session = sessionFactory.openSession()) {
            User user = session.get(User.class, id);
            user.setUser_ban(!user.getUser_ban());
            session.getTransaction().begin();
            session.update(user);
            session.getTransaction().commit();
        }
    }

    @Override
    public void setUserAdmin(long id) {
        try(Session session = sessionFactory.openSession()) {
            User user = session.get(User.class, id);
            user.setUser_role(!user.getUser_role());
            session.getTransaction().begin();
            session.update(user);
            session.getTransaction().commit();
        }
    }

    @Override
    public void setUserDiscount(long id, float discount) {
        try(Session session = sessionFactory.openSession()) {
            User user = session.get(User.class, id);
            user.setUser_discount(discount);
            session.getTransaction().begin();
            session.update(user);
            session.getTransaction().commit();
        }
    }

    @Override
    public List<User> findAllUsers() {
        try(Session session = sessionFactory.openSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
            Root<User> root = criteriaQuery.from(User.class);
            criteriaQuery.select(root);
            Query query = session.createQuery(criteriaQuery);
            return query.getResultList();
        }
    }
}
