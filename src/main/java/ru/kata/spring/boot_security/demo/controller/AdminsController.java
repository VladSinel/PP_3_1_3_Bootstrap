package ru.kata.spring.boot_security.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleServiceImp;
import ru.kata.spring.boot_security.demo.service.UserServiceImp;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminsController {

    private final UserServiceImp userService;
    private final RoleServiceImp roleService;
    private final PasswordEncoder passwordEncoder;


    @Autowired
    AdminsController(UserServiceImp userService, RoleServiceImp roleService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping
    public String listUsers(Principal principal, ModelMap model, User newUser) {
        List<User> listUsers = userService.findAll();
        User thisUser = userService.findByUserName(principal.getName());
        model.addAttribute("listUsers", listUsers);
        model.addAttribute("newUser", newUser);
        model.addAttribute("thisUser", thisUser);
        return "admin";
    }

    @PostMapping("/adduser")
    public String addUser(@ModelAttribute("user") User user,
                          @RequestParam(value = "role") ArrayList<Long> roles) {
        roleService.saveRole(roles, user);
        userService.save(user);
        return "redirect:/admin";
    }

    @DeleteMapping("/remove/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.deleteById(id);
        return "redirect:/admin";
    }

    @PatchMapping("/update")
    public String updateUser(@ModelAttribute("user") User user,
                             @RequestParam(value = "role") ArrayList<Long> roles) {
        roleService.saveRole(roles, user);
        userService.save(user);
        return "redirect:/admin";
    }

}
