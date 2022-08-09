package traineer.vanilson.restfullapis_with_spring_boot_2022.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import traineer.vanilson.restfullapis_with_spring_boot_2022.services.AuthService;
import traineer.vanilson.restfullapis_with_spring_boot_2022.vo.AccountCredentialsVO;

@Tag(name = "Authentication Endpoint")
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authServices;

    @SuppressWarnings("rawtypes")
    @Operation(summary = "Authenticates a user and returns a token")
    @PostMapping(value = "/signin")
    public ResponseEntity signin(@RequestBody AccountCredentialsVO data) {
        if (checkIfParamsIsNotNull(data))
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid client request!");
        var token = authServices.signin(data);
        return token == null ? ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid client request!") : token;
    }

    @SuppressWarnings("rawtypes")
    @Operation(summary = "Refresh token for authenticated user and returns a token")
    @PutMapping(value = "/refresh/{username}")
    public ResponseEntity refreshToken(@PathVariable("username") String username,
                                       @RequestHeader("Authorization") String refreshToken) {
        if (checkIfParamsIsNotNull(username, refreshToken))
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid client request!");
        var token = authServices.refreshToken(username, refreshToken);
        return token == null ? ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid client request!") : token;
    }

    private boolean checkIfParamsIsNotNull(String username, String refreshToken) {
        return refreshToken == null || refreshToken.isBlank() ||
                username == null || username.isBlank();
    }

    private boolean checkIfParamsIsNotNull(AccountCredentialsVO data) {
        return data == null || data.getUsername() == null || data.getUsername().isBlank()
                || data.getPassword() == null || data.getPassword().isBlank();
    }

}
