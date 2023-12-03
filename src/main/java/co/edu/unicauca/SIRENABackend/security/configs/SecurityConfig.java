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

/**
 * Configuración de seguridad para la aplicación SIRENA Backend.
 * Define reglas de autorización para diferentes endpoints, manejo de sesiones,
 * y filtros para la autenticación JWT.
 */
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final AuthenticationProvider authProvider;
    private final LogoutHandler logoutHandler;

    /**
     * Configuración de las reglas de autorización, manejo de sesiones y filtros.
     *
     * @param http Configuración de seguridad HTTP.
     * @return SecurityFilterChain para la aplicación.
     * @throws Exception Excepción en caso de error en la configuración.
     */
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
                        .requestMatchers("/api/v1/building").permitAll()
                        .requestMatchers("/api/v1/classroom").permitAll()
                        .requestMatchers("/api/v1/classroomType").permitAll()
                        .requestMatchers("/api/v1/faculty").permitAll()
                        .requestMatchers("/api/v1/incidence").permitAll()
                        .requestMatchers("/api/v1/incidenceType").permitAll()
                        .requestMatchers("/api/v1/program").permitAll()
                        .requestMatchers("/api/v1/statistics").permitAll()
                        .requestMatchers("/api/v1/data").permitAll()

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
