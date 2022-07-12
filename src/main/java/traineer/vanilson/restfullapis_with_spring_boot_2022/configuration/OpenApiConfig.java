package traineer.vanilson.restfullapis_with_spring_boot_2022.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class OpenApiConfig {

    @Bean
    public OpenAPI customerOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("RESTful API with Java 11 and Spring Boot 2.7.1")
                        .version("v1")
                        .description("Basic Implementation of Restful APIs")
                        .termsOfService(" https://github.com/edsonwade/RestfullAPIs-With-Spring-Boot-2022.git")
                        .license(new License().name("Apache 2.0")
                                .url(" https://github.com/edsonwade/RestfullAPIs-With-Spring-Boot-2022.git"))
                );
    }

}
