package traineer.vanilson.restfullapis_with_spring_boot_2022.exceptions;

public class ObjectRequestException extends RuntimeException {
    public ObjectRequestException(String message) {
        super(message);
    }
}
