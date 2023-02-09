package com.otus.authservice.domain.response;

import com.otus.authservice.util.enums.ApplicationError;

public class ChangePasswordResponse extends AResponse {

    private Long id;
    private Boolean isChanged;

    public ChangePasswordResponse(Long id, Boolean isChanged) {
        this.id = id;
        this.isChanged = isChanged;
    }

    public ChangePasswordResponse(ApplicationError errorContent) {
        super(errorContent.getErrorCode(), errorContent.getMessage());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getChanged() {
        return isChanged;
    }

    public void setChanged(Boolean changed) {
        isChanged = changed;
    }
}
