package com.isp.authorizationserver.config;

import com.isp.authorizationserver.shared.constants.RoleAuthorization;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.server.authorization.client.JdbcRegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2AuthorizationServerConfigurer;
import org.springframework.security.oauth2.server.authorization.settings.AuthorizationServerSettings;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;

import static com.isp.authorizationserver.shared.constants.RoleAuthorization.ADMIN_AUTH_SERVER;
import static com.isp.authorizationserver.shared.constants.RoleAuthorization.APP;
import static com.isp.authorizationserver.shared.constants.endpoints.EndpointPaths.*;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class AuthorizationServerConfig {

    @Bean
    public RegisteredClientRepository registeredClientRepository(JdbcTemplate jdbcTemplate) {
        return new JdbcRegisteredClientRepository(jdbcTemplate);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverter() {
        JwtGrantedAuthoritiesConverter grantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
        grantedAuthoritiesConverter.setAuthorityPrefix(RoleAuthorization.SCOPE_PREFIX); // Requiere el prefijo que usaste en hasAuthority

        JwtAuthenticationConverter jwtConverter = new JwtAuthenticationConverter();
        jwtConverter.setJwtGrantedAuthoritiesConverter(grantedAuthoritiesConverter);
        return jwtConverter;
    }

    @Bean
    @Order(1)
    public SecurityFilterChain authorizationServerSecurityFilterChain(HttpSecurity http) throws Exception {
        OAuth2AuthorizationServerConfigurer authorizationServerConfigurer = new OAuth2AuthorizationServerConfigurer();
        http
                .securityMatcher(authorizationServerConfigurer.getEndpointsMatcher()) // SOLO para endpoints del Authorization Server
                .authorizeHttpRequests(auth -> auth.anyRequest().authenticated())
                .csrf(csrf -> csrf.ignoringRequestMatchers(AUTH.getBasePath()))
                .httpBasic(Customizer.withDefaults())
                .with(authorizationServerConfigurer, Customizer.withDefaults());
        return http.build();
    }

    @Bean
    @Order(2)
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http
                .oauth2ResourceServer(oauth2 ->
                        oauth2.jwt(jwt ->
                                jwt.jwtAuthenticationConverter(jwtAuthenticationConverter())))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.POST, CLIENT.getCreatePath())
                        .hasAuthority(ADMIN_AUTH_SERVER.getScopeWithPrefix())
                        .requestMatchers(HttpMethod.POST, ROLE.getCreatePath())
                        .hasAuthority(ADMIN_AUTH_SERVER.getScopeWithPrefix())
                        .requestMatchers(HttpMethod.GET, ROLE.getGetAllPath())
                        .hasAuthority(ADMIN_AUTH_SERVER.getScopeWithPrefix())
                        .requestMatchers(HttpMethod.POST, USER.getCreatePath())
                        .hasAuthority(APP.getScopeWithPrefix())
                        .requestMatchers(HttpMethod.GET, USER.getValidatePath())
                        .hasAuthority(APP.getScopeWithPrefix())
                        .requestMatchers(HttpMethod.GET, USER.getGetAllPath())
                        .hasAuthority(APP.getScopeWithPrefix())
                        .anyRequest().authenticated()
                )
                .csrf(AbstractHttpConfigurer::disable) // o ignora solo lo necesario
                .formLogin(AbstractHttpConfigurer::disable);
        return http.build();
    }

    @Bean
    public AuthorizationServerSettings authorizationServerSettings() {
        return AuthorizationServerSettings.builder().issuer("http://localhost:9000").build(); // usa valores por defecto (endpoints estándar)
    }
}
