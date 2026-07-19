package nl.wiegersma.dairyfarm.config;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
    public GroupedOpenApi employeeApi() {
        return GroupedOpenApi.builder()
                .group("Employee API panel")
                .addOperationCustomizer((operation, handlerMethod) -> {
                    boolean isPut = handlerMethod.hasMethodAnnotation(PutMapping.class);
                    boolean isGet = handlerMethod.hasMethodAnnotation(GetMapping.class);
                    boolean isPost = handlerMethod.hasMethodAnnotation(PostMapping.class);

                    String className = handlerMethod.getBeanType().getSimpleName();
                    boolean isCowPhotoPut = isPut && className.toLowerCase().contains("cowphoto");

                    if (!(isGet || isPost || isCowPhotoPut)) {
                        return null;
                    }

                    String cleanTagName = className;
                    operation.setTags(List.of(cleanTagName));

                    return operation;
                })
                .build();
    }

    @Bean
    public GroupedOpenApi adminApi() {
        return GroupedOpenApi.builder()
                .group("Admin API panel")
                .addOperationCustomizer((operation, handlerMethod) -> {
                    boolean isPut = handlerMethod.hasMethodAnnotation(PutMapping.class);
                    boolean isDelete = handlerMethod.hasMethodAnnotation(DeleteMapping.class);

                    String className = handlerMethod.getBeanType().getSimpleName();
                    boolean isCowPhotoPut = isPut && className.toLowerCase().contains("cowphoto");

                    String cleanTagName =  className;
                    operation.setTags(List.of(cleanTagName));

                    if ((isPut || isDelete) && !isCowPhotoPut) {
                        operation.setDescription("**Toegang:** Uitsluitend voor BEHEERDERS (Admin)");
                    } else {
                        operation.setDescription("**Toegang:** Medewerkers & Admins");
                    }
                    return operation;
                })
                .build();
    }
}
