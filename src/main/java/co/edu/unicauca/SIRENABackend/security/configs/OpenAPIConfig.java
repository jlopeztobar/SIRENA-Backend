package co.edu.unicauca.SIRENABackend.security.configs;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
    info = @Info(
        contact = @Contact(
            name = "SIRENA Backend",
            email = "sirena@unicauca.edu.co"
        ),
        title = "SIRENA Backend API documentation for developers",
        version = "1.0",
        description = "SIRENA Backend documentation for developers",
        license = @License(
            name = "Name license",
            url = "Some license url"
        )
    ),
    servers = {
        @Server(
            description = "Development server",
            url = "http://localhost:8080/api/v1"
        ),
    }
    // ,security = {
    //     @SecurityRequirement(
    //         name = "bearerAuth",
    //         scopes = {"global"}
    //     )
    // }
)
@SecurityScheme(
    name = "bearerAuth",
    description = "JWT authentication",
    scheme = "bearer",
    type = SecuritySchemeType.HTTP,
    bearerFormat = "JWT",
    in = SecuritySchemeIn.HEADER
)
public class OpenAPIConfig {
    
}
