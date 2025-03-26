package com.project.CKT3.ProjectCuoiKyT3.dao;

import com.project.CKT3.ProjectCuoiKyT3.entity.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductDAOImp implements  ProductDAO {
    private EntityManager entityManager;

    @Autowired
    public ProductDAOImp(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Product> findAll() {
        TypedQuery<Product> query = entityManager.createQuery("select p from Product p", Product.class);
        return query.getResultList();
    }

    @Override
    public Product findById(int id) {
        return entityManager.find(Product.class, id);
    }

    @Override
    @Transactional
    public Product save(Product product) {
        Product savedProduct = entityManager.merge(product);
        return savedProduct;
    }

    @Override
    @Transactional
    public void deleteById(int id) {
        Product product= entityManager.find(Product.class, id);
        entityManager.remove(product);
    }

    @Override
    public List<Product> search(String name, String category, Integer available, String date) {
        String jpql = "SELECT p FROM Product p WHERE 1=1";

        if(name != null && !name.isEmpty()) {
            jpql += " AND LOWER(p.name) LIKE :name";
        }
        if(category != null && !category.isEmpty()) {
            jpql += " AND p.category LIKE :category";
        }
        if(available != null){
            jpql += " AND p.available = :available";
        }
        if(date != null && !date.isEmpty()) {
            jpql += " AND FUNCTION('DATE', p.createdAt) = :date";
        }

        TypedQuery<Product> query = entityManager.createQuery(jpql, Product.class);
        if(name != null && !name.isEmpty()) {
            query.setParameter("name", "%" + name + "%");
        }
        if (category != null && !category.isEmpty()) {
            query.setParameter("category", category);
        }
        if (available != null) {
            query.setParameter("available", available);
        }
        if (date != null && !date.isEmpty()) {
            query.setParameter("date", java.sql.Date.valueOf(date));
        }
        return query.getResultList();
    }
}
