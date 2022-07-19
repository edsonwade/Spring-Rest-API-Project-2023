package traineer.vanilson.restfullapis_with_spring_boot_2022.integrationtest.testcontainers;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.lifecycle.Startables;

import java.util.Map;
import java.util.stream.Stream;


@ContextConfiguration(initializers = AbstractIntegrationTest.Initializer.class)

public class AbstractIntegrationTest {

    public static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

        static MySQLContainer<?> mysql = new MySQLContainer<>("mysql:8.0");


        private static void startContainers() {
            Startables.deepStart(Stream.of(mysql)).join();
        }

        private static Map<String, String> createConnectionConfiguration() {
            return Map.of(
                    "spring.datasource.url", mysql.getJdbcUrl(),
                    "spring.datasource.username", mysql.getUsername(),
                    "spring.datasource.passworde", mysql.getPassword()
            );

        }

        @SuppressWarnings({"unchecked", "rawtypes"})
        @Override
        public void initialize(ConfigurableApplicationContext applicationContext) {
            startContainers();
            ConfigurableEnvironment environment = applicationContext.getEnvironment();

            MapPropertySource testcontainers = new MapPropertySource("testcontainers", (Map) createConnectionConfiguration());

            environment.getPropertySources().addFirst(testcontainers);
        }


    }
}
