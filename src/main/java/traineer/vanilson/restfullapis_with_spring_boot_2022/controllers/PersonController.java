package traineer.vanilson.restfullapis_with_spring_boot_2022.controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import traineer.vanilson.restfullapis_with_spring_boot_2022.exceptions.PersonNotFoundException;
import traineer.vanilson.restfullapis_with_spring_boot_2022.services.PersonService;
import traineer.vanilson.restfullapis_with_spring_boot_2022.vo.v1.PersonVO;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/")
@RequiredArgsConstructor
public class PersonController {

    private final PersonService personService;


    @GetMapping(value = "/person")
    public ResponseEntity<List<PersonVO>> listAllPersons() throws IllegalAccessException {
        return ResponseEntity.ok(personService.findAllPersons());

    }

    @GetMapping(value = "/person/{id}")
    public ResponseEntity<PersonVO> listAllPersonsById(@PathVariable(name = "id") Integer id) {
        return ResponseEntity.ok(personService.findById(id));

    }

    @PostMapping(value = "/person/createNewPerson")
    public ResponseEntity<PersonVO> createNewPerson(@RequestBody PersonVO person) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(personService.createNewPerson(person));
    }

    @PutMapping(value = "/person/update/{id}")
    public ResponseEntity<PersonVO> updatePerson(@RequestBody PersonVO person,
                                                 @PathVariable(value = "id") Integer id) {
        if (person.getId().equals(id)) return ResponseEntity.ok(personService
                .updatePerson(person, id));
        throw new PersonNotFoundException("PersonVO With Id " +
                id + " was not founded in list");
    }

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
            personService.deletePerson(id);
            return ResponseEntity.status(HttpStatus.OK).body(" Person id deleted with successfully ");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Person  with id " + id + " was  not found");
    }

}
