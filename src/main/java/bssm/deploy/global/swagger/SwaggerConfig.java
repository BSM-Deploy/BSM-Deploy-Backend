package bssm.deploy.global.swagger;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static bssm.deploy.global.swagger.SwaggerConfigConstants.*;

@Configuration
public class SwaggerConfig {

	@Value("${token.access-token.name}")
	private String ACCESS_TOKEN_NAME;

	@Bean
	public OpenAPI openAPI() {
		return new OpenAPI()
				.info(getApiInfo())
				.components(getComponents());
	}

	private Components getComponents() {
		return new Components()
				.addSecuritySchemes(JWT, getJwtSecurityScheme());
	}

	private SecurityScheme getJwtSecurityScheme() {
		return new SecurityScheme()
				.type(SecurityScheme.Type.APIKEY)
				.in(SecurityScheme.In.HEADER)
				.name(ACCESS_TOKEN_NAME);
	}

	@Bean
	public OpenApiCustomizer openApiCustomizer() {
		return OpenApi -> OpenApi
				.addSecurityItem(getSecurityItem());
	}

	private SecurityRequirement getSecurityItem() {
		return new SecurityRequirement()
				.addList(JWT);
	}

	private Info getApiInfo() {
		return new Info()
				.title(TITLE)
				.description(DESCRIPTION)
				.version(VERSION);
	}

}
