package com.ehacdev.flutter_api_java.datas.enums;

// public enum UserRole {
//     ADMIN,
//     AGENT,
//     VENDOR,
//     CLIENT
// }


public enum UserRole {
    ADMIN(false), // Assuming ADMIN is blocked by default
    AGENT(false),
    VENDOR(false),
    CLIENT(false);

    private final boolean isBlocked;

    // Constructor
    UserRole(boolean isBlocked) {
        this.isBlocked = isBlocked;
    }

    // Getter method
    public boolean isBlocked() {
        return isBlocked;
    }
}
