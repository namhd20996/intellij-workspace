package com.example.service;

import com.example.dto.EmployeeDTO;

import java.util.List;

public interface IEmployeeService {

    EmployeeDTO saveOrUpdate(EmployeeDTO dto);

    List<EmployeeDTO> findAll();

    void delete(Long id);

    EmployeeDTO findEmployeeEntityById(Long id);
}
