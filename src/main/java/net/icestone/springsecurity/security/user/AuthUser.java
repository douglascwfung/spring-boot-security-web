package net.icestone.springsecurity.security.user;

import java.util.List;

import net.icestone.springsecurity.common.Role;

public record AuthUser(String userId, List<Role> roles) {

}
