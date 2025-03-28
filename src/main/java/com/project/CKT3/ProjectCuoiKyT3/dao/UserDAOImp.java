package com.project.CKT3.ProjectCuoiKyT3.dao;

import com.project.CKT3.ProjectCuoiKyT3.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
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
        if (user != null) {
            entityManager.remove(user);
        }
    }

    @Override
    public User findByUsername(String username) {
        TypedQuery<User> query = entityManager.createQuery("SELECT u FROM User u WHERE u.name = :username", User.class);
        query.setParameter("username", username);
        List<User> result = query.getResultList();
        return result.isEmpty() ? null : result.get(0);
    }

    @Override
    public User findByEmail(String email) {
        TypedQuery<User> query = entityManager.createQuery("SELECT u FROM User u WHERE u.email = :email", User.class);
        query.setParameter("email", email);
        List<User> result = query.getResultList();
        return result.isEmpty() ? null : result.get(0);
    }

    @Override
    public User findByPhone(String phone) {
        TypedQuery<User> query = entityManager.createQuery("SELECT u FROM User u WHERE u.phone = :phone", User.class);
        query.setParameter("phone", phone);
        List<User> result = query.getResultList();
        return result.isEmpty() ? null : result.get(0);
    }


    @Override
    public List<User> searchUsers(String name, String fullName, String email, String phone,
                                  String address, Integer enable, LocalDateTime createdAt) {
        StringBuilder sb = new StringBuilder("SELECT u FROM User u WHERE 1=1");
        if (name != null && !name.isEmpty()) sb.append(" AND u.name LIKE :name");
        if (fullName != null && !fullName.isEmpty()) sb.append(" AND u.fullName LIKE :fullName");
        if (email != null && !email.isEmpty()) sb.append(" AND u.email LIKE :email");
        if (phone != null && !phone.isEmpty()) sb.append(" AND u.phone LIKE :phone");
        if (address != null && !address.isEmpty()) sb.append(" AND u.address LIKE :address");
        if (enable != null) sb.append(" AND u.enable = :enable");
        if (createdAt != null) sb.append(" AND DATE(u.createdAt) = DATE(:createdAt)");

        TypedQuery<User> query = entityManager.createQuery(sb.toString(), User.class);
        if (name != null && !name.isEmpty()) query.setParameter("name", "%" + name + "%");
        if (fullName != null && !fullName.isEmpty()) query.setParameter("fullName", "%" + fullName + "%");
        if (email != null && !email.isEmpty()) query.setParameter("email", "%" + email + "%");
        if (phone != null && !phone.isEmpty()) query.setParameter("phone", "%" + phone + "%");
        if (address != null && !address.isEmpty()) query.setParameter("address", "%" + address + "%");
        if (enable != null) query.setParameter("enable", enable);
        if (createdAt != null) query.setParameter("createdAt", createdAt);

        return query.getResultList();
    }


}
