package traineer.vanilson.restfullapis_with_spring_boot_2022.services;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import traineer.vanilson.restfullapis_with_spring_boot_2022.exceptions.PersonNotFoundException;
import traineer.vanilson.restfullapis_with_spring_boot_2022.mapper.DozerMapper;
import traineer.vanilson.restfullapis_with_spring_boot_2022.persistence.model.Person;
import traineer.vanilson.restfullapis_with_spring_boot_2022.persistence.repository.PersonRepository;
import traineer.vanilson.restfullapis_with_spring_boot_2022.vo.v1.PersonVO;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;
    private static final Logger logger = LoggerFactory.getLogger(PersonService.class);


    @Override
    public List<PersonVO> findAllPersons() {
        return DozerMapper.parseListObject(personRepository.findAll(), PersonVO.class);

    }

    @Override
    public PersonVO findById(Integer id) {
        Person person = personRepository.findById(id)
                .orElseThrow(() -> new PersonNotFoundException("PersonVO With Id " + id + " was not founded in list"));
        return DozerMapper.parseObject(person, PersonVO.class);
    }

    @Override
    public PersonVO createNewPerson(PersonVO person) {
        logger.info(" new PersonVO created with success {}", person);
        Person person1 = DozerMapper.parseObject(person, Person.class);
        return DozerMapper.parseObject(personRepository.save(person1), PersonVO.class);
    }

    @Override
    public PersonVO updatePerson(PersonVO person, Integer id) {
        Person person1 = personRepository.findById(id)
                .orElseThrow(() -> new PersonNotFoundException("PersonVO With Id "
                        + id
                        + " was not founded in list"));
        if (person1.getId().equals(id))
            logger.info(" PersonVO Updated with success {}", person);
        return DozerMapper.parseObject(personRepository.save(person1), PersonVO.class);

    }


    @Override
    public void deletePerson(Integer id) {
        Person person1 = personRepository.findById(id)
                .orElseThrow(() -> new PersonNotFoundException("PersonVO With Id "
                        + id
                        + " was not founded in list"));
        logger.info("Deleting with success{}", person1);
        personRepository.delete(person1);
    }
}
