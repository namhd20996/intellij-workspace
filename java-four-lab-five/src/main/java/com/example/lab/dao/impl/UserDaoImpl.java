package com.example.lab.dao.impl;

import com.example.lab.dao.AbstractDao;
import com.example.lab.dao.UserDao;
import com.example.lab.entity.User;

import java.util.List;
import java.util.Map;

public class UserDaoImpl extends AbstractDao<User> implements UserDao {
    @Override
    public User createUser(User user) {
        return create(user);
    }

    @Override
    public User updateUser(User user) {
        return update(user);
    }

    @Override
    public User deleteUser(User user) {
        return delete(user);
    }

    @Override
    public User findOneById(String id) {
        String hql = "SELECT o FROM User o WHERE o.id =?0";
        return findOne(User.class, hql, id);
    }

    @Override
    public List<User> findAll() {
        return findAll(User.class, false);
    }

    @Override
    public List<User> findByRole(Boolean role) {
        String hql = "SELECT o FROM User o WHERE o.role = ?0";
        return findMany(User.class, hql, role);
    }

    @Override
    public List<User> findByKeyword(Map<String, Object> kw) {
        String hql = "SELECT o FROM User o WHERE o.fullname LIKE:keyword";
        return findMany(User.class, hql, kw);
    }

    @Override
    public User findOneByUsernameAndPassword(String username, String password) {
        String hql = "SELECT o FROM User o WHERE o.id = ?0 AND o.password = ?1";
        return findOne(User.class, hql, username, password);
    }

    @Override
    public List<User> findPage(int pageNumber, int pageSize) {
        return findAll(User.class, false, pageNumber, pageSize);
    }
}
