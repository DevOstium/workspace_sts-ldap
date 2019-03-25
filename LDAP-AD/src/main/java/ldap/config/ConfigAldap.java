package ldap.config;

import java.util.Hashtable;

import javax.naming.directory.DirContext;
import javax.naming.ldap.InitialLdapContext;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ldap.NamingException;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.LdapContextSource;

//@Configuration
public class ConfigAldap {

	
	
	/*
	@Bean
	public LdapContextSource ldapContextSource() {
		LdapTemplate ldapTemplate = new LdapTemplate();
		LdapContextSource context = new LdapContextSource();
		context.setUrl("ldap://localhost:11389/");
		context.setBase("dc=example,dc=com");
		// context.setUserDn("administrator");
		// context.setPassword("secret");

		context.afterPropertiesSet();

		try {
			ldapTemplate.setContextSource(context);
			ldapTemplate.afterPropertiesSet();
		} catch (Exception e) {
			System.out.println("Erro ao Ldap " + e.getMessage());
		}

		return context;
	}
	*/

}
