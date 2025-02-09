package com.securityCrud_1.controller.publicAccess;


import com.securityCrud_1.dto.ApiResponse;
import com.securityCrud_1.dto.request.AuthLoginRequest;
import com.securityCrud_1.dto.request.AuthUserRequest;
import com.securityCrud_1.dto.response.AuthResponse;
import com.securityCrud_1.dto.response.AuthUserResponse;
import com.securityCrud_1.security.CustomUserDetailsService;
import com.securityCrud_1.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/public")
public class AuthUserController {

    @Autowired
    private UserService userService;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @PostMapping("/signup")
    public ResponseEntity<ApiResponse<AuthUserResponse>> registerUser(@RequestBody AuthUserRequest authUserRequest) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse<>("success",
                        "user created",
                        userService.save(authUserRequest))
                );
    }

    @PostMapping("/log-in")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthLoginRequest userRequest) {
        return new ResponseEntity<>(customUserDetailsService.loginUser(userRequest), HttpStatus.OK);
    }

}

