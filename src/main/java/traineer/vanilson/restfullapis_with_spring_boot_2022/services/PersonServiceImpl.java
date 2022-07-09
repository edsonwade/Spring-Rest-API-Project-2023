package traineer.vanilson.restfullapis_with_spring_boot_2022.services;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import traineer.vanilson.restfullapis_with_spring_boot_2022.exceptions.PersonNotFoundException;
import traineer.vanilson.restfullapis_with_spring_boot_2022.persistence.model.Person;
import traineer.vanilson.restfullapis_with_spring_boot_2022.persistence.repository.PersonRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;
    private static final Logger logger = LoggerFactory.getLogger(PersonService.class);

    @Override
    public List<Person> findAllPersons() {
        return Optional.of(personRepository.findAll())
                .orElseThrow(() -> new PersonNotFoundException("Person not founded..."));
    }

    @Override
    public Person findById(Integer id) {
        Optional<Person> person = personRepository.findById(id);
        if (person.isPresent()) {
            logger.info(" id founded with success {}", id);
            return person.get();
        }
        logger.error("The Person with id {} ", id + " doesn't exist");
        throw new PersonNotFoundException("Person With Id " + id + " was not founded in list");
    }
}
