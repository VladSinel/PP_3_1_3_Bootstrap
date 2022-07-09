package ru.kata.spring.boot_security.demo.service;


import org.springframework.beans.factory.annotation.Autowired;;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.kata.spring.boot_security.demo.dao.RoleDAO;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.ArrayList;
import java.util.Set;

@Service
public class RoleServiceImp implements RoleService {

    private final RoleDAO roleDAO;

    @Autowired
    public RoleServiceImp(RoleDAO roleDAO) {
        this.roleDAO = roleDAO;
    }

    @Transactional
    @Override
    public void saveRole(ArrayList<Long> roles, User user) {
        Set<Role> roleArrayList = roleDAO.findByIdIn(roles);
        user.setRoles(roleArrayList);
    }

}
