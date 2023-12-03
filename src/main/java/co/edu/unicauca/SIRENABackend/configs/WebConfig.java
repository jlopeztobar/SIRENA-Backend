package co.edu.unicauca.SIRENABackend.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Configuración personalizada para la gestión de CORS (Cross-Origin Resource Sharing) en la aplicación web.
 * Permite el acceso a recursos desde el origen http://localhost:4200 y define las configuraciones permitidas.
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    /**
     * Configura las reglas CORS para permitir el acceso desde http://localhost:4200.
     *
     * @param registry El registro CORS utilizado para configurar las reglas CORS.
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:4200")
                .allowedMethods("*")
                .allowedHeaders("*")
                .allowCredentials(true);
    }
}
