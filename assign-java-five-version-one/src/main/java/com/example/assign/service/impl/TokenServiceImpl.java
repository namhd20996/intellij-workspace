package com.example.assign.service.impl;

import com.example.assign.repo.TokenRepo;
import com.example.assign.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TokenServiceImpl implements TokenService {

    private final TokenRepo tokenRepo;
}
