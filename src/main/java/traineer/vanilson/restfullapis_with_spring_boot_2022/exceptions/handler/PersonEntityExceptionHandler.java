package traineer.vanilson.restfullapis_with_spring_boot_2022.exceptions.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import traineer.vanilson.restfullapis_with_spring_boot_2022.exceptions.PersonExceptionResponse;
import traineer.vanilson.restfullapis_with_spring_boot_2022.exceptions.PersonNotFoundException;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@RestController
@ControllerAdvice
public class PersonEntityExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = Exception.class)
    public final ResponseEntity<PersonExceptionResponse> handleAll(
            Exception e, WebRequest webRequest) {
        PersonExceptionResponse personExceptionResponse = new PersonExceptionResponse(
                e.getMessage(),
                ZonedDateTime.now(ZoneId.of("Z")),
                webRequest.getDescription(false));

        return new ResponseEntity<>(personExceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = PersonNotFoundException.class)
    public final ResponseEntity<PersonExceptionResponse> handleBadRequest(
            Exception e, WebRequest webRequest) {
        PersonExceptionResponse personExceptionResponse = new PersonExceptionResponse(
                e.getMessage(),
                ZonedDateTime.now(ZoneId.of("Z")),
                webRequest.getDescription(false));

        return new ResponseEntity<>(personExceptionResponse, HttpStatus.NOT_FOUND);
    }


}
