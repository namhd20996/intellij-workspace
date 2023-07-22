package com.example.lab.converter;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;

@RequiredArgsConstructor
public class BaseConverter<T, U> {

    private final ModelMapper mapper;

}
