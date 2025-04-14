package com.my.backend.controller;

import com.my.backend.dto.UserDTO.RequestUserDTO;
import com.my.backend.dto.UserDTO.ResponseUserDTO;
import com.my.backend.dto.UserDTO.UpdatedResponseUserDTO;
import com.my.backend.repository.UserRepository;
import com.my.backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final UserRepository userRepository;

    //    회원가입
    @PostMapping("/register")
    public ResponseEntity<RequestUserDTO> register(@RequestBody RequestUserDTO requestUserDTO) throws IllegalAccessException {
        return ResponseEntity.ok(userService.register(requestUserDTO));
    }

    //    로그인
    @PostMapping("/login")
    public ResponseEntity<ResponseUserDTO> login(@RequestBody ResponseUserDTO responseUserDTO) throws IllegalArgumentException {
        return ResponseEntity.ok(userService.login(responseUserDTO));
    }

    //    회원삭제
    @DeleteMapping("/delete/{id}")
    private ResponseEntity<String> deleteUser(@PathVariable Long id) {
        if (userRepository.findById(id).isEmpty()) {
            return ResponseEntity.badRequest().body("아이디가 존재하지 않습니다.");
        }
        userRepository.deleteById(id);
        return ResponseEntity.ok("삭제 완료");
    }

    //    회원수정
    @PutMapping("/update")
    public ResponseEntity<UpdatedResponseUserDTO> updatedUser(@RequestBody UpdatedResponseUserDTO dto) {
        UpdatedResponseUserDTO userDTO = userService.updatedUser(dto);
        return ResponseEntity.ok(userDTO);
    }
}
