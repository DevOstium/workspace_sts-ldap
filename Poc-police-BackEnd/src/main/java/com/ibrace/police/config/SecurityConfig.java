package com.ibrace.police.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.ibrace.police.security.JWTAuthenticationFilter;
import com.ibrace.police.security.JWTAuthorizationFilter;
import com.ibrace.police.security.TokenProvider;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private TokenProvider tokenProvider; 
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	public static String[] PUBLIC_MATCHES = { 
				"/h2-console/**"
			   ,"/usuarios-police/**"
			   ,"/usuarios-ad/**"
			   ,"/acessos/**"
			   ,"/acesso-usuario/**"
			   ,"/acesso-perfis/**"
	};
	
	public static String    URL_POST    =  "/clientes/inserir";
	public static String[]  ROLES       =  {"ADMIN", "COMPRADOR"};
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.headers().frameOptions().disable();
		http.cors().and().csrf().disable();
		
		http.authorizeRequests()
								.antMatchers("/vendas/**").hasAnyRole("ADMIN", "VENDEDOR")
								//.antMatchers("/compras").hasAnyRole("ADMIN", "COMPRADOR")
								//.antMatchers( VerboHTTP.INSERIR.getDescricao(), URL_POST ).hasAnyRole(ROLES)
								.antMatchers(PUBLIC_MATCHES).permitAll()
								.anyRequest().authenticated();
		
		http.addFilter( new JWTAuthenticationFilter ( authenticationManager(),  tokenProvider ) );
		http.addFilter( new JWTAuthorizationFilter  ( authenticationManager(),  tokenProvider, userDetailsService ) );
		
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}
	
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		
		auth.ldapAuthentication().contextSource().url("ldap://localhost:11389/dc=dominioEmpresa,dc=local")
												.managerDn("uid=admin,ou=system")
												.managerPassword("secret").and().userSearchFilter("(uid={0})");
		
		
		/*
		auth.ldapAuthentication().contextSource().url("ldap://i2br02:389/OU=CSC,DC=i2br,DC=local")
		.managerDn("sis_ldap")
		.managerPassword("@cssAdmnstrdrLdp10").and().userSearchFilter("(sAMAccountName={0})");
		*/
		

		
	}
	
	@Bean
	CorsConfigurationSource corsConfigurationSource() {
			CorsConfiguration configuration = new CorsConfiguration().applyPermitDefaultValues();
							  configuration.setAllowedMethods(Arrays.asList("POST", "GET", "PUT", "DELETE", "OPTIONS"));
			
			final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
			                                      source.registerCorsConfiguration("/**", configuration);
		return source;
	}
	
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	
	
	
}

















