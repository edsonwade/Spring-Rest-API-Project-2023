package traineer.vanilson.restfullapis_with_spring_boot_2022.services;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import traineer.vanilson.restfullapis_with_spring_boot_2022.exception.PersonNotFoundException;
import traineer.vanilson.restfullapis_with_spring_boot_2022.persistence.model.Person;
import traineer.vanilson.restfullapis_with_spring_boot_2022.persistence.repository.PersonRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PersonService {

    private final PersonRepository personRepository;
    private static final Logger logger = LoggerFactory.getLogger(PersonService.class);

    public List<Person> findAllPersons() {
        return Optional.of(personRepository.findAll())
                .orElseThrow(IllegalArgumentException::new);
    }

    public Person findById(Integer id) {
        Person person = personRepository.findById(id).get();
        if (!person.getId().equals(id)) {
            logger.error("The Person with id {} ", id + " doesn't exist");
            throw new PersonNotFoundException("Person not founded...");
        }
        logger.info(" id founded with success {}", id);
        return person;

    }

}
