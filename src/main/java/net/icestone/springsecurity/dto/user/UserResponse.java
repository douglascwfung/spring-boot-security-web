package net.icestone.springsecurity.dto.user;

import java.util.List;

import net.icestone.springsecurity.common.Role;

public record UserResponse(
    String id,
    String username,
    String firstName,
    String lastName,
    List<Role> roles,
    Boolean active) {}
