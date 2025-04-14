package com.my.backend.service;

import com.my.backend.config.JwtTokenProvider;
import com.my.backend.dto.UserDTO.RequestUserDTO;
import com.my.backend.dto.UserDTO.ResponseUserDTO;
import com.my.backend.dto.UserDTO.UpdatedResponseUserDTO;
import com.my.backend.entity.User;
import com.my.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final ValidationService validationService;

    //    회원가입 기능
    public RequestUserDTO register(RequestUserDTO requestUserDTO) {

//  비밀번호 불일치 시 예외 던짐
        if (!requestUserDTO.getPassword().equals(requestUserDTO.getRepeatPassword())) {
            throw new IllegalArgumentException("비밀번호가 불일치");
        }
        User user = requestUserDTO.toEntity();
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
//  회원가입이 성공하면 성공 후 사용자 정보를 리턴한다.
        log.info("회원가입 성공");
        return RequestUserDTO.from(user);
    }

    //   로그인 기능
    public ResponseUserDTO login(ResponseUserDTO responseUserDTO) {
        User user = userRepository.findByEmail(responseUserDTO.getEmail()).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 이메일 입니다."));
        if (!passwordEncoder.matches(responseUserDTO.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("비밀번호를 확인하세요.");
        }
        String token = jwtTokenProvider.createToken(user.getEmail());

        log.info("로그인 성공");
        return ResponseUserDTO.from(user, token);
    }

    //    회원 수정 기능
    public UpdatedResponseUserDTO updatedUser(UpdatedResponseUserDTO dto) {
        User user = userRepository.findById(dto.getId())
                .orElseThrow(() -> new IllegalArgumentException("아이디를 찾을 수 없습니다."));

//        이메일 수정
        if (dto.getEmail() != null) {
            if (validationService.checkEmail(dto.getEmail())) {
                throw new IllegalArgumentException("이미 사용중인 이메일 입니다,");
            }
            user.setEmail(dto.getEmail());
        }

//        비밀번호 수정
        if (dto.getPassword() != null) {
            String password = passwordEncoder.encode(dto.getPassword());
            user.setPassword(password);
        }

        User updatedUser = userRepository.save(user);
        return UpdatedResponseUserDTO.from(user);
    }


}
