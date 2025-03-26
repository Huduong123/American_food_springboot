package com.project.CKT3.ProjectCuoiKyT3.service;


import com.project.CKT3.ProjectCuoiKyT3.dao.ProductDAO;
import com.project.CKT3.ProjectCuoiKyT3.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImp implements ProductService {
    private ProductDAO productDAO;
    @Autowired
    public ProductServiceImp(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    @Override
    public List<Product> findAll() {
        return productDAO.findAll();
    }

    @Override
    public Product findById(int id) {
        return productDAO.findById(id);
    }

    @Override
    public Product save(Product product) {
        return productDAO.save(product);
    }

    @Override
    public void deleteById(int id) {
        productDAO.deleteById(id);
    }

    @Override
    public List<Product> search(String name, String category, Integer available, String date) {
        return productDAO.search(name, category, available, date);
    }
}
