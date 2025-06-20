package net.icestone.springsecurity.security.authentication;

import java.util.Collection;
import java.util.stream.Collectors;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import net.icestone.springsecurity.security.user.AuthUser;

public record UserAuthentication(AuthUser authUser) implements Authentication {

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		   return authUser.roles().stream()
			       .map(Enum::name)
			       .map(SimpleGrantedAuthority::new)
			       .collect(Collectors.toSet());
		   
	}

	@Override
	public Object getCredentials() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getDetails() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getPrincipal() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isAuthenticated() {
		
		   // This value is set to true in this example because Authentication is used only to represent
		   // an authenticated user and not for transferring authentication details
		   return true;
	}

	@Override
	public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		
	}

}
