package com.securityCrud_1.service.user;

import com.securityCrud_1.dto.request.AuthUserRequest;
import com.securityCrud_1.dto.response.AuthUserResponse;

public interface UserService {

    public AuthUserResponse save(AuthUserRequest authUser);

}
