package net.icestone.springsecurity.controller.api;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.icestone.springsecurity.security.dto.LoginDto;
import net.icestone.springsecurity.security.dto.TokenDto;
import net.icestone.springsecurity.service.AuthService;

@RestController
@RequestMapping("/api/auth")
public class AuthApiController {

  private final AuthService authService;

  public AuthApiController(AuthService authService) {
    this.authService = authService;
  }

  @PreAuthorize("isAnonymous()")
  @PostMapping("/login")
  public TokenDto login(@RequestBody LoginDto loginDto) {

    return authService.login(loginDto);
  }

}
