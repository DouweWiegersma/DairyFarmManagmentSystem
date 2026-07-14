package nl.wiegersma.dairyfarm.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.core.DelegatingOAuth2TokenValidator;
import org.springframework.security.oauth2.core.OAuth2TokenValidator;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.security.oauth2.jwt.JwtAudienceValidator;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;



import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@Configuration
public class SecurityConfig {

    @Value("${issuer-uri}")
    private String issuer;
    @Value("${audience}")
    private String audience;
    @Value("${client-id}")
    private String clientId;



    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        return http
                .httpBasic(hp -> hp.disable())
                .csrf(csrf->csrf.disable())
                .cors(cors->{
                    CorsConfiguration corsConfiguration = new CorsConfiguration();
                    corsConfiguration.setAllowedOrigins(List.of("http://localhost:3000"));
                    corsConfiguration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE"));
                    corsConfiguration.setAllowedHeaders(List.of("Authorization", "Content-Type"));
                    corsConfiguration.setAllowCredentials(true);
                    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
                    source.registerCorsConfiguration("/**", corsConfiguration);
                    cors.configurationSource(source);

                })
                .oauth2ResourceServer(oauth2 -> oauth2
                        .jwt(jwt -> jwt
                                .jwtAuthenticationConverter(jwtAuthenticationConverter())
                                .decoder(jwtDecoder())
                        ))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.GET, "/clawtreatments/**").hasAnyAuthority("ADMIN", "EMPLOYEE")
                        .requestMatchers(HttpMethod.POST, "/clawtreatments/**").hasAnyAuthority("ADMIN", "EMPLOYEE")
                        .requestMatchers(HttpMethod.PUT, "/clawtreatments/**").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/clawtreatments/**").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/cows/**").hasAnyAuthority("ADMIN", "EMPLOYEE")
                        .requestMatchers(HttpMethod.POST, "/cows/**").hasAnyAuthority("ADMIN", "EMPLOYEE")
                        .requestMatchers(HttpMethod.PUT, "/cows/**").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/cows/**").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/diseases/**").hasAnyAuthority("ADMIN", "EMPLOYEE")
                        .requestMatchers(HttpMethod.POST, "/diseases/**").hasAnyAuthority("ADMIN", "EMPLOYEE")
                        .requestMatchers(HttpMethod.PUT, "/diseases/**").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/diseases/**").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/medications/**").hasAnyAuthority("ADMIN", "EMPLOYEE")
                        .requestMatchers(HttpMethod.POST, "/medications/**").hasAnyAuthority("ADMIN", "EMPLOYEE")
                        .requestMatchers(HttpMethod.PUT, "/medications/**").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/medications/**").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/medicationinventories/**").hasAnyAuthority("ADMIN", "EMPLOYEE")
                        .requestMatchers(HttpMethod.POST, "/medicationinventories/**").hasAnyAuthority("ADMIN", "EMPLOYEE")
                        .requestMatchers(HttpMethod.PUT, "/medicationinventories/**").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/medicationinventories/**").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/treatments/**").hasAnyAuthority("ADMIN", "EMPLOYEE")
                        .requestMatchers(HttpMethod.POST, "/treatments/**").hasAnyAuthority("ADMIN", "EMPLOYEE")
                        .requestMatchers(HttpMethod.PUT, "/treatments/**").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/treatments/**").hasAuthority("ADMIN")
                        .anyRequest().denyAll()
                )
                .sessionManagement(session-> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .build();
    }


    public JwtDecoder jwtDecoder(){
        NimbusJwtDecoder jwtDecoder = JwtDecoders.fromOidcIssuerLocation(issuer);

        OAuth2TokenValidator<Jwt> audienceValidator = new JwtAudienceValidator(audience);
        OAuth2TokenValidator<Jwt> withIssuer = JwtValidators.createDefaultWithIssuer(issuer);
        OAuth2TokenValidator<Jwt> withAudience = new DelegatingOAuth2TokenValidator<>(withIssuer, audienceValidator);
        jwtDecoder.setJwtValidator(withAudience);
        return jwtDecoder;
    }


    public JwtAuthenticationConverter jwtAuthenticationConverter() {

        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(new Converter<>() {
            @Override
            public Collection<GrantedAuthority> convert(Jwt source) {
                Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>();
                for (String authority : getAuthorities(source)) {
                    grantedAuthorities.add(new SimpleGrantedAuthority( authority));
                }
                return grantedAuthorities;
            }
            private List<String> getAuthorities(Jwt jwt){
                Map<String, Object> resourceAcces = jwt.getClaim("resource_access");
                if (resourceAcces != null) {
                    if (resourceAcces.get(clientId) instanceof Map) {
                        Map<String, Object> client = (Map<String, Object>) resourceAcces.get(clientId);
                        if (client != null && client.containsKey("roles")) {
                            return (List<String>) client.get("roles");
                        }
                    } else {
                        Map<String, Object> realmAcces = jwt.getClaim("realm_access");
                        if (realmAcces != null && realmAcces.containsKey("roles")) {
                            return (List<String>) realmAcces.get("roles");
                        }
                        return new ArrayList<>();
                    }
                }
                return new ArrayList<>();
            }
        });
        return jwtAuthenticationConverter;
    }

}