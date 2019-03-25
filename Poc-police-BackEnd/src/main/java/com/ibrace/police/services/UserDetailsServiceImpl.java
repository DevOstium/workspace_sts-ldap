package com.ibrace.police.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ibrace.police.domain.UsuarioPolice;
import com.ibrace.police.repositories.UsuarioPoliceRepository;
import com.ibrace.police.security.UserDetailsImpl;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UsuarioPoliceRepository repo;

	@Override
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {

		UsuarioPolice user = repo.findByLogin(login);

		if (user == null) {
			throw new UsernameNotFoundException(login);
		}

		return new UserDetailsImpl(user.getId(), user.getLogin(), user.getPefis());
	}

}
