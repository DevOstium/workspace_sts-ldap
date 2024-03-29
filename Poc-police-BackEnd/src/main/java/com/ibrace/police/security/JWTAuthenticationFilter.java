package com.ibrace.police.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.ldap.userdetails.LdapUserDetailsImpl;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibrace.police.domain.Credenciais;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private AuthenticationManager authenticationManager;
	private TokenProvider tokenProvider;

	public JWTAuthenticationFilter(AuthenticationManager authenticationManager, TokenProvider tokenProvider) {
		this.authenticationManager = authenticationManager;
		this.tokenProvider = tokenProvider;
	}

	@Override
    public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res) throws AuthenticationException {
		try {
				Credenciais creds = new ObjectMapper().readValue(req.getInputStream(), Credenciais.class);
				UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(creds.getLogin(), creds.getSenha(), new ArrayList<>() );
				Authentication auth = authenticationManager.authenticate(authToken);
			return auth;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
    protected void successfulAuthentication ( HttpServletRequest    req,
                                              HttpServletResponse   res,
                                              FilterChain           chain,
                                              Authentication        auth
                                            ) throws IOException {
			String username = ( (LdapUserDetailsImpl) auth.getPrincipal()).getUsername();

			String token    = tokenProvider.generateToken( username );
			
			res.addHeader("Authorization", token);
			res.addHeader("access-control-expose-headers", "Authorization");
		}
	
	public class JWTAuthenticationFailureHandler implements AuthenticationFailureHandler {
		   
		
		@Override
	    public void onAuthenticationFailure(  HttpServletRequest       request
	    		                             ,HttpServletResponse      response
	    		                             ,AuthenticationException  exception
	    		                           ) throws IOException, ServletException {
	    		
					response.setStatus(401);
	    			response.setContentType("application/json"); 
	    			response.getWriter().append(json());
	    }
	    private String json() {
	        long date = new Date().getTime();

	        return "{\"timestamp\": " + date + ", "
	               + "\"status\"  : 401, "
	               + "\"error\"   : \"Não autorizado\", "
	               + "\"message\" : \"Login ou senha inválidos\", "
	               + "\"path\"    : \"/login\"}";
	    }
	
	}
	
}	
	
	
	
	
