package com.otus.authservice.service;

import com.otus.authservice.entity.Role;
import com.otus.authservice.domain.request.CreateRoleRequest;
import com.otus.authservice.domain.request.dto.RoleDto;
import com.otus.authservice.domain.response.CreateRoleResponse;
import com.otus.authservice.domain.response.GetRolesResponse;
import com.otus.authservice.domain.response.dto.RoleData;

public interface RoleService {

    Role toRole(RoleDto roleDto);
    RoleDto toRoleDto(Role role);
    RoleData toRoleData(Role role);
    boolean isAdmin(Role role);
    CreateRoleResponse createRole(CreateRoleRequest request);
    GetRolesResponse getRoles();
    Role getRoleById(Long roleId);
}
