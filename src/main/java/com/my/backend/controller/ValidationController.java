package com.my.backend.controller;

import com.my.backend.dto.UserDTO.ResponseUserDTO;
import com.my.backend.service.ValidationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/check")
@RequiredArgsConstructor
public class ValidationController {
    private final ValidationService validationService;

    @PostMapping("/email")
    public ResponseEntity<String> checkEmail(@RequestBody ResponseUserDTO responseUserDTO) {
        String email = responseUserDTO.getEmail();
        if (validationService.checkEmail(email)) {
            return ResponseEntity.badRequest().body("이미 사용중인 이메일 입니다.");
        }
        return ResponseEntity.ok("사용 가능한 이메일 입니다.");
    }
}
