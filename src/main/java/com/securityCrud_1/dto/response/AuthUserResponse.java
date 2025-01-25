package com.securityCrud_1.dto.response;


import com.securityCrud_1.entity.AuthUser;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AuthUserResponse {

    private String username;
    private String email;


    public static AuthUserResponse fromEntity(AuthUser authUser) {
        return AuthUserResponse.builder()
                .username(authUser.getUsername())
                .email(authUser.getEmail())
                .build();
    }

}
