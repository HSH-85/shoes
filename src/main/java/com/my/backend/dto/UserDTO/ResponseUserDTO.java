package com.my.backend.dto.UserDTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.my.backend.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)

public class ResponseUserDTO {
    //    로그인 전용 DTO (클라이언트가 서버측으로 로그인을 요청)
    private Long id;

    private String email;

    private String token;

    private String password;


    //    Entity -> DTO 변환 (DB에서 꺼낸 Entity -> 클라이언트 응답으로 보낼 때) 즉 repository에서 어떤 정보를 꺼내 클라이언트한테 주어질 때 사용
    public static ResponseUserDTO from(User user, String token) {
        return ResponseUserDTO.builder()
                .id(user.getId())
                .email(user.getEmail())
                .token(token)
                .build();
    }
}
