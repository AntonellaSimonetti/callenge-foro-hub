package ar.antonella.forohub.securities;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ar.antonella.forohub.models.Usuario;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Service
public class TockenService {

    @Value("${api.security.token.secret}")
    private String SECRET;

    public String generateToken(Usuario usuario) {


        try {
            var algoritmo = Algorithm.HMAC256(SECRET);
            return JWT.create()
                    .withIssuer("auth0")
                    .withSubject(usuario.getUsername())
                    .withExpiresAt(fechaExpiracion())
                    .sign(algoritmo);
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Error al generar el token JWT", exception);
        }
    }

    private Instant fechaExpiracion() {
        return ZonedDateTime.now(ZoneId.of("America/Bogota"))
                .plusHours(2)
                .toInstant();
    }

    public String getSubject(String token){
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            return JWT.require(algorithm)
                    .withIssuer("auth0")
                    .build()
                    .verify(token)
                    .getSubject();

        } catch (JWTVerificationException exception){
            throw new RuntimeException("Token no valido");
        }
    }

}
