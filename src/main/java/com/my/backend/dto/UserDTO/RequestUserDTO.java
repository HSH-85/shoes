package com.my.backend.dto.UserDTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.my.backend.entity.User;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RequestUserDTO {
    //    회원가입 전용 DTO (클라이언트가 서버측으로 DB를 조회하여 회원이 있는지 조회)
    private Long id;

    private String email;

    private String password;

    private String repeatPassword;

    private String address;

    private String addressDetail;


    //    Entity -> DTO 변환 (DB에서 꺼낸 Entity -> 클라이언트 응답으로 보낼 때) 즉 repository에서 어떤 정보를 꺼내 클라이언트한테 주어질 때 사용
    //   즉 controller 에서 dto 형식으로 전달 받는다(1차 검증) 그리고 해당값들을 다시 entity로 변환해서 넣는다.
    public static RequestUserDTO from(User user) {
        return RequestUserDTO.builder()
                .id(user.getId())
                .email(user.getEmail())
                .build();
    }

    //    DTO -> Entity 변환 (Controller -> Service -> Repository 내려보낼 때 사용)
    public User toEntity() {
        return User.builder()
                .email(this.email)
                .password(this.password)
                .address(this.address)
                .addressDetail(this.addressDetail)
                .build();
    }

}
