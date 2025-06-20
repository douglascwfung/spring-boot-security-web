package net.icestone.springsecurity.service;

import net.icestone.springsecurity.dto.user.UserResponse;
import net.icestone.springsecurity.dto.user.UserResponseWithCredentials;
import net.icestone.springsecurity.security.dto.LoginDto;
import net.icestone.springsecurity.security.dto.TokenDto;
import net.icestone.springsecurity.security.exception.ApplicationAuthenticationException;
import net.icestone.springsecurity.security.user.AuthUser;
import net.icestone.springsecurity.security.user.AuthUserCache;
import java.util.UUID;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

  private final AuthUserCache authUserCache;

  private final UserService userService;

  private final PasswordEncoder passwordEncoder;

  public AuthService(
      AuthUserCache authUserCache, UserService userService, PasswordEncoder passwordEncoder) {
    this.authUserCache = authUserCache;
    this.userService = userService;
    this.passwordEncoder = passwordEncoder;
  }

  public TokenDto login(LoginDto loginDto) {

    UserResponseWithCredentials userCredentials =
        userService.getUserCredentialsByUsername(loginDto.username());

    if (!passwordEncoder.matches(loginDto.password(), userCredentials.passwordHash())) {
      throw new ApplicationAuthenticationException("Password is incorrect");
    }

    String token = UUID.randomUUID().toString();
    UserResponse userResponse = userCredentials.userResponse();
    AuthUser authUser = new AuthUser(userResponse.id(), userResponse.roles());

    authUserCache.login(token, authUser);

    return new TokenDto(token);
  }

  public void logout(String token) {

    authUserCache.logout(token);
  }
}
