package net.icestone.springsecurity.config;

import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import net.icestone.springsecurity.common.OpenApiConstants;

@Configuration
@SecurityScheme(
    name = OpenApiConstants.BASIC_SECURITY_REQUIREMENT,
    type = SecuritySchemeType.HTTP,
    scheme = "basic")
public class OpenApiConfig {}
