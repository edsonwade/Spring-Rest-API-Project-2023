package traineer.vanilson.restfullapis_with_spring_boot_2022.security.jwt;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import traineer.vanilson.restfullapis_with_spring_boot_2022.exceptions.InvalidJwtAuthenticationException;
import traineer.vanilson.restfullapis_with_spring_boot_2022.security.TokenSecurity;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TokenProviderJwt {

    @Value("${security.jwt.token.secret-key:secret}")
    private String secretKey = "secret";
    @Value("${security.jwt.token.expire-length:3600000}")
    private long validityInMilliseconds = 3600000; //1h

    private final UserDetailsService userDetailsService;


    Algorithm algorithm = null;

    @PostConstruct
    protected void init() {

        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());

        algorithm = Algorithm.HMAC256(secretKey.getBytes());
    }

    public TokenSecurity createAccessToken(String username, List<String> roles) {
        Date now = new Date();
        Date validity = new Date(now.getTime() + validityInMilliseconds);
        var accessToken = getAccessToken(username, roles, now, validity);
        var refreshToken = getRefreshToken(username, roles, now);
        return new TokenSecurity(username, true, now, validity, accessToken, refreshToken);
    }

    private String getAccessToken(String username, List<String> roles, Date now, Date validity) {
        String issuerUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
                .build()
                .toUriString();
        return JWT.create()
                .withClaim("roles", roles)
                .withIssuedAt(now)
                .withExpiresAt(validity)
                .withSubject(username)
                .withIssuer(issuerUrl)
                .sign(algorithm)
                .strip();
    }

    private String getRefreshToken(String username, List<String> roles, Date now) {
        Date validityRefreshToken = new Date(now.getTime() + (validityInMilliseconds * 3));
        return JWT.create()
                .withClaim("roles", roles)
                .withIssuedAt(now)
                .withExpiresAt(validityRefreshToken)
                .withSubject(username)
                .sign(algorithm)
                .strip();
    }


    public Authentication getAuthentication(String token) {
        DecodedJWT decodedJWT = decodedToken(token);
        var userDetails = this.userDetailsService.loadUserByUsername(decodedJWT.getSubject());
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    private DecodedJWT decodedToken(String token) {

        var algorithm = Algorithm.HMAC256(secretKey.getBytes());

        JWTVerifier verifier = JWT.require(algorithm).build();

        return verifier.verify(token);
    }

    public String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        return (bearerToken != null && bearerToken.startsWith("Bearer ")) ?
                bearerToken.substring("Bearer ".length()) : null;


    }

    public boolean validateToken(String token) throws InvalidJwtAuthenticationException {
        DecodedJWT decodedJWT = decodedToken(token);
        try {
           return !decodedJWT.getExpiresAt().before(new Date());

        } catch (Exception e) {

            throw new InvalidJwtAuthenticationException("Expired or Invalid JWT Token");

        }
    }

}
