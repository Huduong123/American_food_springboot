package com.project.CKT3.ProjectCuoiKyT3.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Pattern(
            regexp = "^(?=.*[\\p{L}])[\\p{L}0-9 ]+$",
            message = "Tên đăng nhập không được chứa ký tự đặc biệt và phải có ít nhất 1 chữ cái"
    )
    @NotBlank(message = "Tên đăng nhập không được để trống")
    @Size(min =5, message = "Tên đăng nhập phải có ít nhất 5 ký tự")
    @Column(name = "username")
    private String name;

    @Pattern(regexp = "^[\\p{L} ]+$", message = "Họ và tên chỉ được chứa chữ cái và khoảng trắng, không có số/ký tự đặc biệt")
    @Size(min = 6, message = "Họ và tên phải có ít nhất 6 ký tự")
    @NotBlank(message = "Họ và tên không được để trống")
    @Column(name = "full_name")
    private String fullName;

    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@gmail\\.com$", message = "Email phải đúng định dạng và kết thúc bằng @gmail.com")
    @NotBlank(message = "Email không được để trống")
    @Column(name = "email")
    private String email;

    @Pattern(
            regexp = "^(?=.*[A-Z])(?=.*[^a-zA-Z0-9]).{1,}$",
            message = "Mật khẩu phải có ít nhất 1 chữ hoa và 1 ký tự đặc biệt"
    )
    @Size(min =5, message = "Mật khẩu phải có ít nhất 5 ký tự")
    @NotBlank(message = "Mật khẩu không được để trống")
    @Column(name = "password")
    private String password;

    @Pattern(regexp = "^[0-9]{9,15}$", message = "Số điện thoại chỉ được chứa số, không có chữ hoặc ký tự đặc biệt")
    @Size(min = 9, message = "Số điện thoại phải có ít nhất 9 số")
    @NotBlank(message = "Số điện thoại không được để trống")
    @Column(name = "phone")
    private String phone;

    @Pattern(
            regexp = "^[\\p{L}0-9\\s\\-,./]+$",
            message = "Địa chỉ không được chứa ký tự đặc biệt (chỉ cho phép chữ, số, khoảng trắng, dấu -, ., /)"
    )
    @NotBlank(message = "Địa chỉ không được để trống")
    @Column(name = "address")
    private String address;

    @Column(name = "enabled")
    private int enable;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    public User() {
    }

    public User(String name, String fullName, String address, String phone, String password, String email, int enable, LocalDateTime createdAt) {
        this.name = name;
        this.fullName = fullName;
        this.address = address;
        this.phone = phone;
        this.password = password;
        this.email = email;
        this.enable = enable;
        this.createdAt = createdAt;
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

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public int getEnable() {
        return enable;
    }

    public void setEnable(int enable) {
        this.enable = enable;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", enable=" + enable +
                ", createdAt=" + createdAt +
                '}';
    }
}
