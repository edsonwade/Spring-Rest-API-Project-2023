package traineer.vanilson.restfullapis_with_spring_boot_2022.vo.v1;

import lombok.Data;
import lombok.NoArgsConstructor;
import traineer.vanilson.restfullapis_with_spring_boot_2022.persistence.model.Gender;

import java.io.Serializable;

@NoArgsConstructor
@Data
public class PersonVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private String address;
    private Gender gender;

    public PersonVO(String firstName, String lastName, String email, String address, Gender gender) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.address = address;
        this.gender = gender;
    }
}