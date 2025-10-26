package com.gmnds.academy.dto;

import com.gmnds.academy.enums.UserRole;

public record RegisterDTO(String login, String password, UserRole role) {
}
