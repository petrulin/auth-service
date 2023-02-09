package com.otus.authservice.domain.response;

import com.otus.authservice.util.enums.ApplicationError;

public class CreateRoleResponse extends AResponse {

	private Long id;
	private String name;

	public CreateRoleResponse() {
	}

	public CreateRoleResponse(ApplicationError errorContent) {
		super(errorContent.getErrorCode(), errorContent.getMessage());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
