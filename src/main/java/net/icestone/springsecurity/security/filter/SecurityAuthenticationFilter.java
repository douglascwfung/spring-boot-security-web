package net.icestone.springsecurity.security.filter;

import net.icestone.springsecurity.common.AuthConstants;
import net.icestone.springsecurity.security.authentication.UserAuthentication;
import net.icestone.springsecurity.security.exception.TokenAuthenticationException;
import net.icestone.springsecurity.security.user.AuthUser;
import net.icestone.springsecurity.security.user.AuthUserCache;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class SecurityAuthenticationFilter extends OncePerRequestFilter {

  private final AuthUserCache authUserCache;

  public SecurityAuthenticationFilter(AuthUserCache authUserCache) {
    this.authUserCache = authUserCache;
  }

  @Override
  protected void doFilterInternal(
      HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {

    String authenticationHeader = request.getHeader(AuthConstants.AUTHORIZATION_HEADER);

    if (authenticationHeader == null) {
      // Authentication token is not present, let's rely on anonymous authentication
      filterChain.doFilter(request, response);
      return;
    }

    AuthUser authUser =
        authUserCache
            .getByToken(authenticationHeader)
            .orElseThrow(() -> new TokenAuthenticationException("Token is not valid"));

    UserAuthentication authentication = new UserAuthentication(authUser);

    SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
    securityContext.setAuthentication(authentication);
    SecurityContextHolder.setContext(securityContext);

    filterChain.doFilter(request, response);
  }
}
