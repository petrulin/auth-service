package com.otus.authservice.domain.response;

import com.otus.authservice.domain.response.dto.UserData;
import com.otus.authservice.util.enums.ApplicationError;
import java.util.ArrayList;
import java.util.List;

public class GetUsersResponse extends AResponse {

	private List<UserData> users = new ArrayList<>();

	public GetUsersResponse() {
	}

	public GetUsersResponse(ApplicationError errorContent) {
		super(errorContent.getErrorCode(), errorContent.getMessage());
	}

	public GetUsersResponse(List<UserData> users) {
		this.users = users;
	}

	public List<UserData> getUsers() {
		return users;
	}

	public void setUsers(List<UserData> users) {
		this.users = users;
	}
}
