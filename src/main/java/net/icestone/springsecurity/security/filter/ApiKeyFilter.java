package net.icestone.springsecurity.security.filter;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpServletRequest;
import net.icestone.springsecurity.common.AuthConstants;
import net.icestone.springsecurity.security.authentication.ApiKeyAuthentication;


@Component
public class ApiKeyFilter extends AbstractAuthenticationCreationFilter {


 @Override
 protected Authentication buildAuthentication(HttpServletRequest request) {


   String apiKey = request.getHeader(AuthConstants.API_KEY_AUTHORIZATION_HEADER);


   if (apiKey == null || apiKey.isEmpty()) {
     return null;
   }


   return ApiKeyAuthentication.unauthenticated(apiKey);
 }
}