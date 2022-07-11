package traineer.vanilson.restfullapis_with_spring_boot_2022.services;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import traineer.vanilson.restfullapis_with_spring_boot_2022.controllers.PersonController;
import traineer.vanilson.restfullapis_with_spring_boot_2022.exceptions.PersonNotFoundException;
import traineer.vanilson.restfullapis_with_spring_boot_2022.persistence.model.Person;
import traineer.vanilson.restfullapis_with_spring_boot_2022.persistence.repository.PersonRepository;

import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;
    private static final Logger logger = LoggerFactory.getLogger(PersonService.class);


    @Override
    public List<Person> findAllPersons() {
        List<Person> persons = personRepository.findAll();
        persons
                .forEach(p -> p.add(linkTo(methodOn(PersonController.class)
                        .listAllPersonsById(p.getPerson_id()))
                        .withSelfRel()));
        logger.info("List all Persons {} ", persons);
        return persons;
    }


    @Override
    public Person findById(Integer id) {
        Person person = personRepository.findById(id)
                .orElseThrow(() -> new PersonNotFoundException("Person With Id " + id + " was not founded in list"));

        person.add(linkTo(methodOn(PersonController.class).listAllPersonsById(id)).withSelfRel());
        logger.info(" found the person with id {}", id);
        return person;

    }

    @Override
    public Person createNewPerson(Person person) {

        logger.info(" new Person created with success {}", person);
        person.add(linkTo(methodOn(PersonController.class)
                .listAllPersonsById(person.getPerson_id()))
                .withSelfRel());

        return personRepository.save(person);


    }

    @Override
    public Person updatePerson(Person person, Integer id) {
        person = personRepository.findById(id).get();
        if (id.equals(person.getPerson_id())) {
            logger.info(" Person Updated with success {}", person);
            return personRepository.save(person);

        }
        logger.error("The Person with id {} ", id + " doesn't exist");
        throw new PersonNotFoundException("Person With Id " + id + " was not founded in list");

    }

    @Override
    public void deletePerson(Integer id) {
        Optional<Person> person = personRepository.findById(id);
        person.ifPresent(personRepository::delete);
        logger.info("Person with Id {}", id + " deleted with success");
    }
}