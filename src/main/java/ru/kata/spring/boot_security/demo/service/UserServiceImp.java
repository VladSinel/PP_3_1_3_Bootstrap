package ru.kata.spring.boot_security.demo.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.kata.spring.boot_security.demo.dao.RoleDAO;
import ru.kata.spring.boot_security.demo.dao.UserDAO;
import ru.kata.spring.boot_security.demo.model.User;


import java.util.List;


@Service
@Transactional
public class UserServiceImp implements UserService {

    private final UserDAO userDAO;
    private final RoleDAO roleDAO;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImp(UserDAO userDAO, RoleDAO roleDAO, PasswordEncoder passwordEncoder) {
        this.userDAO = userDAO;
        this.roleDAO = roleDAO;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public User findByUserName(String userName){
        return userDAO.findByName(userName);
    }

    @Transactional
    @Override
    public List<User> findAll() {
        return userDAO.findAll();
    }

    @Transactional
    @Override
    public User findUserById(Long id) {
        return userDAO.findUserById(id);
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        userDAO.deleteById(id);
    }

    @Transactional
    @Override
    public void save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDAO.save(user);
    }

}
