package com.otus.authservice.domain.response.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class ApiClientDTO {

    private Long id;
    private String clientName;
    private String clientToken;
    private LocalDateTime createdAt;
    private LocalDateTime modify;
    private String sysName;

}
