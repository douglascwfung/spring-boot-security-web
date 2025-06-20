package net.icestone.springsecurity.dto.user;

public record UserResponseWithCredentials(UserResponse userResponse, String passwordHash) {}
