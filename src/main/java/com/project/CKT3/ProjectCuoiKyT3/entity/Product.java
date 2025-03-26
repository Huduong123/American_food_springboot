package com.project.CKT3.ProjectCuoiKyT3.entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.time.LocalDateTime;


@Entity
@Table(name = "food_items")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Pattern(regexp = "^[\\p{L}0-9 ]+$", message = "Tên món không được chứa ký tự đặc biệt")
    @NotBlank(message = "Tên không được để trống")
    @Size(min =4, message = "Tên món phải có ít nhất 4 ký tự")
    @Column(name = "name")
    private String name;

    @NotBlank(message = "Vui lòng chọn danh mục")
    @Column(name = "category")
    private String category;

    @Column(name = "description")
    @NotBlank(message = "Mô tả không được để trống")
    @Size(min = 10, message = "Mô tả phải có ít nhất 10 ký tự")
    private String description;

    @Min(value=1, message = "Số lượng phải lớn hơn 0")
    @Column(name = "quantity")
    private int quantity;


    @DecimalMin(value = "0.1", message = "Giá tiền phải lớn hơn 0")
    @Column(name = "price")
    private double price;
    @Column(name = "image_url")
    private String image_url;
    @Column(name ="available")
    private int available;


    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    public Product() {
    }

    public Product(String name, int quantity, LocalDateTime createdAt, int available, String image_url, double price, String description, String category) {
        this.name = name;
        this.quantity = quantity;
        this.createdAt = createdAt;
        this.available = available;
        this.image_url = image_url;
        this.price = price;
        this.description = description;
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public int getAvailable() {
        return available;
    }

    public void setAvailable(int available) {
        this.available = available;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", description='" + description + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                ", image_url='" + image_url + '\'' +
                ", available=" + available +
                ", createdAt=" + createdAt +
                '}';
    }
}
