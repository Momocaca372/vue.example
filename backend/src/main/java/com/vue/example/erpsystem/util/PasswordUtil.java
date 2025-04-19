package com.vue.example.erpsystem.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import io.github.cdimascio.dotenv.Dotenv;

public class PasswordUtil {

    private static final String SALT;

    static {
        Dotenv dotenv = Dotenv.configure().load();
        SALT = dotenv.get("SECURITY_SALT_KEY", "defaultSalt");
    }

    public static String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            String salted = SALT + password;
            byte[] hashedBytes = md.digest(salted.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(hashedBytes);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("SHA-512 加密失敗", e);
        }
    }
}
