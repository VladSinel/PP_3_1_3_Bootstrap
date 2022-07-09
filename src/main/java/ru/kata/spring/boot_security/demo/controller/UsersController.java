package ru.kata.spring.boot_security.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleServiceImp;
import ru.kata.spring.boot_security.demo.service.UserServiceImp;

import java.security.Principal;


@Controller
@RequestMapping("/user")
public class UsersController {
    private final UserServiceImp userService;
    private final RoleServiceImp roleService;

    @Autowired
    public UsersController(UserServiceImp userService, RoleServiceImp roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping
    public String userPage(Principal principal, Model model){
        User user = userService.findByUserName(principal.getName());
        model.addAttribute("user", user);
        return "user";
    }

}
