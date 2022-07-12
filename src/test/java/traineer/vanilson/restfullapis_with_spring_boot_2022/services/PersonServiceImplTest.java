package traineer.vanilson.restfullapis_with_spring_boot_2022.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import traineer.vanilson.restfullapis_with_spring_boot_2022.exceptions.RequiredObjectIsNullException;
import traineer.vanilson.restfullapis_with_spring_boot_2022.mocks.MockPerson;
import traineer.vanilson.restfullapis_with_spring_boot_2022.persistence.model.Gender;
import traineer.vanilson.restfullapis_with_spring_boot_2022.persistence.repository.PersonRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PersonServiceImplTest {

    private final PersonRepository personRepository = mock(PersonRepository.class);
    private final PersonServiceImpl personService = new PersonServiceImpl(personRepository);

    private static final String EXPECTED_MESSAGE = "It is not allowed to persist a null object!";


    MockPerson person;

//    @InjectMocks
//    private PersonServiceImpl personService;

    @BeforeEach
    void setUp() {
        person = new MockPerson();
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findAllPersonsExistentInDatabase() {

        var person1 = person.mockEntityList();
        when(personRepository.findAll()).thenReturn(person1);

        var person = personService.findAllPersons();

        assertNotNull(person);
        assertEquals(14, person.size());

        var personOne = person.get(1);

        assertNotNull(personOne);
        assertNotNull(personOne.getPerson_id());
        assertNotNull(personOne.getLinks());
        assertEquals("Address Test1", personOne.getAddress());
        assertEquals("mock@email.test1", personOne.getEmail());
        assertEquals("First Name Test1", personOne.getFirstName());
        assertEquals("Last Name Test1", personOne.getLastName());
        assertEquals(Gender.FEMALE, personOne.getGender());

        var personFour = person.get(4);

        assertNotNull(personFour);
        assertNotNull(personFour.getPerson_id());
        assertNotNull(personFour.getLinks());
        assertEquals("Address Test4", personFour.getAddress());
        assertEquals("mock@email.test4", personFour.getEmail());
        assertEquals("First Name Test4", personFour.getFirstName());
        assertEquals("Last Name Test4", personFour.getLastName());
        assertEquals(Gender.MALE, personFour.getGender());


    }

    @Test
    void findPersonByIdAndReturnThePerson() {
        var person1 = person.mockEntity(1);
        person1.setPerson_id(1);
        when(personRepository.findById(1)).thenReturn(Optional.of(person1));

        var person = personService.findById(1);

        assertNotNull(person);
        assertNotNull(person.getPerson_id());
        assertNotNull(person.getLinks());
        assertEquals("Address Test1", person.getAddress());
        assertEquals("mock@email.test1", person.getEmail());
        assertEquals("First Name Test1", person.getFirstName());
        assertEquals("Last Name Test1", person.getLastName());
        assertEquals(Gender.FEMALE, person.getGender());


    }

    @Test
    void createNewPerson() {
        var person2 = person.mockEntity(1);

        person2.setPerson_id(1);

        when(personRepository.save(person2)).thenReturn(person2);

        var person = personService.createNewPerson(person2);

        assertNotNull(person);
        assertNotNull(person.getPerson_id());
        assertNotNull(person.getLinks());
        assertEquals("Address Test1", person.getAddress());
        assertEquals("mock@email.test1", person.getEmail());
        assertEquals("First Name Test1", person.getFirstName());
        assertEquals("Last Name Test1", person.getLastName());
        assertEquals(Gender.FEMALE, person.getGender());


    }

    @Test
    void createWithNullPerson() {
        Exception exception = assertThrows(RequiredObjectIsNullException.class,
                () -> {
                    personService.createNewPerson(null);
                });
        final String ACTUAL_MESSAGE = exception.getMessage();

        assertTrue(ACTUAL_MESSAGE.contains(EXPECTED_MESSAGE));


    }


    @Test
    void deletePerson() {
        var person1 = person.mockEntity(1);
        person1.setPerson_id(1);
        when(personRepository.findById(1)).thenReturn(Optional.of(person1));
        personService.deletePerson(1);
    }
}