package com.project.CKT3.ProjectCuoiKyT3.service;

import com.project.CKT3.ProjectCuoiKyT3.entity.Authority;

import java.util.List;

public interface AuthorityService {
    List<Authority> findAll();
    Authority findById(int id);
    Authority saveAuthority(Authority authority);
    void deleteAuthorityById(int id);

    boolean existsByUsernameAndAuthority(String username, String authority);

    List<Authority> search(String username, String authority);

}
