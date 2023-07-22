package com.example.lab.service.impl;

import com.example.lab.converter.UserConverter;
import com.example.lab.dao.UserDao;
import com.example.lab.dto.UserDTO;
import com.example.lab.entity.User;
import com.example.lab.service.UserService;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserServiceImpl implements UserService {

    @Inject
    private UserDao dao;

    @Override
    public UserDTO create(UserDTO user) {
        return UserConverter.getInstance()
                .toDTO(dao.createUser(UserConverter.getInstance().toEntity(user)));
    }

    @Override
    public UserDTO update(UserDTO user) {
        return UserConverter.getInstance()
                .toDTO(dao.updateUser(UserConverter.getInstance().toEntity(user)));
    }

    @Override
    public void delete(String id) {
        User user = dao.findOneById(id);
        dao.deleteUser(user);
    }

    @Override
    public UserDTO findOneById(String id) {
        return UserConverter.getInstance().toDTO(dao.findOneById(id));
    }

    @Override
    public List<UserDTO> findAll() {
        return UserConverter.getInstance().toListDTO(dao.findAll());
    }

    @Override
    public List<UserDTO> findByRole(Boolean role) {
        return UserConverter.getInstance().toListDTO(dao.findByRole(role));
    }

    @Override
    public List<UserDTO> findByKeyword(String kw) {
        Map<String, Object> map = new HashMap<>();
        map.put("keyword", kw);
        return UserConverter.getInstance().toListDTO(dao.findByKeyword(map));
    }

    @Override
    public UserDTO findOneByUsernameAndPassword(String username, String password) {
        return UserConverter.getInstance().toDTO(dao.findOneByUsernameAndPassword(username, password));
    }

    @Override
    public List<UserDTO> findPage(int pageNumber, int pageSize) {
        return null;
    }
}
