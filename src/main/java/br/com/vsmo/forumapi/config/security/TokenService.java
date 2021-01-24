package br.com.vsmo.forumapi.config.security;

import br.com.vsmo.forumapi.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenService {

  @Value("${forum.jwt.expiration}")
  private String expiration;

  @Value("${forum.jwt.secret}")
  private String secret;

  public String generateToken(Authentication authentication) {
    User user = (User) authentication.getPrincipal();
    Date nowDate = new Date();
    Date expirationDate = new Date(nowDate.getTime() + Long.parseLong(this.expiration));

    return Jwts.builder()
            .setIssuer("API do Fórum")
            .setSubject(user.getId().toString())
            .setIssuedAt(nowDate)
            .setExpiration(expirationDate)
            .signWith(SignatureAlgorithm.HS256, this.secret).compact();
  }

  public boolean isValid(String token) {

    try {
      Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  public Long getUserId(String token) {
    Claims claims = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();
    return Long.parseLong(claims.getSubject());
  }
}
