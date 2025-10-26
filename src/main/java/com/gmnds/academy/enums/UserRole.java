package com.gmnds.academy.enums;


public enum UserRole {
    ADMIN("admin"),
    STUDENT("student");

    private final String role;

    UserRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }

}
