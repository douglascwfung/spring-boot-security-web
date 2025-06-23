package net.icestone.springsecurity.controller.api;

import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import net.icestone.springsecurity.common.OpenApiConstants;
import net.icestone.springsecurity.dto.user.UserCreateRequest;
import net.icestone.springsecurity.dto.user.UserPasswordUpdateRequest;
import net.icestone.springsecurity.dto.user.UserResponse;
import net.icestone.springsecurity.dto.user.UserUpdateRequest;
import net.icestone.springsecurity.security.user.AuthUser;
import net.icestone.springsecurity.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserApiController {

  private final UserService userService;

  public UserApiController(UserService userService) {
    this.userService = userService;
  }

  @PreAuthorize("permitAll()")
  @PostMapping
  public UserResponse createUser(@RequestBody UserCreateRequest userCreateRequest) {

    return userService.createUser(userCreateRequest);
  }

  @PreAuthorize("isAuthenticated()")
  @PostMapping("/change-password")
  @SecurityRequirements({
    @SecurityRequirement(name = OpenApiConstants.BEARER_TOKEN_SECURITY_REQUIREMENT),
    @SecurityRequirement(name = OpenApiConstants.API_KEY_SECURITY_REQUIREMENT)
  })
  public void changeUserPassword(
      @AuthenticationPrincipal AuthUser authUser,
      @RequestBody UserPasswordUpdateRequest passwordUpdateRequest) {
    userService.changeUserPassword(passwordUpdateRequest, authUser);
  }

  @PreAuthorize("isAuthenticated() && #userId == authentication.principal.userId")
  @PutMapping("/{id}")
  @SecurityRequirements({
    @SecurityRequirement(name = OpenApiConstants.BEARER_TOKEN_SECURITY_REQUIREMENT),
    @SecurityRequirement(name = OpenApiConstants.API_KEY_SECURITY_REQUIREMENT)
  })
  public UserResponse updateUser(
      @PathVariable("id") String userId, @RequestBody UserUpdateRequest userUpdateRequest) {
    return userService.updateUser(userId, userUpdateRequest);
  }

  // check isAuthenticated(), because authentication.principal is String for anonymous
  // authentication
  // Spring SPEL uses the same parameter names as method parameter names.
  // Yes, there is a /me endpoint, but we also allow users to get user data by ID just for
  // demonstration purposes
  @PreAuthorize(
      "isAuthenticated() && (hasAuthority('ROLE_ADMIN') || (#userId == authentication.principal.userId))")
  @GetMapping("/{id}")
  @SecurityRequirements({
    @SecurityRequirement(name = OpenApiConstants.BEARER_TOKEN_SECURITY_REQUIREMENT),
    @SecurityRequirement(name = OpenApiConstants.API_KEY_SECURITY_REQUIREMENT)
  })
  public UserResponse getUserById(@PathVariable("id") String userId) {
    return userService.getUserById(userId);
  }

  @PreAuthorize("isAuthenticated()")
  @GetMapping("/me")
  @SecurityRequirements({
    @SecurityRequirement(name = OpenApiConstants.BEARER_TOKEN_SECURITY_REQUIREMENT),
    @SecurityRequirement(name = OpenApiConstants.API_KEY_SECURITY_REQUIREMENT)
  })
  public UserResponse getCurrentUser(@AuthenticationPrincipal AuthUser authUser) {
    return userService.getUserById(authUser.userId());
  }

  @PreAuthorize("hasRole('ROLE_ADMIN')")
  @GetMapping
  @SecurityRequirements({
    @SecurityRequirement(name = OpenApiConstants.BEARER_TOKEN_SECURITY_REQUIREMENT),
    @SecurityRequirement(name = OpenApiConstants.API_KEY_SECURITY_REQUIREMENT)
  })
  public Page<UserResponse> listUsers(@PageableDefault @ParameterObject Pageable pageable) {
    return userService.listUsers(pageable);
  }

  @PreAuthorize("hasRole('ROLE_ADMIN')")
  @PostMapping("/{id}/activate")
  @SecurityRequirements({
    @SecurityRequirement(name = OpenApiConstants.BEARER_TOKEN_SECURITY_REQUIREMENT),
    @SecurityRequirement(name = OpenApiConstants.API_KEY_SECURITY_REQUIREMENT)
  })
  public UserResponse activateUser(@PathVariable("id") String userId) {
    return userService.activateUser(userId);
  }

  @PreAuthorize("hasRole('ROLE_ADMIN')")
  @PostMapping("/{id}/deactivate")
  @SecurityRequirements({
    @SecurityRequirement(name = OpenApiConstants.BEARER_TOKEN_SECURITY_REQUIREMENT),
    @SecurityRequirement(name = OpenApiConstants.API_KEY_SECURITY_REQUIREMENT)
  })
  public UserResponse deactivateUser(@PathVariable("id") String userId) {
    return userService.deactivateUser(userId);
  }

  @PreAuthorize("hasRole('ROLE_ADMIN')")
  @PostMapping("/{id}/promote")
  @SecurityRequirements({
    @SecurityRequirement(name = OpenApiConstants.BEARER_TOKEN_SECURITY_REQUIREMENT),
    @SecurityRequirement(name = OpenApiConstants.API_KEY_SECURITY_REQUIREMENT)
  })
  public UserResponse promoteUserToAdmin(@PathVariable("id") String userId) {
    return userService.promoteUserToAdmin(userId);
  }
}
