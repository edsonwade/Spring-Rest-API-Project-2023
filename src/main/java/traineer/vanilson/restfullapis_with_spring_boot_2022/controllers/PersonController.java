package traineer.vanilson.restfullapis_with_spring_boot_2022.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import traineer.vanilson.restfullapis_with_spring_boot_2022.exceptions.PersonNotFoundException;
import traineer.vanilson.restfullapis_with_spring_boot_2022.persistence.model.Person;
import traineer.vanilson.restfullapis_with_spring_boot_2022.services.PersonService;
import traineer.vanilson.restfullapis_with_spring_boot_2022.utils.MediaType;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/v1/")
@RequiredArgsConstructor
public class PersonController {

    private final PersonService personService;


    @GetMapping(value = "/person",
            produces = {MediaType.APPLICATION_JSON,
                    MediaType.APPLICATION_XML,
                    MediaType.APPLICATION_YML
            })
    public ResponseEntity<List<Person>> listAllPersons() throws IllegalAccessException {
        return ResponseEntity.ok(Optional.of(personService.findAllPersons())
                .orElseThrow(IllegalAccessException::new));
    }

    @GetMapping(value = "/person/{id}",
            produces = {MediaType.APPLICATION_JSON,
                    MediaType.APPLICATION_XML,
                    MediaType.APPLICATION_YML
            }
    )
    public ResponseEntity<Person> listAllPersonsById(@PathVariable(name = "id") Integer id) {
        return ResponseEntity.ok(personService.findById(id));

    }

    @PostMapping(value = "/person/createNewPerson",
            produces = {MediaType.APPLICATION_JSON,
                    MediaType.APPLICATION_XML,
                    MediaType.APPLICATION_YML},
            consumes = {MediaType.APPLICATION_JSON,
                    MediaType.APPLICATION_XML,
                    MediaType.APPLICATION_YML}
    )
    public ResponseEntity<Person> createNewPerson(@RequestBody Person person) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(personService.createNewPerson(person));
    }

    @PutMapping(value = "/person/update/{id}",
            produces = {MediaType.APPLICATION_JSON,
                    MediaType.APPLICATION_XML,
                    MediaType.APPLICATION_YML},
            consumes = {MediaType.APPLICATION_JSON,
                    MediaType.APPLICATION_XML,
                    MediaType.APPLICATION_YML})
    public ResponseEntity<Person> updatePerson(@RequestBody Person person,
                                               @PathVariable(value = "id") Integer id) {
        if (person.getPerson_id().equals(id)) return ResponseEntity.ok(personService
                .updatePerson(person, id));
        throw new PersonNotFoundException("Person With Id " +
                id + " was not founded in list");
    }

    @DeleteMapping(value = "/person/delete/{id}")
    public ResponseEntity<Object> deleteParkingSpotById(@PathVariable(value = "id") int id) {
        Person person = personService.findById(id);
        if (person.getPerson_id().equals(id)) {
            personService.deletePerson(id);
            return ResponseEntity.status(HttpStatus.OK).body(" Person id deleted with successfully ");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Person  with id " + id + " was  not found");
    }


}