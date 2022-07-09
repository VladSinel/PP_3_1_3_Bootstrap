package ru.kata.spring.boot_security.demo.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserDAO extends JpaRepository<User, Long> {

    // решил сделать JOIN FETCH через аннотацию, а не через entity manager createQuery в сервисах или репозиториях,
    // результат получился такой же, а кода меньше
    @Query ("SELECT u FROM User u JOIN FETCH u.roles where u.name = (:name)")
    User findByName(String name);

    User findUserById(Long id);

    @Query(value = "SELECT DISTINCT b FROM User b LEFT JOIN FETCH b.roles ORDER BY b.id")
    List<User> findAll();

}
