//package traineer.vanilson.restfullapis_with_spring_boot_2022.mocks;
//
//import traineer.vanilson.restfullapis_with_spring_boot_2022.persistence.model.Gender;
//import traineer.vanilson.restfullapis_with_spring_boot_2022.persistence.model.Person;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class MockPerson {
//
//    List<Person> people = new ArrayList<>();
//
//    public Person mockEntity() {
//        return mockEntity(0);
//    }
//
//    public List<Person> mockEntityList() {
//
//        for (int i = 0; i < 14; i++) {
//            people.add(mockEntity(i));
//        }
//        return people;
//    }
//
//    public Person mockEntity(Integer number) {
//        Person person = new Person();
//        person.setPerson_id(number);
//        person.setFirstName("First Name Test" + number);
//        person.setLastName("Last Name Test" + number);
//        person.setEmail("mock@email.test" + number);
//        person.setAddress("Address Test" + number);
//        person.setGender(((number % 2) == 0) ? Gender.MALE : Gender.FEMALE);
//        return person;
//    }
//}
//