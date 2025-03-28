package com.project.CKT3.ProjectCuoiKyT3.dao;

import com.project.CKT3.ProjectCuoiKyT3.entity.User;

import java.time.LocalDateTime;
import java.util.List;

public interface UserDAO {
    List<User> findAll();
    User findById(int id);
    User saveUser(User user);
    void deleteUserById(int id);

    User findByUsername(String username);
    User findByEmail(String email);
    User findByPhone(String phone);

    List<User> searchUsers(String name, String fullName, String email, String phone,
                           String address, Integer enable, LocalDateTime createdAt);
}
