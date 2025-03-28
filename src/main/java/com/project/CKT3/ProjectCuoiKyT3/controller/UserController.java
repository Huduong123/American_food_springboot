package com.project.CKT3.ProjectCuoiKyT3.controller;

import com.project.CKT3.ProjectCuoiKyT3.entity.User;
import com.project.CKT3.ProjectCuoiKyT3.service.UserService;
import jakarta.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/system/users")
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/list-user")
    public String listUser(@RequestParam(required = false) String name,
                           @RequestParam(required = false) String fullName,
                           @RequestParam(required = false) String email,
                           @RequestParam(required = false) String phone,
                           @RequestParam(required = false) String address,
                           @RequestParam(required = false) Integer enable,
                           @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate createdAt,
                           Model model) {

        List<User> userList = userService.searchUsers(
                name, fullName, email, phone, address, enable,
                createdAt != null ? createdAt.atStartOfDay() : null
        );

        model.addAttribute("users", userList);
        return "system/users/list-user";
    }


    @GetMapping("/user-form-insert")
    public String userFormInsert(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "system/users/user-form-insert";
    }

    @GetMapping("/user-form-update")
    public String userFormUpdate(@RequestParam("id") int id, Model model) {
        User user = userService.findById(id);
        model.addAttribute("user", user);
        return "system/users/user-form-update";
    }
    @PostMapping("/updateUser")
    public String updateUser(@Valid @ModelAttribute("user") User user,
                             BindingResult bindingResult,
                             @RequestParam(value = "newPassword", required = false) String newPassword,
                             @RequestParam(value = "confirmPassword", required = false) String confirmPassword,

                             Model model) {

        User existingUser = userService.findById(user.getId());

// Email trùng nhưng khác ID → lỗi
        User existingEmail = userService.findByEmail(user.getEmail());
        if (existingEmail != null && existingEmail.getId() != user.getId()) {
            bindingResult.rejectValue("email", "error.user", "Email đã được sử dụng!");
        }

// Số điện thoại trùng nhưng khác ID → lỗi
        User existingPhone = userService.findByPhone(user.getPhone());
        if (existingPhone != null && existingPhone.getId() != user.getId()) {
            bindingResult.rejectValue("phone", "error.user", "Số điện thoại đã được sử dụng!");
        }

// Check lỗi trước khi xử lý mật khẩu
        if (bindingResult.hasErrors()) {
            return "system/users/user-form-update";
        }

        // Giữ lại mật khẩu cũ nếu không đổi
        if (newPassword != null && !newPassword.isEmpty()) {
            if (!newPassword.equals(confirmPassword)) {
                model.addAttribute("user", user);
                model.addAttribute("passwordError", "Mật khẩu xác nhận không khớp.");
                return "system/users/user-form-update";
            }
            user.setPassword(newPassword); // sẽ được hash trong service
        } else {
            user.setPassword(existingUser.getPassword()); // giữ nguyên
        }


        user.setCreatedAt(existingUser.getCreatedAt()); // giữ ngày tạo

        userService.saveUser(user);
        return "redirect:/system/users/list-user";
    }

    @PostMapping("/saveUser")
    public String saveUser(@Valid @ModelAttribute("user") User user,
                           BindingResult bindingResult,
                           Model model) {

        User existingUser = userService.findByUsername(user.getName());
        if (existingUser != null) {
            bindingResult.rejectValue("name", "error.user", "Tên đăng nhập đã tồn tại!");
        }

        User existingEmail = userService.findByEmail(user.getEmail());
        if (existingEmail != null) {
            bindingResult.rejectValue("email", "error.user", "Email đã được sử dụng!");
        }

        User existingPhone = userService.findByPhone(user.getPhone());
        if (existingPhone != null) {
            bindingResult.rejectValue("phone", "error.user", "Số điện thoại đã được sử dụng!");
        }

        if (bindingResult.hasErrors()) {
            model.addAttribute("user", user);
            return "system/users/user-form-insert";
        }

        user.setEnable(1);
        userService.saveUser(user);
        return "redirect:/system/users/list-user";
    }



    @GetMapping("/delete")
    public String deleteUser(@RequestParam("id") int id) {
        userService.deleteUserById(id);
        return "redirect:/system/users/list-user";
    }
}
