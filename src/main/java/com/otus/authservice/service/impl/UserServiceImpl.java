package com.otus.authservice.service.impl;

import com.otus.authservice.domain.request.ChangePasswordRequest;
import com.otus.authservice.domain.request.CreateUserRequest;
import com.otus.authservice.domain.request.dto.RoleDto;
import com.otus.authservice.domain.request.dto.UserDto;
import com.otus.authservice.domain.response.*;
import com.otus.authservice.domain.response.dto.RoleData;
import com.otus.authservice.domain.response.dto.UserData;
import com.otus.authservice.entity.Role;
import com.otus.authservice.entity.User;
import com.otus.authservice.exception.ApplicationException;
import com.otus.authservice.repository.UserRepository;
import com.otus.authservice.service.RoleService;
import com.otus.authservice.service.UserService;
import com.otus.authservice.util.enums.ApplicationError;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service("userService")

public class UserServiceImpl  implements UserService {

    private final UserRepository userRepository;
    private final RoleService roleService;
    private final PasswordEncoder bcryptEncoder;

    public UserServiceImpl(UserRepository userRepository, RoleService roleService, PasswordEncoder bcryptEncoder) {
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.bcryptEncoder = bcryptEncoder;
    }


// User methods:

    @Transactional
    public CreateUserResponse createUser(CreateUserRequest request) {
        var user = toUser(request);
        user.setEnabled(Boolean.TRUE);
        user.setAccountNonLocked(Boolean.FALSE);
        user.setAccountNonExpired(Boolean.FALSE);
        user.setCredentialsNonExpired(Boolean.FALSE);
        var saved = userRepository.save(user);

        return toCreateUserResponse(saved);
    }

    @Transactional
    public CreateUserResponse editUser(CreateUserRequest request) {
        User user = userRepository.findByUsername(request.getUsername());
        user.setFirstName(request.getFirstName());
        user.setMiddleName(request.getMiddleName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setMobilePhone(request.getMobilePhone());
        var saved = userRepository.save(user);

        return toCreateUserResponse(saved);
    }

    @Transactional
    public boolean deleteUsers(List<Long> ids) {
        return userRepository.deleteUsersByIdIn(userRepository.findIdsNotAdminUsersByIds(ids)) > 0;
    }

    public GetUsersResponse getUsers() {
        return new GetUsersResponse(userRepository.findAll().stream()
                .map(this::toUserData)
                .collect(Collectors.toList())
        );
    }

    public GetUserResponse getUser(Long userId) {
        return toGetUserResponse(
                userRepository.findById(userId).orElseThrow(
                        () -> new ApplicationException(ApplicationError.USER_NOT_FOUND)
                )
        );
    }

    public UserResponse getUserByUsername(String userName) {

        User user = userRepository.findByUsername(userName);
        UserResponse ur = new UserResponse();
        if (user != null) {
            BeanUtils.copyProperties(user, ur, "roles");
        }
        return ur;

    }

    public EnableActionUserResponse userEnableAction(Long userId, Boolean enableAction) {
        var user = userRepository.findById(userId).orElseThrow(() -> new ApplicationException(ApplicationError.USER_NOT_FOUND));

        if (user.getRoles().stream().anyMatch(roleService::isAdmin)) {
            throw new ApplicationException(ApplicationError.ADMIN_CAN_NOT_BE_DISABLED);
        }

        user.setEnabled(enableAction);
        return toEnableActionUserResponse(userRepository.save(user)) ;
    }

    private UserDto toUserDto(User user) {
        var userDto = new UserDto();
        BeanUtils.copyProperties(user, userDto, "roles");

        List<RoleDto> roles = user.getRoles().stream()
                //.map(this::toRoleDto)
                .map(roleService::toRoleDto)
                .collect(Collectors.toList());

        userDto.setRoles(roles);

        return userDto;
    }

    private User toUser(UserDto userDto) {
        var user = new User();
        BeanUtils.copyProperties(userDto, user, "password", "roles");

        user.setPassword(toEncode(userDto.getPassword()));
        List<Role> roles = userDto.getRoles().stream()
                .map(roleService::toRole)
                .collect(Collectors.toList());

        user.setRoles(roles);

        return user;
    }

    private User toUser(CreateUserRequest request) {
        Long id   = request.getId();
        User user = id == null ? new User() : userRepository.findById(id).get();
        BeanUtils.copyProperties(request, user, "password", "roles");

        if (id == null) {
            user.setPassword(toEncode(request.getPassword()));
        }

        List<Role> roles = request.getRoles().stream()
                .map(role -> roleService.getRoleById(role.getId()))
                .collect(Collectors.toList());

        user.setRoles(roles);

        return user;
    }


    private UserData toUserData(User user) {
        var userData = new UserData();
        BeanUtils.copyProperties(user, userData, "roles");

        List<RoleData> roles = user.getRoles().stream()
                .map(roleService::toRoleData)
                .collect(Collectors.toList());

        userData.setRoles(roles);

        return userData;
    }


    private CreateUserResponse toCreateUserResponse(User user) {
        var response = new CreateUserResponse();

        if (user == null) {
            return response;
        }

        BeanUtils.copyProperties(user, response, "roles");

        List<RoleData> roles = user.getRoles().stream()
                .map(roleService::toRoleData)
                .collect(Collectors.toList());

        response.setRoles(roles);
        response.setOperationTime(LocalDateTime.now());

        return response;
    }

    private GetUserResponse toGetUserResponse(User user) {
        var response = new GetUserResponse();
        BeanUtils.copyProperties(user, response, "roles");

        List<RoleData> roles = user.getRoles().stream()
                .map(roleService::toRoleData)
                .collect(Collectors.toList());

        response.setRoles(roles);

        return response;
    }

    private EnableActionUserResponse toEnableActionUserResponse(User user) {
        var response = new EnableActionUserResponse();

        response.setId(user.getId());
        response.setIsEnabled(user.getEnabled());

        return response;
    }

    private String toEncode(String password) {
        return bcryptEncoder.encode(password);
    }
    public ChangePasswordResponse changePassword(ChangePasswordRequest request) {
        Integer result = userRepository.changePassword(request.getId(), toEncode(request.getPassword()));
        return new ChangePasswordResponse(request.getId(), result != 0);
    }


}
