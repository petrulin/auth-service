package com.otus.authservice.service.impl;

import com.otus.authservice.entity.Role;
import com.otus.authservice.repository.RoleRepository;
import com.otus.authservice.domain.request.CreateRoleRequest;
import com.otus.authservice.domain.request.dto.RoleDto;
import com.otus.authservice.domain.response.CreateRoleResponse;
import com.otus.authservice.domain.response.GetRolesResponse;
import com.otus.authservice.domain.response.dto.RoleData;
import com.otus.authservice.service.RoleService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import java.util.stream.Collectors;


@Service("roleService")

public class RoleServiceImpl  implements RoleService{

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public CreateRoleResponse createRole(CreateRoleRequest request) {
        return toCreateRoleResponse(
                roleRepository.save(toRole(request))
        );
    }

    private Role toRole(CreateRoleRequest request) {
        var role = new Role();
        BeanUtils.copyProperties(request, role);
        return role;
    }

    private CreateRoleResponse toCreateRoleResponse(Role role) {
        var response = new CreateRoleResponse();
        BeanUtils.copyProperties(role, response);

        return response;
    }

    @Override
    public GetRolesResponse getRoles() {
        return new GetRolesResponse(roleRepository.findAll().stream()
                .map(this::toRoleData)
                .collect(Collectors.toList())
        );
    }

    @Override
    public Role toRole(RoleDto roleDto) {
        var role = new Role();
        BeanUtils.copyProperties(roleDto, role);
        return role;
    }

    @Override
    public RoleDto toRoleDto(Role role) {
        var dto = new RoleDto();
        BeanUtils.copyProperties(role, dto);
        return dto;
    }

    @Override
    public RoleData toRoleData(Role role) {
        var data = new RoleData();
        BeanUtils.copyProperties(role, data);
        return data;
    }

    @Override
    public boolean isAdmin(Role role) {
        return "role_admin".equals(role.getName());
    }

    @Override
    public Role getRoleById(Long roleId) {
        return roleRepository.findById(roleId).get();

    }


}
