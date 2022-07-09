package traineer.vanilson.restfullapis_with_spring_boot_2022.persistence.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "tb_persons")
@NoArgsConstructor
@Data
public class Person implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
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
    @Column(name = "address",
            nullable = false,
            length = 40)
    private String address;
    @Enumerated(EnumType.STRING)
    @Column(name = "gender",
            nullable = false
    )
    private Gender gender;

    public Person(String firstName, String lastName, String email, String address, Gender gender) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.address = address;
        this.gender = gender;
    }

}