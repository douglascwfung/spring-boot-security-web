package net.icestone.springsecurity.dto.user;

public record UserPasswordUpdateRequest(String oldPassword, String newPassword) {}
