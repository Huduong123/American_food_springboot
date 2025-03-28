package com.project.CKT3.ProjectCuoiKyT3.controller;

import com.project.CKT3.ProjectCuoiKyT3.entity.User;
import com.project.CKT3.ProjectCuoiKyT3.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/system/users")
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/list-user")
    public String listUser(Model model) {
        List<User> userList = userService.findAll();
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
    public String userFormUpdate(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "system/users/user-form-update";
    }

    @PostMapping("/saveUser")
    public String saveUser(@Valid @ModelAttribute("user") User user,
                           BindingResult bindingResult,
                           Model model) {

        // Kiểm tra username trùng
        User existingUser = userService.findByUsername(user.getName());
        if (existingUser != null) {
            bindingResult.rejectValue("name", "error.user", "Tên đăng nhập đã tồn tại!");
        }

        if (bindingResult.hasErrors()) {
            model.addAttribute("user", user);
            return "system/users/user-form-insert";
        }

        userService.saveUser(user);
        return "redirect:/system/users/list-user";
    }


    @GetMapping("/delete")
    public String deleteUser(@RequestParam("id") int id) {
        userService.deleteUserById(id);
        return "redirect:/system/users/list-user";
    }
}
