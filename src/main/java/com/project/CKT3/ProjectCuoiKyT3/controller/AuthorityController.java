package com.project.CKT3.ProjectCuoiKyT3.controller;

import com.project.CKT3.ProjectCuoiKyT3.entity.Authority;
import com.project.CKT3.ProjectCuoiKyT3.entity.User;
import com.project.CKT3.ProjectCuoiKyT3.service.AuthorityService;
import com.project.CKT3.ProjectCuoiKyT3.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/system/authorities")
public class AuthorityController {
    private AuthorityService authorityService;
    private final UserService userService;

    public AuthorityController(AuthorityService authorityService, UserService userService) {
        this.authorityService = authorityService;
        this.userService = userService;
    }

    @GetMapping("/list-authorities")
    public String listAuthority(@RequestParam(required = false) String username,
                                @RequestParam(required = false) String authority,
                                Model model) {
        List<Authority> authorities = authorityService.search(username, authority);

        model.addAttribute("authorities", authorities);
        model.addAttribute("username", username);
        model.addAttribute("authority", authority);
        return "system/authorities/list-authorities";
    }

    @GetMapping("/authorities-form-insert")
    public String authoritiesFormInsert(Model model) {
        Authority authority = new Authority();
        model.addAttribute("authorities", authority);
        return "system/authorities/authorities-form-insert";
    }

    @GetMapping("/authorities-form-update")
    public String authoritiesFormUpdate(@RequestParam("id") int id, Model model) {
        Authority authority = authorityService.findById(id);
        model.addAttribute("authorities", authority);
        return "system/authorities/authorities-form-update";
    }

    @PostMapping("/saveAuthorities")
    public String saveAuthority(@Valid @ModelAttribute("authorities") Authority authority,
                                BindingResult bindingResult,
                                Model model) {

        // Kiểm tra username có tồn tại
        User user = userService.findByUsername(authority.getUsername());
        if (user == null) {
            bindingResult.rejectValue("username", "error.username", "Tên đăng nhập không tồn tại trong hệ thống!");
        }
        // Kiểm tra trùng quyền
        boolean isDuplicate = authorityService.existsByUsernameAndAuthority(authority.getUsername(), authority.getAuthority());
        if (isDuplicate && authority.getId() == 0) {
            bindingResult.rejectValue("authority", "error.authority", "Người dùng này đã có quyền này rồi!");
        }
        if (bindingResult.hasErrors()) {
            // Xác định là đang ở insert hay update dựa vào id
            if (authority.getId() == 0) {
                return "system/authorities/authorities-form-insert";
            } else {
                return "system/authorities/authorities-form-update";
            }
        }

        authorityService.saveAuthority(authority);
        return "redirect:/system/authorities/list-authorities";
    }


    @GetMapping("/delete")
    public String deleteAuthorityById(@RequestParam("id") int id) {
        authorityService.deleteAuthorityById(id);
        return "redirect:/system/authorities/list-authorities";
    }
}
