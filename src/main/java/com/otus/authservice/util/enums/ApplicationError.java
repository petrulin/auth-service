package com.otus.authservice.util.enums;

public enum ApplicationError {

    SUCCESS("", 0),

	EMAIL_SERVICE_UNAVAILABLE("Client not found", -500),
	CLIENT_NOT_FOUND("Client not found", -501),
	USER_NOT_FOUND("User not found", -502),
	ADMIN_CAN_NOT_BE_DISABLED("Admin user can not be disabled", -503),

    WRONG_API_CLIENT_TOKEN("Wrong client token", -504),
    AUTH_SERVICE_INTERNAL_ERROR("Auth service internal error", -700);


    ApplicationError(String message, int errorCode) {
        this.message = message;
        this.errorCode = errorCode;
    }

    private String message;
    private int errorCode;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }
}
