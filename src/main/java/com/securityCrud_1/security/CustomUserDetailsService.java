package com.securityCrud_1.security;

import com.securityCrud_1.dto.request.AuthLoginRequest;
import com.securityCrud_1.dto.response.AuthResponse;
import com.securityCrud_1.entity.AuthUser;
import com.securityCrud_1.exception.UserNotFoundException;
import com.securityCrud_1.repository.AuthUserRepository;
import com.securityCrud_1.util.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final AuthUserRepository authUserRepository;

    private final JwtUtils jwtUtils;

    private final PasswordEncoder passwordEncoder;

    public CustomUserDetailsService(AuthUserRepository authUserRepository, JwtUtils jwtUtils, PasswordEncoder passwordEncoder) {
        this.authUserRepository = authUserRepository;
        this.jwtUtils = jwtUtils;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AuthUser authUser = authUserRepository.findByUsername(username);
        if (Objects.isNull(authUser)) {
            throw new UserNotFoundException("unknown user");
        }

        return User.withUsername(authUser.getUsername())
                .password(authUser.getPassword())
                .accountExpired(false)
                .accountLocked(false)
                .credentialsExpired(false)
                .disabled(false)
                .build();
    }

    public AuthResponse loginUser(AuthLoginRequest authLoginRequest) {
        String username = authLoginRequest.username();
        String password = authLoginRequest.password();

        Authentication authentication = this.authenticate(username, password);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String accessToken = jwtUtils.createToken(authentication);

        AuthResponse authResponse = new AuthResponse(username, "User Loged successfuly", accessToken, true);
        return authResponse;
    }

    public Authentication authenticate(String username, String password) {
        UserDetails userDetails = this.loadUserByUsername(username);

        if (userDetails == null) {
            throw new BadCredentialsException("Invalid username or password");
        }
        if (!passwordEncoder.matches(password, userDetails.getPassword())) {
            throw new BadCredentialsException("Invalid password");
        }

        return new UsernamePasswordAuthenticationToken(username, userDetails.getPassword());

    }
}
