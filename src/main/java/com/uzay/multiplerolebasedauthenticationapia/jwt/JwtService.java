package com.uzay.multiplerolebasedauthenticationapia.jwt;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class JwtService {
    private final String SECRET_KEY="XiIJLb8Emuzp71YDQNGjfgK394Ut6Ub3F43QRMRkyr48Cz+6ij1u/2u76PRgl2eEYCbC5OaEy89hrViYgVV68g==";


    public String generateToken(UserDetails userDetails) {
        Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
        List<String> list = authorities.stream().map(GrantedAuthority::getAuthority).toList();
        return Jwts
                .builder()
                .subject(userDetails.getUsername())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() +( 1000 * 60 * 60 * 24)))
                .signWith(getSignKey(SECRET_KEY))
                .claim("rol",list)
                .compact();



    }
    public boolean validateToken(String token, UserDetails userDetails) {
        String username = getUsername(token);
       return(username.equals(userDetails.getUsername()) && !isExpired(token));






    }
    public boolean isExpired(String token) {
        //son kullanma tarihi bugünden önceyse tarihi geçmiş demektir
        return (getExpirationDate(token).before(new Date()));


    }
    public String getUsername(String token) {
        return Jwts.parser().verifyWith(getSignKey(SECRET_KEY)).build()
                .parseSignedClaims(token).getPayload().getSubject();


    }
    public Date getExpirationDate(String token) {
        return Jwts.parser().verifyWith(getSignKey(SECRET_KEY)).build()
                .parseSignedClaims(token).getPayload().getExpiration();



    }
    public List<String> getRole(String token) {
        Claims claims = Jwts.parser().verifyWith(getSignKey(SECRET_KEY)).build()
                .parseSignedClaims(token).getPayload();

        // "rol" claim'ini JSON string olarak al
        String rolesJson = claims.get("rol", String.class);

        // JSON string'i List<String> olarak parse et
        Gson gson = new Gson();
        List<String> roles = gson.fromJson(rolesJson, new TypeToken<List<String>>(){}.getType());

        return roles;
    }
    public SecretKey getSignKey(String secretKey){
        byte[] decode = Decoders.BASE64.decode(secretKey);
        SecretKey secretKey1 = Keys.hmacShaKeyFor(decode);
        return secretKey1;

    }



}
