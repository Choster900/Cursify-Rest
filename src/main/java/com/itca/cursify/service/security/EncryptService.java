package com.itca.cursify.service.security;

public interface EncryptService {
    String scryptPassoword(String password);
    boolean verifyPassword(String originalPassoword,String hashPassword);
}
