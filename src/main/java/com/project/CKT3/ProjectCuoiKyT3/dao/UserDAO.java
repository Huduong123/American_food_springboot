package com.project.CKT3.ProjectCuoiKyT3.dao;

import com.project.CKT3.ProjectCuoiKyT3.entity.User;

import java.util.List;

public interface UserDAO {
    List<User> findAll();
    User findById(int id);
    User saveUser(User user);
    void deleteUserById(int id);
    User findByUsername(String username);

}
