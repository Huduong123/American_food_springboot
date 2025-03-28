package com.project.CKT3.ProjectCuoiKyT3.service;

import com.project.CKT3.ProjectCuoiKyT3.dao.AuthorrityDAO;
import com.project.CKT3.ProjectCuoiKyT3.entity.Authority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorityServiceImp implements  AuthorityService{

   private AuthorrityDAO authorityDAO;

   @Autowired
    public AuthorityServiceImp(AuthorrityDAO authorityDAO) {
        this.authorityDAO = authorityDAO;
    }

    @Override
    public List<Authority> findAll() {
        return authorityDAO.findAll();
    }

    @Override
    public Authority findById(int id) {
        return authorityDAO.findById(id);
    }

    @Override
    public Authority saveAuthority(Authority authority) {
        return authorityDAO.saveAuthority(authority);
    }

    @Override
    public void deleteAuthorityById(int id) {
        authorityDAO.deleteAuthorityById(id);
    }

    @Override
    public boolean existsByUsernameAndAuthority(String username, String authority) {
        return authorityDAO.existsByUsernameAndAuthority(username, authority);
    }


    @Override
    public List<Authority> search(String username, String authority) {
        return authorityDAO.searchAuthority(username, authority);
    }
}
