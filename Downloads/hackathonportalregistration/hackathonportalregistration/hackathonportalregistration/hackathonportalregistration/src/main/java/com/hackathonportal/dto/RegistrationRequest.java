package com.hackathonportal.dto;

import lombok.Data;

@Data
public class RegistrationRequest {
    private Long userId;
    private Long hackathonId;
}