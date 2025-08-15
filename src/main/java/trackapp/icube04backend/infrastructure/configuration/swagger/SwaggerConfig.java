package trackapp.icube04backend.infrastructure.configuration.swagger;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("ChallengePinApp API")
                        .version("1.0.0")
                        .description("Documentación de API's REST")

                )
                .servers(Collections.singletonList(new Server().url("http://localhost:8080")));
    }
}