package com.project.CKT3.ProjectCuoiKyT3.service;

import com.project.CKT3.ProjectCuoiKyT3.entity.Product;

import java.util.List;

public interface ProductService {
    List<Product> findAll();
    Product findById(int id);
    Product save(Product product);
    void deleteById(int id);
    List<Product> search(String name, String category, Integer available, String date);
}
