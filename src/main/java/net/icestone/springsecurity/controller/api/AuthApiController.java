package net.icestone.springsecurity.controller.api;

import net.icestone.springsecurity.common.AuthConstants;
import net.icestone.springsecurity.common.OpenApiConstants;
import net.icestone.springsecurity.security.dto.LoginDto;
import net.icestone.springsecurity.security.dto.TokenDto;
import net.icestone.springsecurity.service.AuthService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.servlet.http.HttpServletRequest;
import java.util.Optional;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

  @PreAuthorize("isAuthenticated()")
  @PostMapping("/logout")
  @SecurityRequirement(name = OpenApiConstants.TOKEN_SECURITY_REQUIREMENT)
  public void logout(HttpServletRequest httpServletRequest) {

    String token =
        Optional.ofNullable(httpServletRequest.getHeader(AuthConstants.AUTHORIZATION_HEADER))
            .orElseThrow();

    authService.logout(token);
  }
}
