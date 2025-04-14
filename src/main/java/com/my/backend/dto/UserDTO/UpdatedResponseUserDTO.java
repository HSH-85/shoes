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
// 회원 정보 수정 DTO
public class UpdatedResponseUserDTO {
    private Long id;
    private String email;
    private String password;
    private String address;
    private String addressDetail;

    public User toEntity() {
        return User.builder()
                .email(this.email)
                .address(this.address)
                .addressDetail(this.addressDetail)
                .build();
    }

    public static UpdatedResponseUserDTO from(User user) {
        return UpdatedResponseUserDTO.builder()
                .id(user.getId())
                .email(user.getEmail())
                .address(user.getAddress())
                .addressDetail(user.getAddressDetail())
                .build();
    }
}
