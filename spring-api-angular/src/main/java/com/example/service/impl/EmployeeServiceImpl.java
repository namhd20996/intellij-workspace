package com.example.service.impl;

import com.example.converter.EmployeeConverter;
import com.example.dto.EmployeeDTO;
import com.example.entity.EmployeeEntity;
import com.example.exception.UserNotFoundException;
import com.example.repository.EmployeeRepo;
import com.example.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class EmployeeServiceImpl implements IEmployeeService {

    @Autowired
    private EmployeeRepo employeeRepo;

    @Autowired
    private EmployeeConverter converter;

    @Override
    public EmployeeDTO saveOrUpdate(EmployeeDTO dto) {
        // UUID tạo ra 1 số 128bit không bị trùng nhau
        dto.setEmployeeCode(UUID.randomUUID().toString());
        return converter.toDTO(employeeRepo.save(converter.toEntity(dto)));
    }

    @Override
    public List<EmployeeDTO> findAll() {
        return converter.toListDTO(employeeRepo.findAll());
    }

    @Override
    public void delete(Long id) {
        employeeRepo.deleteById(id);
    }

    @Override
    public EmployeeDTO findEmployeeEntityById(Long id) {
        return converter.toDTO(employeeRepo.findById(id).orElseThrow(() -> new UserNotFoundException("User by id " + id + " was not found")));
    }
}
