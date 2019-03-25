package com.ibrace.police.security;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.ibrace.police.domain.Perfil;

public class UserDetailsImpl implements UserDetails {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private String login;
	private String senha;
	private Collection<? extends GrantedAuthority> perfis;

	public UserDetailsImpl() {}

	public UserDetailsImpl( Integer id, String login, Set<Perfil> perfis ) {
		super();
		this.id       = id;
		this.login    = login;
		this.perfis   = perfis.stream().map(role -> new SimpleGrantedAuthority(role.getNome())).collect(Collectors.toList());
	}

	public boolean hasRole(Perfil role) {
		return getAuthorities().contains(new SimpleGrantedAuthority(role.getNome()));
	}
	
	public Integer getId() {
		return id;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.perfis;
	}

	@Override
	public String getPassword() {
		return this.senha;
	}

	@Override
	public String getUsername() {
		return this.login;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
