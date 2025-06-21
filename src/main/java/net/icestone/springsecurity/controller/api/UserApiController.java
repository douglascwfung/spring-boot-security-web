package net.icestone.springsecurity.controller.api;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import net.icestone.springsecurity.common.OpenApiConstants;

@RestController
@RequestMapping("/api/users")
public class UserApiController {

  @PreAuthorize("isAuthenticated()")
  @GetMapping("/me")
  @SecurityRequirement(name = OpenApiConstants.BASIC_SECURITY_REQUIREMENT)
  public User getCurrentUser(@AuthenticationPrincipal User user) {
    return user;
  }
}