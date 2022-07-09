package traineer.vanilson.restfullapis_with_spring_boot_2022.configuration;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import traineer.vanilson.restfullapis_with_spring_boot_2022.persistence.model.Gender;
import traineer.vanilson.restfullapis_with_spring_boot_2022.persistence.model.Person;
import traineer.vanilson.restfullapis_with_spring_boot_2022.persistence.repository.PersonRepository;
import traineer.vanilson.restfullapis_with_spring_boot_2022.services.PersonService;

import java.util.List;

@RequiredArgsConstructor
@Component
public class PersonConfig implements CommandLineRunner {

    private final PersonRepository personRepository;
    private static final Logger logger = LoggerFactory.getLogger(PersonService.class);

    @Override
    public void run(String... args) throws Exception {
        List<Person> people = List.of(
                new Person("vanilson", "wayne", "vanilson@gmail.com", "Rua Luz Soriano Porto", Gender.MALE),
                new Person("tugce", "senturk", "tugce@gmail.com", "Street Amesterdam", Gender.FEMALE),
                new Person("Rick", "Renner", "rick@gmail.com", "mexico ", Gender.OTHER)
        );
//        logger.info("saving person .... ");
//        personRepository.saveAll(people);
//        logger.info("Person save with success {}", people);

    }
}
