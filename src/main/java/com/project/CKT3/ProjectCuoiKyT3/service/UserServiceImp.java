package com.project.CKT3.ProjectCuoiKyT3.service;

import com.project.CKT3.ProjectCuoiKyT3.dao.AuthorrityDAO;
import com.project.CKT3.ProjectCuoiKyT3.dao.UserDAO;
import com.project.CKT3.ProjectCuoiKyT3.entity.Authority;
import com.project.CKT3.ProjectCuoiKyT3.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserServiceImp implements UserService {
    private UserDAO userDAO;
    private AuthorrityDAO authorityDAO;
    private PasswordEncoder passwordEncoder;
    @Autowired
    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }
    @Autowired
    public void setAuthorityDAO(AuthorrityDAO authorityDAO) {
        this.authorityDAO = authorityDAO;
    }

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }
    @Override
    public List<User> findAll() {
        return userDAO.findAll();
    }

    @Override
    public User findById(int id) {
        return userDAO.findById(id);
    }

    @Override
    public User saveUser(User user) {
        // Nếu là mật khẩu plaintext (không phải chuỗi đã mã hóa), thì mã hóa
        if (!user.getPassword().startsWith("$2a$")) {
            String encodedPassword = passwordEncoder.encode(user.getPassword());
            user.setPassword(encodedPassword);
        }
        User savedUser = userDAO.saveUser(user);

        Authority authority = new Authority();
        authority.setUsername(user.getName());
        authority.setAuthority("ROLE_USER");

        authorityDAO.addAuthority(authority);

        return savedUser;
    }

    @Override
    public void deleteUserById(int id) {
        userDAO.deleteUserById(id);
    }

    @Override
    public User findByUsername(String username) {
        return userDAO.findByUsername(username);
    }

    @Override
    public User findByEmail(String email) {
        return userDAO.findByEmail(email);
    }

    @Override
    public User findByPhone(String phone) {
        return userDAO.findByPhone(phone);
    }


    @Override
    public List<User> searchUsers(String name, String fullName, String email, String phone, String address, Integer enable, LocalDateTime createdAt) {
        return userDAO.searchUsers(name, fullName, email, phone, address, enable, createdAt);
    }
}
