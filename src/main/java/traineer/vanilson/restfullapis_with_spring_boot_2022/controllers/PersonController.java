package traineer.vanilson.restfullapis_with_spring_boot_2022.controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import traineer.vanilson.restfullapis_with_spring_boot_2022.exceptions.PersonNotFoundException;
import traineer.vanilson.restfullapis_with_spring_boot_2022.services.PersonService;
<<<<<<< HEAD
import traineer.vanilson.restfullapis_with_spring_boot_2022.utils.MediaType;
=======
import traineer.vanilson.restfullapis_with_spring_boot_2022.vo.v1.PersonVO;
>>>>>>> 92c3238013c9fc6d6f3c2bc56e4530c892af95df

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/")
@RequiredArgsConstructor
public class PersonController {

    private final PersonService personService;


<<<<<<< HEAD
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
=======
    @GetMapping(value = "/person")
    public ResponseEntity<List<PersonVO>> listAllPersons() throws IllegalAccessException {
        return ResponseEntity.ok(personService.findAllPersons());

    }

    @GetMapping(value = "/person/{id}")
    public ResponseEntity<PersonVO> listAllPersonsById(@PathVariable(name = "id") Integer id) {
>>>>>>> 92c3238013c9fc6d6f3c2bc56e4530c892af95df
        return ResponseEntity.ok(personService.findById(id));

    }

<<<<<<< HEAD
    @PostMapping(value = "/person/createNewPerson",
            produces = {MediaType.APPLICATION_JSON,
                    MediaType.APPLICATION_XML,
                    MediaType.APPLICATION_YML},
            consumes = {MediaType.APPLICATION_JSON,
                    MediaType.APPLICATION_XML,
                    MediaType.APPLICATION_YML}
    )
    public ResponseEntity<Person> createNewPerson(@RequestBody Person person) {
=======
    @PostMapping(value = "/person/createNewPerson")
    public ResponseEntity<PersonVO> createNewPerson(@RequestBody PersonVO person) {
>>>>>>> 92c3238013c9fc6d6f3c2bc56e4530c892af95df
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(personService.createNewPerson(person));
    }

<<<<<<< HEAD
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
=======
    @PutMapping(value = "/person/update/{id}")
    public ResponseEntity<PersonVO> updatePerson(@RequestBody PersonVO person,
                                                 @PathVariable(value = "id") Integer id) {
        if (person.getId().equals(id)) return ResponseEntity.ok(personService
>>>>>>> 92c3238013c9fc6d6f3c2bc56e4530c892af95df
                .updatePerson(person, id));
        throw new PersonNotFoundException("PersonVO With Id " +
                id + " was not founded in list");
    }

<<<<<<< HEAD
    @DeleteMapping(value = "/person/delete/{id}")
    public ResponseEntity<Object> deleteParkingSpotById(@PathVariable(value = "id") int id) {
        Person person = personService.findById(id);
        if (person.getPerson_id().equals(id)) {
=======
//    @DeleteMapping(value = "/person/delete/{id}")
//    public ResponseEntity<Void> deletePerson(
//            @PathVariable(value = "id") Integer id) {
//        ResponseEntity.ok(personService.deletePerson(id));
//
//    }

    @DeleteMapping(value = "/person/delete/{id}")
    public ResponseEntity<Object> deleteParkingSpotById(@PathVariable(value = "id") int id) {
        PersonVO person = personService.findById(id);
        if (person.getId().equals(id)) {
>>>>>>> 92c3238013c9fc6d6f3c2bc56e4530c892af95df
            personService.deletePerson(id);
            return ResponseEntity.status(HttpStatus.OK).body(" Person id deleted with successfully ");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Person  with id " + id + " was  not found");
    }
<<<<<<< HEAD

=======
>>>>>>> 92c3238013c9fc6d6f3c2bc56e4530c892af95df

}
