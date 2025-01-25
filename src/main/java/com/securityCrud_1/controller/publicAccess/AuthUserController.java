package com.securityCrud_1.controller.publicAccess;


import com.securityCrud_1.dto.ApiResponse;
import com.securityCrud_1.dto.request.AuthUserRequest;
import com.securityCrud_1.dto.response.AuthUserResponse;
import com.securityCrud_1.service.user.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/public")
public class AuthUserController {

    private final UserService userService;

    public AuthUserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    public ResponseEntity<ApiResponse<AuthUserResponse>> registerUser(@RequestBody AuthUserRequest authUserRequest) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse<>("success",
                        "user created",
                        userService.save(authUserRequest))
                );
    }

}

