package traineer.vanilson.restfullapis_with_spring_boot_2022.controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import traineer.vanilson.restfullapis_with_spring_boot_2022.persistence.model.Person;
import traineer.vanilson.restfullapis_with_spring_boot_2022.services.PersonService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/v1/")
@RequiredArgsConstructor
public class PersonController {

    private final PersonService personService;


    @GetMapping(value = "/person")
    public ResponseEntity<List<Person>> listAllPersons() throws IllegalAccessException {
        return ResponseEntity.ok(Optional.of(personService.findAllPersons())
                .orElseThrow(IllegalAccessException::new));
    }

    @GetMapping(value = "/person/{id}")
    public ResponseEntity<Person> listAllPersonsById(@PathVariable(name = "id") Integer id) {
        return ResponseEntity.ok(personService.findById(id));

    }


}
