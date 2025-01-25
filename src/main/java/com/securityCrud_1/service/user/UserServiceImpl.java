package com.securityCrud_1.service.user;


import com.securityCrud_1.dto.request.AuthUserRequest;
import com.securityCrud_1.dto.response.AuthUserResponse;
import com.securityCrud_1.entity.AuthUser;
import com.securityCrud_1.repository.AuthUserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {


    private final AuthUserRepository authUserRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(AuthUserRepository authUserRepository) {
        this.authUserRepository = authUserRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }


    @Override
    public AuthUserResponse save(AuthUserRequest authUser) {
        AuthUser authUserEntity = toEntity(authUser);
        String encodedPassword = passwordEncoder.encode(authUser.getPassword());
        authUserEntity.setPassword(encodedPassword);
        return AuthUserResponse.fromEntity(authUserRepository.save(authUserEntity));
    }

    private AuthUser toEntity(AuthUserRequest authUser) {
        return AuthUser.builder()
                .username(authUser.getUsername())
                .password(authUser.getPassword())
                .email(authUser.getEmail())
                .build();
    }
}
