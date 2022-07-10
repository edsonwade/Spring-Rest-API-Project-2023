package traineer.vanilson.restfullapis_with_spring_boot_2022.services;


import traineer.vanilson.restfullapis_with_spring_boot_2022.vo.v1.PersonVO;

import java.util.List;

public interface PersonService {

    List<PersonVO> findAllPersons();

    PersonVO findById(Integer id);

    PersonVO createNewPerson(PersonVO person);

    PersonVO updatePerson(PersonVO person, Integer id);

    void deletePerson(Integer id);

}
