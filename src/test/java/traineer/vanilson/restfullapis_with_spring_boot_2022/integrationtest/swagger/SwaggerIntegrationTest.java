package traineer.vanilson.restfullapis_with_spring_boot_2022.integrationtest.swagger;


import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import traineer.vanilson.restfullapis_with_spring_boot_2022.configs.TestConfig;
import traineer.vanilson.restfullapis_with_spring_boot_2022.integrationtest.testcontainers.AbstractIntegrationTest;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)

public class SwaggerIntegrationTest extends AbstractIntegrationTest {


    @Test
    void shouldDisplaySwaggerUiPages() {

        var content = given()
                .basePath("/swagger-ui/index.html")
                .port(TestConfig.SERVER_PORT)
                .when()
                .get()
                .then()
                .statusCode(200)
                .extract()
                .body()
                .asString();

        assertTrue(content.contains("Swagger UI"));

    }
}
