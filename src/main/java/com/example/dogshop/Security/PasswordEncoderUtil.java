package com.example.dogshop.Security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public final class PasswordEncoderUtil {
    private static final BCryptPasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder();


    public static BCryptPasswordEncoder getInstance() {
        return PASSWORD_ENCODER;
    }

}