package traineer.vanilson.restfullapis_with_spring_boot_2022.services;

import traineer.vanilson.restfullapis_with_spring_boot_2022.persistence.model.Person;

import java.util.List;

public interface PersonService {

    List<Person> findAllPersons();

    Person findById(Integer id);

    Person createNewPerson(Person person);

    Person updatePerson(Person person, Integer id);

    void deletePerson(Integer id);

}
