package traineer.vanilson.restfullapis_with_spring_boot_2022.integrationtest.testcontainers;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.containers.MariaDBContainer;
import org.testcontainers.lifecycle.Startables;

import java.util.Map;
import java.util.stream.Stream;


@ContextConfiguration(initializers = AbstractIntegrationTest.Initializer.class)

public class AbstractIntegrationTest {

    public static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

        static MariaDBContainer<?> mariadb = new MariaDBContainer<>("mariadb:latest");


        private static void startContainers() {
            Startables.deepStart(Stream.of(mariadb)).join();
        }

        private static Map<String, String> createConnectionConfiguration() {
            return Map.of(
                    "spring.datasource.url", mariadb.getJdbcUrl(),
                    "spring.datasource.username", mariadb.getUsername(),
                    "spring.datasource.password", mariadb.getPassword()
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
