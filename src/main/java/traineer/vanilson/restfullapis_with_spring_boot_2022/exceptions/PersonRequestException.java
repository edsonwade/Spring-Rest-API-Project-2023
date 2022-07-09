package traineer.vanilson.restfullapis_with_spring_boot_2022.exceptions;

public class PersonRequestException extends RuntimeException {
    public PersonRequestException(String message) {
        super(message);
    }
}
