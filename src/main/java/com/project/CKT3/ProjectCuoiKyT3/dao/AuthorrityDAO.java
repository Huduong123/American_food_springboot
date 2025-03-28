package com.project.CKT3.ProjectCuoiKyT3.dao;

import com.project.CKT3.ProjectCuoiKyT3.entity.Authority;

import java.util.List;

public interface AuthorrityDAO {
    void addAuthority(Authority authority);

    List<Authority> findAll();
    Authority findById(int id);
    Authority saveAuthority(Authority authority);
    void deleteAuthorityById(int id);
    boolean existsByUsernameAndAuthority(String username, String authority);
    List<Authority> searchAuthority(String username, String authority);
}
