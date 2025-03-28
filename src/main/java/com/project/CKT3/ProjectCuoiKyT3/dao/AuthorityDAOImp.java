package com.project.CKT3.ProjectCuoiKyT3.dao;

import com.project.CKT3.ProjectCuoiKyT3.entity.Authority;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AuthorityDAOImp implements  AuthorrityDAO{
    @Autowired
    private EntityManager entityManager;

    @Autowired
    public AuthorityDAOImp(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void addAuthority(Authority authority) {
        entityManager.persist(authority);
    }

    @Override
    public List<Authority> findAll() {
        TypedQuery<Authority> query = entityManager.createQuery("select a from Authority a", Authority.class);
        return query.getResultList();
    }

    @Override
    public Authority findById(int id) {
        return entityManager.find(Authority.class, id);
    }

    @Override
    @Transactional
    public Authority saveAuthority(Authority authority) {
        Authority savedAuthority = entityManager.merge(authority);
        return savedAuthority;
    }

    @Override
    @Transactional
    public void deleteAuthorityById(int id) {
        Authority authority = entityManager.find(Authority.class, id);
        entityManager.remove(authority);
    }

    @Override
    public boolean existsByUsernameAndAuthority(String username, String authority) {
        String jpql = "SELECT COUNT(a) FROM Authority a WHERE a.username = :username AND a.authority = :authority";
        Long count = entityManager.createQuery(jpql, Long.class)
                .setParameter("username", username)
                .setParameter("authority", authority)
                .getSingleResult();
        return count > 0;
    }

    @Override
    public List<Authority> searchAuthority(String username, String authority) {
        String jpql = "SELECT a FROM Authority a WHERE 1=1";

        if (username != null && !username.isEmpty()) {
            jpql += " AND a.username LIKE :username";
        }
        if (authority != null && !authority.isEmpty()) {
            jpql += " AND a.authority = :authority";
        }

        TypedQuery<Authority> query = entityManager.createQuery(jpql, Authority.class);

        if (username != null && !username.isEmpty()) {
            query.setParameter("username", "%" + username + "%");
        }
        if (authority != null && !authority.isEmpty()) {
            query.setParameter("authority", authority);
        }

        return query.getResultList();
    }
}
