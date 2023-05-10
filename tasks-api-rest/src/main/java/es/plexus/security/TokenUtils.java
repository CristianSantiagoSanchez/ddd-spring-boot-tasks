package es.plexus.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class TokenUtils {
    //Encoders.BASE64.encode(Keys.secretKeyFor(SignatureAlgorithm.HS256).getEncoded())
    private final static String ACCESS_TOKEN_SECRET ="eyJhbGciOiJIUzI1NiJ9.ew0KICAic3ViIjogIjEyMzQ1Njc4OTAiLA0KICAibmFtZSI6ICJBbmlzaCBOYXRoIiwNCiAgImlhdCI6IDE1MTYyMzkwMjINCn0.JqEhzhd9DhWCvVnpug26gLX-5QunHzdgsfuIwEQDSn0"
            ;
    private final static int ACCESS_TOKEN_VALIDITY_SECONDS = 1000 * 60 * 60 * 24 * 7;


    public static String createToken(UserDetailsImpl userDetails) {
        Date expirationDate = new Date(System.currentTimeMillis() + ACCESS_TOKEN_VALIDITY_SECONDS);

        Map<String, Object> extra = new HashMap<>();
        extra.put("id", userDetails.getId());
        extra.put("email", userDetails.getEmail());
        extra.put("username", userDetails.getUsername());
        extra.put("name", userDetails.getName());

        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setExpiration(expirationDate)
                .addClaims(extra)
                .signWith(Keys.hmacShaKeyFor(ACCESS_TOKEN_SECRET.getBytes()))
                .compact();
    }

    public static UsernamePasswordAuthenticationToken getAuthenticaton(String token){
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(ACCESS_TOKEN_SECRET.getBytes())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

            String username = claims.getSubject();
            return new UsernamePasswordAuthenticationToken(username, null, Collections.emptyList());
        } catch (JwtException e) {
            return null;
        }

    }
    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        if (token.startsWith("Bearer ")){
            token = token.replace("Bearer ", "");
        }
        return Jwts.parserBuilder()
                .setSigningKey(ACCESS_TOKEN_SECRET.getBytes())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
    /*public String extractUsername(String token){
        return extractClaim(token, c -> c.get("username").toString());
    }
    public void verifyTokenByUserId(String token, long userId){
        if (userId != Long.valueOf(extractClaim(token, c -> c.get("id")).toString())){ //Comprobar si es admin cunado esten los roles
            throw new TokenException("Token is invalid");
        }
    }*/


}
