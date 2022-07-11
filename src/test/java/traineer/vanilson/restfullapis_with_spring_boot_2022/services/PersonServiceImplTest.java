package traineer.vanilson.restfullapis_with_spring_boot_2022.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import traineer.vanilson.restfullapis_with_spring_boot_2022.mocks.MockPerson;
import traineer.vanilson.restfullapis_with_spring_boot_2022.persistence.model.Gender;
import traineer.vanilson.restfullapis_with_spring_boot_2022.persistence.model.Person;
import traineer.vanilson.restfullapis_with_spring_boot_2022.persistence.repository.PersonRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PersonServiceImplTest {

    private final PersonRepository personRepository = mock(PersonRepository.class);
    private final PersonServiceImpl personService = new PersonServiceImpl(personRepository);

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
    }

    @Test
    void findPersonByIdAndReturnThePerson() {
        Person person1 = person.mockEntity(1);
        person1.setPerson_id(1);
        when(personRepository.findById(1)).thenReturn(Optional.of(person1));

        Person person = personService.findById(1);

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
    }
}