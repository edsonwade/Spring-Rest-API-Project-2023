package traineer.vanilson.restfullapis_with_spring_boot_2022.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import traineer.vanilson.restfullapis_with_spring_boot_2022.exceptions.ObjectNotFoundException;
import traineer.vanilson.restfullapis_with_spring_boot_2022.persistence.model.Person;
import traineer.vanilson.restfullapis_with_spring_boot_2022.services.PersonService;
import traineer.vanilson.restfullapis_with_spring_boot_2022.utils.MediaType;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/v1/")
@RequiredArgsConstructor
@Tag(name = "People", description = "Endpoints for Managing People")
public class PersonController {

    private final PersonService personService;


    @GetMapping(value = "/person",
            produces = {MediaType.APPLICATION_JSON,
                    MediaType.APPLICATION_XML,
                    MediaType.APPLICATION_YML
            })
    @Operation(summary = "Find All People", description = "Find All People",
            tags = {"People"},
            responses = {
                    @ApiResponse(description = "Success"
                            , responseCode = "200"
                            , content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = Person.class))
                            )}),

                    @ApiResponse(description = "Bad Request"
                            , responseCode = "400"
                            , content = @Content),
                    @ApiResponse(description = "Unauthorized"
                            , responseCode = "400"
                            , content = @Content),
                    @ApiResponse(description = "Not Found"
                            , responseCode = "404"
                            , content = @Content),
                    @ApiResponse(description = "Internal Error"
                            , responseCode = "500"
                            , content = @Content)
            })
    public ResponseEntity<List<Person>> listAllPersons() throws IllegalAccessException {
        return ResponseEntity.ok(Optional.of(personService.findAllPersons())
                .orElseThrow(IllegalAccessException::new));
    }


    @CrossOrigin(origins = {"http://localhost:8080", "http://localhost:8082"})

    @GetMapping(value = "/person/{id}",
            produces = {MediaType.APPLICATION_JSON,
                    MediaType.APPLICATION_XML,
                    MediaType.APPLICATION_YML
            }
    )
    @Operation(summary = "Find People By Id", description = "Find  People By Id",
            tags = {"People"},
            responses = {
                    @ApiResponse(description = "Success"
                            , responseCode = "200"
                            , content = @Content(schema = @Schema(implementation = Person.class), mediaType = "application/json")),
                    @ApiResponse(description = "No Content"
                            , responseCode = "204"
                            , content = @Content),
                    @ApiResponse(description = "Bad Request"
                            , responseCode = "400"
                            , content = @Content),
                    @ApiResponse(description = "Unauthorized"
                            , responseCode = "401"
                            , content = @Content),
                    @ApiResponse(description = "Not Found"
                            , responseCode = "404"
                            , content = @Content),
                    @ApiResponse(description = "Internal Error"
                            , responseCode = "500"
                            , content = @Content)
            })
    public ResponseEntity<Person> listAllPersonsById(@PathVariable(name = "id") Integer id) {
        return ResponseEntity.ok(personService.findById(id));


    }

    @CrossOrigin(origins = {"http://localhost:8080",
            "http://localhost:8082"})
    @PostMapping(value = "/person/createNewPerson",
            produces = {MediaType.APPLICATION_JSON,
                    MediaType.APPLICATION_XML,
                    MediaType.APPLICATION_YML},
            consumes = {MediaType.APPLICATION_JSON,
                    MediaType.APPLICATION_XML,
                    MediaType.APPLICATION_YML}
    )

    @Operation(summary = "Add  a new  Person by passing" +
            "in Json ,XML, OR YAML representation of Person ",
            description = "Add  a new  Person",
            tags = {"People"},
            responses = {
                    @ApiResponse(description = "Success"
                            , responseCode = "201"
                            , content = @Content(schema = @Schema(implementation = Person.class), mediaType = "application/json")),
                    @ApiResponse(description = "Bad Request"
                            , responseCode = "400"
                            , content = @Content),
                    @ApiResponse(description = "Unauthorized"
                            , responseCode = "401"
                            , content = @Content),
                    @ApiResponse(description = "Internal Error"
                            , responseCode = "500"
                            , content = @Content)
            })
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
    @Operation(summary = "Update   a Person by passing" +
            "in Json ,XML, OR YAML representation of Person ",
            description = "Updated",
            tags = {"People"},
            responses = {
                    @ApiResponse(description = "Success"
                            , responseCode = "200"
                            , content = @Content(schema = @Schema(implementation = Person.class), mediaType = "application/json")),
                    @ApiResponse(description = "Bad Request"
                            , responseCode = "400"
                            , content = @Content),
                    @ApiResponse(description = "Unauthorized"
                            , responseCode = "401"
                            , content = @Content),
                    @ApiResponse(description = "Not Found"
                            , responseCode = "404"
                            , content = @Content),
                    @ApiResponse(description = "Internal Error"
                            , responseCode = "500"
                            , content = @Content)
            })
    public ResponseEntity<Person> updatePerson(
            @PathVariable(value = "id") Integer id,
            @RequestBody Person person) {

        if (!person.getPerson_id().equals(id)) {
            throw new ObjectNotFoundException("Person With Id " +
                    id + " was not found in list");
        }
        return ResponseEntity.ok().body(personService.updatePerson(person, id));

    }

    @DeleteMapping(value = "/person/delete/{id}")
    @Operation(summary = "Delete a Person by Id",
            description = "Delete",
            tags = {"People"},
            responses = {
                    @ApiResponse(description = "No Content"
                            , responseCode = "204"
                            , content = @Content),
                    @ApiResponse(description = "Bad Request"
                            , responseCode = "400"
                            , content = @Content),
                    @ApiResponse(description = "Unauthorized"
                            , responseCode = "401"
                            , content = @Content),
                    @ApiResponse(description = "Not Found"
                            , responseCode = "404"
                            , content = @Content),
                    @ApiResponse(description = "Internal Error"
                            , responseCode = "500"
                            , content = @Content)
            })
    public ResponseEntity<Object> deleteParkingSpotById(@PathVariable(value = "id") int id) {
        Person person = personService.findById(id);
        if (person.getPerson_id().equals(id)) {
            personService.deletePerson(id);
            return ResponseEntity.status(HttpStatus.OK).body(" Person id deleted with successfully ");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Person  with id " + id + " was  not found");
    }


}