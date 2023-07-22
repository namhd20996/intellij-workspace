package com.example.lab.dao;

import com.example.lab.entity.User;

import java.util.List;
import java.util.Map;

public interface UserDao {

    User createUser(User user);

    User updateUser(User user);

    User deleteUser(User user);

    User findOneById(String id);

    List<User> findAll();

    List<User> findByRole(Boolean role);

    List<User> findByKeyword(Map<String, Object> kw);

    User findOneByUsernameAndPassword(String username, String password);

    List<User> findPage(int pageNumber, int pageSize);
}
