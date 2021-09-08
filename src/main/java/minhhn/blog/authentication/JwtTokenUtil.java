package minhhn.blog.authentication;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtTokenUtil implements Serializable {

  private static final long serialVersionUID = -2550185165626007488L;

  public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60L;

  @Value("${jwt.secret}")
  private String secret;

  private final UserDetailsService jwtUserService;

  public JwtTokenUtil(UserDetailsService jwtUserService) {
    this.jwtUserService = jwtUserService;
  }

  public Boolean validateToken(String token, UserDetails userDetails) {
    final String username = this.getUsernameFromToken(token);
    return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
  }

  public JwtResponse generateToken(JwtRequest authenticationRequest) {
    final UserDetails userDetails = this.jwtUserService.loadUserByUsername(authenticationRequest.getUsername());
    Map<String, Object> claims = new HashMap<>();
    String token = this.doGenerateToken(claims, userDetails.getUsername());
    return new JwtResponse(token);
  }

  public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
    final Claims claims = this.getAllClaimsFromToken(token);
    return claimsResolver.apply(claims);
  }

  private Claims getAllClaimsFromToken(String token) {
    return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
  }

  private Boolean isTokenExpired(String token) {
    final Date expirationDate = this.getExpirationDateFromToken(token);
    return expirationDate.before(new Date());
  }

  public Date getExpirationDateFromToken(String token) {
    return this.getClaimFromToken(token, Claims::getExpiration);
  }

  /*
    1. Define  claims of the token, like Issuer, Expiration, Subject, and the ID
    2. Sign the JWT using the HS512 algorithm and secret key.
    3. According to JWS Compact Serialization(https://tools.ietf.org/html/draft-ietf-jose-json-web-signature-41#section-3.1)
   */
  private String doGenerateToken(Map<String, Object> claims, String subject) {
    return Jwts.builder()
        .setClaims(claims)
        .setSubject(subject)
        .setIssuedAt(new Date(System.currentTimeMillis()))
        .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
        .signWith(SignatureAlgorithm.HS512, secret).compact();
  }

  public String getUsernameFromToken(String token) {
    return this.getClaimFromToken(token, Claims::getSubject);
  }

}
