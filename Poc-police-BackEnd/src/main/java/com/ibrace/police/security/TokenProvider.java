package com.ibrace.police.security;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.ibrace.police.domain.UsuarioPolice;
import com.ibrace.police.services.UsuarioPoliceService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class TokenProvider {

	@Value("${jwt.secret}")
	private String secret;

	@Value("${jwt.expiration}")
	private Long expiration;
	
	@Autowired
	private UsuarioPoliceService usuarioPoliceService;
	
	public String generateToken (String username) {
		Claims claims = getPayload(username);
	
		return Jwts.builder()
					             .setClaims(claims)
					             .setExpiration(new Date(System.currentTimeMillis() + expiration))
								 .signWith(SignatureAlgorithm.HS512, secret.getBytes())
								 .compact();
	}

	private Claims getPayload(String username) {
		
		UsuarioPolice usuarioPolice = usuarioPoliceService.findByLogin(username);
		
		Claims claims = Jwts.claims().setSubject(username);
		
		 //user.getAuthorities()    .stream().map(g -> new String( g.getAuthority() )).collect(Collectors.toSet() );
		 	
		       claims.put("username", username);
			   claims.put("perfis",   usuarioPolice != null ? usuarioPolice.getPefis().stream().map(role -> new String(role.getNome())).collect(Collectors.toSet()) : "" );
		       claims.put("modulos",  usuarioPolice != null ? usuarioPolice.getModulos().stream().map(modulo -> new String(modulo.getNome())).collect(Collectors.toSet()) : "" );
			   claims.put("homeURL",  "/vendas");
			   
		return claims;
	}

	public boolean isValid(String token) {
		Claims claims = parseToken(token);
			
			if(claims != null ) {

				String username = claims.getSubject();
				Date expirationDate = claims.getExpiration();
				Date now = new Date(System.currentTimeMillis());
			
					if(username != null && expirationDate != null && now.before(expirationDate)) {
						return true;
					}
			}
			return false;			
	}

	private Claims parseToken(String token) {
		try {
			return Jwts.parser().setSigningKey(secret.getBytes()).parseClaimsJws(token).getBody();
		} catch (Exception e) {
			return null;
		}
	}

	public String getUsername(String token) {
			Claims claims = parseToken(token);
			if(claims != null ) {
				return claims.getSubject();
			}
		return null;
	}
}























