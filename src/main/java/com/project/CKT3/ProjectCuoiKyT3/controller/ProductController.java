package com.project.CKT3.ProjectCuoiKyT3.controller;


import com.project.CKT3.ProjectCuoiKyT3.entity.Product;
import com.project.CKT3.ProjectCuoiKyT3.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

@Controller
@RequestMapping("/admin/products")
public class ProductController {
    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/list-product")
    public String listProduct(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "category", required = false) String category,
            @RequestParam(value = "available", required = false) Integer available,
            @RequestParam(value = "date", required = false) String date,
            Model model) {
        List<Product> products = productService.search(name, category, available, date);
        model.addAttribute("products", products);
        model.addAttribute("name", name);
        model.addAttribute("category", category);
        model.addAttribute("available", available);
        model.addAttribute("date", date);

//        List<Product> products = productService.findAll();
//        model.addAttribute("products", products);
        return "admin/products/list-product";
    }

    @GetMapping("/product-form-insert")
    public String productFormInsert( Model model) {
        Product product = new Product();
        model.addAttribute("product", product);
        return "admin/products/product-form-insert";
    }

    @PostMapping("/saveProduct")
    public String saveProduct(@Valid @ModelAttribute("product") Product product,
                              BindingResult bindingResult,
                              @RequestParam(value = "imageFile", required = false) MultipartFile imageFile) {
        boolean isNew = (product.getId() == 0);

        // Nếu là sản phẩm mới và không có ảnh → lỗi
        if (isNew && (imageFile == null || imageFile.isEmpty())) {
            bindingResult.rejectValue("image_url", "error.product", "Ảnh không được để trống");
        }

        if (bindingResult.hasErrors()) {
            return isNew ? "admin/products/product-form-insert" : "admin/products/product-form-update";
        }

        // Nếu có ảnh mới được chọn, thì upload và ghi đè
        if (imageFile != null && !imageFile.isEmpty()) {
            try {
                String fileName = Path.of(imageFile.getOriginalFilename()).getFileName().toString();
                String uploadDir = "src/main/resources/static/default/images/";
                Files.createDirectories(Paths.get(uploadDir));
                Path filePath = Paths.get(uploadDir, fileName);
                Files.copy(imageFile.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
                product.setImage_url(fileName); // set ảnh mới
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (!isNew) {
            // cập nhật nhưng không đổi ảnh → giữ nguyên ảnh cũ
            Product existingProduct = productService.findById(product.getId());
            product.setImage_url(existingProduct.getImage_url());
        }

        product.setAvailable(product.getQuantity() == 0 ? 0 : 1);
        productService.save(product);
        return "redirect:/admin/products/list-product";
    }


    @GetMapping("/product-form-update")
    public String productFormUpdate(@RequestParam("id") int id, Model model) {
        Product product = productService.findById(id);
        model.addAttribute("product", product);
        return "admin/products/product-form-update";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("id") int id) {
        productService.deleteById(id);
        return "redirect:/admin/products/list-product";
    }


}
