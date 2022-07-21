package traineer.vanilson.restfullapis_with_spring_boot_2022.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.naming.AuthenticationException;
import java.io.Serializable;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class InvalidJwtAuthenticationException extends AuthenticationException implements Serializable {

    private static final long serialVersionUID = 1L;

    public InvalidJwtAuthenticationException(String explanation) {
        super(explanation);
    }
}
