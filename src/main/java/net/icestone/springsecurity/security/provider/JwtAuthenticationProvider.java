package net.icestone.springsecurity.security.provider;

import net.icestone.springsecurity.security.authentication.JwtAuthentication;
import net.icestone.springsecurity.security.service.jwt.JwtService;
import net.icestone.springsecurity.security.user.AuthUser;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;


@Component
public class JwtAuthenticationProvider implements AuthenticationProvider {


 private final JwtService jwtService;


 public JwtAuthenticationProvider(JwtService jwtService) {
   this.jwtService = jwtService;
 }


 @Override
 public Authentication authenticate(Authentication authentication) throws AuthenticationException {


   JwtAuthentication jwtAuthentication = (JwtAuthentication) authentication;


   AuthUser authUser = jwtService.resolveJwtToken(jwtAuthentication.jwtToken());


   return JwtAuthentication.authenticated(authUser);
 }


 @Override
 public boolean supports(Class<?> authentication) {
   return JwtAuthentication.class.isAssignableFrom(authentication);
 }
}