package traineer.vanilson.restfullapis_with_spring_boot_2022.persistence.model;

import java.io.Serializable;

public enum Gender implements Serializable {
    MALE,
    FEMALE,
    OTHER;

    public String getGender() {
        return this.name();
    }

}
