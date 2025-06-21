package net.icestone.springsecurity.security;

import net.icestone.springsecurity.dto.user.UserResponseWithCredentials;
import net.icestone.springsecurity.security.user.AuthUser;
import net.icestone.springsecurity.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

  private final UserService userService;

  public UserDetailsServiceImpl(UserService userService) {
    this.userService = userService;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

    UserResponseWithCredentials userCredentialsByUsername =
        userService.getUserCredentialsByUsername(username);

    return new AuthUser(
        userCredentialsByUsername.userResponse().id(),
        userCredentialsByUsername.userResponse().roles(),
        userCredentialsByUsername.passwordHash());
  }
}