package com.springboot.blog.security;

import com.springboot.blog.exception.BlogAPIException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenProvider {

    @Value("${app.jwt-secret}")
    private String jwtSecret;

    @Value("${app.jwr-expiration-milliseconds}")
    private int jwtExpirationMs;

    //generate token
    /*public String generateToken(Authentication authentication){

        String username = authentication.getName();
        Date currentdate=new Date();
        Date expireDate=new Date(currentdate.getTime() + jwtExpirationMs);
        String token = Jwts.builder()
                .setSubject(username)
                .setIssuedAt(currentdate)
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }*/



    /*//get username from token
    public String getUsernameFromJwt(String token){
        Claims claims = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();
         return claims.getSubject();
    }

    //Validate Token
    public Boolean validToken(String token){
        try{
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;
        }
        catch (SignatureException ex){
            throw new BlogAPIException(HttpStatus.BAD_REQUEST,"Invalid jwt signature");
        }

    }*/

    //generate token
    public String generateToken(Authentication authentication){
        String username = authentication.getName();
        Date currentDate=new Date();
        Date expireDate=new Date(currentDate.getTime() +jwtExpirationMs);
        String token = Jwts.builder()
                .setSubject(username)
                .setIssuedAt(currentDate)
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
        return token;
    }

    //Get username from token
    public String getUsernameFromJwt(String token){
        Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
       return  claims.getSubject();
    }

    //validate token
    public boolean validateToken(String token){
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
            return true;
        }
        catch (SignatureException ex){
            throw new BlogAPIException(HttpStatus.BAD_REQUEST,"Invalid jwt signature");
        }
    }



}
