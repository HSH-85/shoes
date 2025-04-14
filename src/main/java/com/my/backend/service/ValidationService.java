package com.my.backend.service;

import com.my.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ValidationService {
    private final UserRepository userRepository;

    //    이메일 체크
    public boolean checkEmail(String email) {
        return userRepository.existsByEmail(email);
    }
}
