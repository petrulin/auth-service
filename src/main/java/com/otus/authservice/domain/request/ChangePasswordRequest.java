package com.otus.authservice.domain.request;

public class ChangePasswordRequest {
    private Long id;
    private String password;

    public ChangePasswordRequest() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
