package com.otus.authservice.controller;

import com.otus.authservice.domain.request.CreateRoleRequest;
import com.otus.authservice.domain.response.CreateRoleResponse;
import com.otus.authservice.domain.response.GetRolesResponse;
import com.otus.authservice.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.otus.authservice.util.enums.ApplicationError.AUTH_SERVICE_INTERNAL_ERROR;
import static org.springframework.http.HttpStatus.OK;

@Slf4j
@RestController
@RequestMapping("/auth-service/api/v1/")
public class RoleController {

	private final RoleService roleService;

	public RoleController(RoleService roleService) {
		this.roleService = roleService;
	}

	@PostMapping(path = "roles", consumes = "application/json", produces = "application/json")
	public ResponseEntity<CreateRoleResponse> createRole(@RequestBody CreateRoleRequest request) {
		try {
			return new ResponseEntity<>(roleService.createRole(request), OK);
		} catch(Exception ex) {
			log.error("::AuthService:: addRole error with message -> {}", ex.getLocalizedMessage());
			log.error("::AuthService:: addRole error with stackTrace -> {}", ExceptionUtils.getStackTrace(ex));
			return new ResponseEntity<>(new CreateRoleResponse(AUTH_SERVICE_INTERNAL_ERROR), OK);
		}
	}

	@GetMapping(path = "roles", produces = "application/json")
	public ResponseEntity<GetRolesResponse> getRoles() {
		try {
			return new ResponseEntity<>(roleService.getRoles(), OK);
		} catch(Exception ex) {
			log.error("::AuthService:: getRoles error with message -> {}", ex.getLocalizedMessage());
			log.error("::AuthService:: getRoles error with stackTrace -> {}", ExceptionUtils.getStackTrace(ex));
			return new ResponseEntity<>(new GetRolesResponse(AUTH_SERVICE_INTERNAL_ERROR), OK);
		}
	}
}
