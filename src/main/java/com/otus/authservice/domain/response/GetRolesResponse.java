package com.otus.authservice.domain.response;

import com.otus.authservice.domain.response.dto.RoleData;
import com.otus.authservice.util.enums.ApplicationError;

import java.util.ArrayList;
import java.util.List;

public class GetRolesResponse extends AResponse {

	private List<RoleData> roles = new ArrayList<>();

	public GetRolesResponse() {
	}

	public GetRolesResponse(List<RoleData> roles) {
		this.roles = roles;
	}

	public GetRolesResponse(ApplicationError errorContent) {
		super(errorContent.getErrorCode(), errorContent.getMessage());
	}

	public List<RoleData> getRoles() {
		return roles;
	}

	public void setRoles(List<RoleData> roles) {
		this.roles = roles;
	}
}
