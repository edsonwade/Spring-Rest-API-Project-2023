package traineer.vanilson.restfullapis_with_spring_boot_2022.persistence.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "tb_persons")
@NoArgsConstructor
@JsonPropertyOrder({"id", "firstName", "lastName", "email", "address", "gender"})
public class Person extends RepresentationModel<Person> implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("id")
    private Integer person_id;
    @Column(name = "first_name",
            nullable = false,
            length = 255)
    private String firstName;
    @Column(name = "last_name",
            nullable = false,
            length = 200)
    private String lastName;
    @Column(name = "email",
            nullable = false,
            unique = true,
            length = 40)
    private String email;
    private String address;
    @Enumerated(EnumType.STRING)
    private Gender gender;

    public Person(String firstName, String lastName, String email, String address, Gender gender) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.address = address;
        this.gender = gender;
    }

    public Person(Integer person_id, String firstName, String lastName, String email, String address, Gender gender) {
        this.person_id = person_id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.address = address;
        this.gender = gender;
    }

    public Integer getPerson_id() {
        return person_id;
    }

    public void setPerson_id(Integer person_id) {
        this.person_id = person_id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Person person = (Person) o;
        return Objects.equals(person_id, person.person_id) &&
                Objects.equals(firstName, person.firstName) &&
                Objects.equals(lastName, person.lastName) &&
                Objects.equals(email, person.email) &&
                Objects.equals(address, person.address) &&
                gender == person.gender;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), person_id, firstName, lastName, email, address, gender);
    }

    @Override
    public String toString() {
        return "Person{" +
                "person_id=" + person_id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", gender=" + gender +
                '}';
    }
}