package com.otus.authservice.domain.response.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResponseMessage {

    private String senderMessageId;

    private String senderClientId;

    private Long notificationMessageId;

    private Long messageId;

    private String status;


}
