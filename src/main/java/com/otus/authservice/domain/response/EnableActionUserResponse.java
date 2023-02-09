package com.otus.authservice.domain.response;

import com.otus.authservice.exception.ApplicationException;
import com.otus.authservice.util.enums.ApplicationError;

public class EnableActionUserResponse extends AResponse {

	private Long    id;
	private Boolean isEnabled;

	public EnableActionUserResponse() {
	}

	public EnableActionUserResponse(ApplicationError errorContent) {
		super(errorContent.getErrorCode(), errorContent.getMessage());
	}

	public EnableActionUserResponse(Integer code, String message) {
		super(code, message);
	}

	public EnableActionUserResponse(ApplicationException exception) {
		this(exception.getCode(), exception.getMessage());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Boolean getIsEnabled() {
		return isEnabled;
	}

	public void setIsEnabled(Boolean isEnabled) {
		this.isEnabled = isEnabled;
	}
}
