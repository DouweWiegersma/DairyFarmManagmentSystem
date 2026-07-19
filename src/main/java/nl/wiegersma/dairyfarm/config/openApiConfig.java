package nl.wiegersma.dairyfarm.config;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.customizers.OperationCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;


import java.util.List;

@Configuration
public class openApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        final String securitySchemeName = "BearerAuth";

        return new OpenAPI()
                .info(new Info().title("Dairy Farm Management System API").version("1.0"))
                .components(new Components()
                        .addSecuritySchemes(securitySchemeName, new SecurityScheme()
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")
                                .description("Voer hier je Keycloak/Auth0 JWT token in.")))
                .addSecurityItem(new SecurityRequirement().addList(securitySchemeName));
    }

    @Bean
    public OperationCustomizer customizeTagsOnly() {
        return (operation, handlerMethod) -> {
            boolean isPut = handlerMethod.hasMethodAnnotation(PutMapping.class);
            boolean isDelete = handlerMethod.hasMethodAnnotation(DeleteMapping.class);

            boolean isAdminOnly = isPut || isDelete;

            if (isPut && handlerMethod.getBeanType().getSimpleName().toLowerCase().contains("cowphoto")) {
                isAdminOnly = false;
            }

            if (isAdminOnly) {
                operation.setTags(List.of("2. Admin API"));
                operation.setDescription("Vereiste rechten: ADMIN");
            } else {
                operation.setTags(List.of("1. Employee & Admin API"));
                operation.setDescription("Vereiste rechten: EMPLOYEE of ADMIN");
            }

            return operation;
        };
    }
}
