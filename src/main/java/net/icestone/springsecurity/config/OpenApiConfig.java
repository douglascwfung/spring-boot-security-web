package net.icestone.springsecurity.config;

import net.icestone.springsecurity.common.AuthConstants;
import net.icestone.springsecurity.common.OpenApiConstants;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.context.annotation.Configuration;

@Configuration
@SecurityScheme(
   name = OpenApiConstants.BEARER_TOKEN_SECURITY_REQUIREMENT,
   type = SecuritySchemeType.APIKEY,
   in = SecuritySchemeIn.HEADER,
   paramName = AuthConstants.JWT_AUTHORIZATION_HEADER)
@SecurityScheme(
   name = OpenApiConstants.API_KEY_SECURITY_REQUIREMENT,
   type = SecuritySchemeType.APIKEY,
   in = SecuritySchemeIn.HEADER,
   paramName = AuthConstants.API_KEY_AUTHORIZATION_HEADER)
public class OpenApiConfig {}