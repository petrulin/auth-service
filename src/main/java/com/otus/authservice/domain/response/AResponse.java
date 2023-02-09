package com.otus.authservice.domain.response;

import com.otus.authservice.util.enums.ApplicationError;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static java.time.LocalDateTime.now;

public abstract class AResponse implements IResponse {

    protected LocalDateTime operationTime = now();

	protected int errorCode;

	protected String errorMessage = "";
    protected AResponse() {
        this.errorCode = ApplicationError.SUCCESS.getErrorCode();
        this.errorMessage = ApplicationError.SUCCESS.getMessage();
        this.operationTime = LocalDateTime.now();
    }
    protected AResponse(ApplicationError applicationError) {
        this.operationTime = LocalDateTime.now();
        this.errorCode = applicationError.getErrorCode();
        this.errorMessage = applicationError.getMessage();
    }
    public AResponse(int errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public String getOperationTime() {
        return operationTime.format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss"));
    }

    public void setOperationTime(LocalDateTime operationTime) {
        this.operationTime = operationTime;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
