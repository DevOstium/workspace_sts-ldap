package ldap.service;

import static org.springframework.ldap.query.LdapQueryBuilder.query;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.ContextMapper;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.AbstractContextMapper;
import org.springframework.ldap.query.LdapQuery;
import org.springframework.stereotype.Service;

import ldap.domain.Person;

@Service
public class LdapService  {

	
	@Autowired
	private LdapTemplate ldapTemplate; 
	
	
	
	
	/*
	
	@SuppressWarnings("unchecked")
	public List findByName(String name) {
		LdapQuery query = query().where("objectclass").is("fagner").and("sn").whitespaceWildcardsLike("name");

		return ldapTemplate.search(query, getContextMapper());
	}
	
	
	protected ContextMapper getContextMapper() {
		return new PersonContextMapper();
	}
	
	private static class PersonContextMapper extends AbstractContextMapper<Person> {
		public Person doMapFromContext(DirContextOperations context) {
			Person person = new Person();
			person.setFullName(context.getStringAttribute("cn"));
			person.setLastName(context.getStringAttribute("sn"));
			person.setDescription(context.getStringAttribute("description"));
			return person;
		}
	}
	*/
}
