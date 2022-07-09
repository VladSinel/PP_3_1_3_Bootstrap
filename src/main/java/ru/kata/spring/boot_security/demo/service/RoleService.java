package ru.kata.spring.boot_security.demo.service;


import ru.kata.spring.boot_security.demo.model.User;
import java.util.ArrayList;


public interface RoleService {

    void saveRole (ArrayList<Long> roles, User user);

}
