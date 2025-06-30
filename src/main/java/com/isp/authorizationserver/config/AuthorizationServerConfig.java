package com.isp.authorizationserver.config;

import com.isp.authorizationserver.config.security.ClientIdCaptureFilter;
import com.isp.authorizationserver.config.security.CustomUserDetailsService;
import com.isp.authorizationserver.shared.constants.EndPoints;
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
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static com.isp.authorizationserver.shared.constants.RoleAuthorization.ADMIN_AUTH_SERVER;
import static com.isp.authorizationserver.shared.constants.RoleAuthorization.APP;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class AuthorizationServerConfig {

    private final CustomUserDetailsService customUserDetailsService;

    public AuthorizationServerConfig(CustomUserDetailsService customUserDetailsService) {
        this.customUserDetailsService = customUserDetailsService;
    }

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
                .csrf(AbstractHttpConfigurer::disable) // Desactiva CSRF para permitir peticiones como POST /oauth2/token sin necesidad de token CSRF
                .httpBasic(Customizer.withDefaults()) // Requiere autenticación Basic para /oauth2/token (usado por client_id + client_secret)
                .formLogin(AbstractHttpConfigurer::disable) // Desactiva el formulario de login (aquí no se usa login con vista)
                .addFilterBefore(new ClientIdCaptureFilter(), UsernamePasswordAuthenticationFilter.class)
                .with(authorizationServerConfigurer, Customizer.withDefaults());
        return http.build();
    }

    @Bean
    @Order(2)
    public SecurityFilterChain endPointsSecurityFilterChain(HttpSecurity http) throws Exception {
        http
                .securityMatcher("/api/**")
                .oauth2ResourceServer(oauth2 ->
                        oauth2.jwt(jwt ->
                                jwt.jwtAuthenticationConverter(jwtAuthenticationConverter())))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.POST, EndPoints.CLIENT)
                        .hasAuthority(ADMIN_AUTH_SERVER.getScopeWithPrefix())
                        .requestMatchers(HttpMethod.POST, EndPoints.ROLE)
                        .hasAuthority(ADMIN_AUTH_SERVER.getScopeWithPrefix())
                        .requestMatchers(HttpMethod.GET, EndPoints.ROLE)
                        .hasAuthority(ADMIN_AUTH_SERVER.getScopeWithPrefix())
                        .requestMatchers(HttpMethod.POST, EndPoints.USER)
                        .hasAuthority(APP.getScopeWithPrefix())
                        .requestMatchers(HttpMethod.GET, EndPoints.USER)
                        .hasAuthority(APP.getScopeWithPrefix())
                        .anyRequest().authenticated()
                )
                .csrf(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable);
        return http.build();
    }

    @Bean
    @Order(3)
    public SecurityFilterChain viewsSecurityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/login").permitAll()
                        .anyRequest().authenticated())
                .csrf(Customizer.withDefaults())
                .httpBasic(AbstractHttpConfigurer::disable)
                .formLogin(Customizer.withDefaults())
                .addFilterBefore(new ClientIdCaptureFilter(), UsernamePasswordAuthenticationFilter.class)
                .userDetailsService(customUserDetailsService);
        return http.build();
    }

    @Bean
    public AuthorizationServerSettings authorizationServerSettings() {
        return AuthorizationServerSettings.builder().issuer("http://localhost:9000").build(); // usa valores por defecto (endpoints estándar)
    }
}
