package com.fenix.LDAP;

import java.util.ArrayList;
import java.util.List;

import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.LdapContextSource;
import org.springframework.ldap.filter.AndFilter;
import org.springframework.ldap.filter.EqualsFilter;
import org.springframework.stereotype.Service;

@Service
public class UserADService implements UserADRepository {

	private LdapTemplate getConnectLdap() throws Exception {
		LdapTemplate ldapTemplate = new LdapTemplate();
		LdapContextSource contextSource = new LdapContextSource();

		contextSource.setUrl("ldap://i2br02:389/");
		contextSource.setBase("OU=CSC,DC=i2br,DC=local");
		contextSource.setUserDn("sis_ldap");
		contextSource.setPassword("@cssAdmnstrdrLdp10");

		contextSource.afterPropertiesSet();
		ldapTemplate.setContextSource(contextSource);
		ldapTemplate.afterPropertiesSet();

		return ldapTemplate;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserAD> findAll() {
		List<UserAD> users = new ArrayList<UserAD>();

    	try {
        	LdapTemplate ldapTemplate = getConnectLdap();
        	
            @SuppressWarnings("rawtypes")
			List search = ldapTemplate.search("", "(objectClass=person)", new UserADAttributesMapper());
            
            	users.addAll(search);
            	
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return users;
	}

	@SuppressWarnings("unchecked")
	public List<UserAD> findUserByLogin(String login) {
		
		LdapTemplate ldapTemplate;
    
		AndFilter andFilter = new AndFilter();
		
    	try {
			ldapTemplate = 	getConnectLdap();
							andFilter.and(new EqualsFilter("objectclass","person"));
							andFilter.and(new EqualsFilter("sAMAccountName", login));
							
			 List<UserAD> ret = ldapTemplate.search("", andFilter.encode(), new UserADAttributesMapper());
			
			 return ret;
			 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
