package com.ibrace.police.LDAP;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface UserADRepository {

	public List<UserAD> findAll();

	public List<UserAD> findUserByLogin(String login);
}
