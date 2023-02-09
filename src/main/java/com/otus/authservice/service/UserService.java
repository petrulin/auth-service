package com.otus.authservice.service;

import com.otus.authservice.domain.request.ChangePasswordRequest;
import com.otus.authservice.domain.request.CreateUserRequest;
import com.otus.authservice.domain.response.*;

import java.util.List;

public interface UserService {
    ChangePasswordResponse changePassword(ChangePasswordRequest request);
    CreateUserResponse createUser(CreateUserRequest request);

    CreateUserResponse editUser(CreateUserRequest request);
    boolean deleteUsers(List<Long> ids);
    GetUserResponse getUser(Long userId);
    UserResponse getUserByUsername(String userName);
    GetUsersResponse getUsers();
    EnableActionUserResponse userEnableAction(Long userId, Boolean enableAction);
}
