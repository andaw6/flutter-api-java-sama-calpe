package com.ehacdev.flutter_api_java.utils;

import java.util.UUID;

public  class Utils {

    public static UUID convertUUID(String id){
        try {
            return UUID.fromString(id);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Invalid UUID format.", e);
        }
    }
}
    