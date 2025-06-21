package net.icestone.springsecurity.security.user;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import net.icestone.springsecurity.common.Role;

public record AuthUser(String userId, List<Role> roles, String passwordHash) {

	  public Collection<? extends GrantedAuthority> getAuthorities() {
	    return roles.stream().map(role -> new SimpleGrantedAuthority(role.name())).toList();
	  }
	}

