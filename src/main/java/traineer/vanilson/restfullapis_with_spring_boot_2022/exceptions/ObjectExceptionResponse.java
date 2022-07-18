package traineer.vanilson.restfullapis_with_spring_boot_2022.exceptions;

import lombok.Data;

import java.io.Serializable;
import java.time.ZonedDateTime;


@Data
public class ObjectExceptionResponse implements Serializable {
    private static final long serialVersionUID = 1L;
    private final String message;
    private final ZonedDateTime timestamp;
    private String details;

    public ObjectExceptionResponse(String message, ZonedDateTime timestamp, String details) {
        this.message = message;
        this.timestamp = timestamp;
        this.details = details;
    }
}
