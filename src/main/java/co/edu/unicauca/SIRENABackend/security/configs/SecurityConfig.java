package co.edu.unicauca.SIRENABackend.security.configs;

import static co.edu.unicauca.SIRENABackend.security.common.enums.RoleEnum.ADMIN;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import co.edu.unicauca.SIRENABackend.security.jwt.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final AuthenticationProvider authProvider;
    private final LogoutHandler logoutHandler;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(authRequest -> authRequest
                        .requestMatchers(
                                "/auth/login",
                                "/auth/refresh-token",
                                "/v2/api-docs",
                                "/v3/api-docs",
                                "/v3/api-docs/**",
                                "/swagger-resources",
                                "/swagger-resources/**",
                                "/configuration/ui",
                                "/configuration/security",
                                "/swagger-ui/**",
                                "/webjars/**",
                                "/swagger-ui.html")
                        .permitAll()

                        // Auth endpoints
                        .requestMatchers("/auth/register").hasRole(ADMIN.name())
                        .requestMatchers("/role/**").hasRole(ADMIN.name())
                        .requestMatchers("/user/**").hasRole(ADMIN.name())

                        // Sevicios de la aplicación
                        .requestMatchers("/api/v1/bookings").permitAll()
                        .requestMatchers("/api/v1/classroom").permitAll()
                        .requestMatchers("/api/v1/classroomType").permitAll()
                        .requestMatchers("/api/v1/incidence").permitAll()
                        .requestMatchers("/api/v1/incidenceType").permitAll()

                        .anyRequest()
                        .authenticated())
                .sessionManagement(sessionManager -> sessionManager
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .logout(
                        logout -> logout.logoutUrl("/auth/logout")
                                .addLogoutHandler(logoutHandler)
                                .logoutSuccessHandler(
                                        (request, response,
                                                authetication) -> SecurityContextHolder
                                                        .clearContext()))
                .build();
    }
}
