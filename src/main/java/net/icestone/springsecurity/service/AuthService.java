package net.icestone.springsecurity.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import net.icestone.springsecurity.dto.user.UserResponse;
import net.icestone.springsecurity.dto.user.UserResponseWithCredentials;
import net.icestone.springsecurity.security.dto.LoginDto;
import net.icestone.springsecurity.security.dto.TokenDto;
import net.icestone.springsecurity.security.exception.ApplicationAuthenticationException;
import net.icestone.springsecurity.security.service.jwt.JwtService;
import net.icestone.springsecurity.security.user.AuthUser;
import net.icestone.springsecurity.security.user.AuthUserType;

@Service
public class AuthService {

  private final UserService userService;

  private final PasswordEncoder passwordEncoder;

  private final JwtService jwtService;

  public AuthService(
      UserService userService, PasswordEncoder passwordEncoder, JwtService jwtService) {
    this.userService = userService;
    this.passwordEncoder = passwordEncoder;
    this.jwtService = jwtService;
  }

  public TokenDto login(LoginDto loginDto) {

    UserResponseWithCredentials userCredentials =
        userService.getUserCredentialsByUsername(loginDto.username());

    if (!passwordEncoder.matches(loginDto.password(), userCredentials.passwordHash())) {
      throw new ApplicationAuthenticationException("Password is incorrect");
    }

    UserResponse userResponse = userCredentials.userResponse();
    AuthUser authUser =
        new AuthUser(userResponse.id(), userResponse.roles(), AuthUserType.INTERNAL);

    String jwtToken = jwtService.createJwtToken(authUser);

    return new TokenDto(jwtToken);
  }
}
