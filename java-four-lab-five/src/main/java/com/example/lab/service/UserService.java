package com.example.lab.service;

import com.example.lab.dto.UserDTO;

import java.util.List;

public interface UserService {

    UserDTO create(UserDTO user);

    UserDTO update(UserDTO user);

    void delete(String id);

    UserDTO findOneById(String id);

    List<UserDTO> findAll();

    List<UserDTO> findByRole(Boolean role);

    List<UserDTO> findByKeyword(String kw);

    UserDTO findOneByUsernameAndPassword(String username, String password);

    List<UserDTO> findPage(int pageNumber, int pageSize);
}
