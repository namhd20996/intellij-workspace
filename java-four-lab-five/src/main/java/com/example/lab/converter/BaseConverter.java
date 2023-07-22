package com.example.lab.converter;

import org.modelmapper.ModelMapper;

import java.util.Optional;

public class BaseConverter<T, U> {

    private final ModelMapper mapper = new ModelMapper();

}
