package com.project.CKT3.ProjectCuoiKyT3.dao;

import com.project.CKT3.ProjectCuoiKyT3.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDAOImp implements  UserDAO {
    private EntityManager entityManager;

    @Autowired
    public UserDAOImp(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<User> findAll() {
        TypedQuery<User> query = entityManager.createQuery("select u from User u", User.class);
        return query.getResultList();
    }

    @Override
    public User findById(int id) {
        return entityManager.find(User.class, id);
    }

    @Override
    @Transactional
    public User saveUser(User user) {
        User savedUser = entityManager.merge(user);
        return savedUser;
    }

    @Override
    @Transactional
    public void deleteUserById(int id) {
        User user = entityManager.find(User.class, id);
        entityManager.remove(user);
    }

    @Override
    public User findByUsername(String username) {
        TypedQuery<User> query = entityManager.createQuery("SELECT u FROM User u WHERE u.name = :username", User.class);
        query.setParameter("username", username);
        List<User> result = query.getResultList();
        return result.isEmpty() ? null : result.get(0);
    }

}
