package com.project.CKT3.ProjectCuoiKyT3.dao;

import com.project.CKT3.ProjectCuoiKyT3.entity.Authority;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AuthorityDAOImp implements  AuthorrityDAO{
    @Autowired
    private EntityManager entityManager;


    @Override
    @Transactional
    public void addAuthority(Authority authority) {
        entityManager.persist(authority);
    }
}
