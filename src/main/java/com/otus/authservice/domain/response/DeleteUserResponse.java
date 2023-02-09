package com.otus.authservice.domain.response;

import com.otus.authservice.util.enums.ApplicationError;

public class DeleteUserResponse extends AResponse {

	private boolean isSuccessful;

	public DeleteUserResponse() {
	}

	public DeleteUserResponse(boolean isSuccessful) {
		this.isSuccessful = isSuccessful;
	}

	public DeleteUserResponse(ApplicationError errorContent) {
		super(errorContent.getErrorCode(), errorContent.getMessage());
	}

	public boolean getIsSuccessful() {
		return isSuccessful;
	}

	public void setIsSuccessful(boolean isSuccessful) {
		this.isSuccessful = isSuccessful;
	}
}
