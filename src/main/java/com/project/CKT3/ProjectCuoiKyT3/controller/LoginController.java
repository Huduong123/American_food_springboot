package com.project.CKT3.ProjectCuoiKyT3.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {


    @GetMapping("/")
    public String home(){
        return "home";
    }
    @GetMapping("/login")
    public  String login(){
        return "login";
    }
    @GetMapping("/admin")
    public  String admin(){
        return "admin";
    }
    @GetMapping("system")
    public  String system(){
        return "system";
    }

}
