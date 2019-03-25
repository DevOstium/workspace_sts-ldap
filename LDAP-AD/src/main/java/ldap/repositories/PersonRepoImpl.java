package ldap.repositories;

import static org.springframework.ldap.query.LdapQueryBuilder.query;

import java.util.List;

import javax.naming.Name;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.ContextMapper;
import org.springframework.ldap.core.DirContextAdapter;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.AbstractContextMapper;
import org.springframework.ldap.filter.EqualsFilter;
import org.springframework.ldap.query.LdapQuery;
import org.springframework.ldap.support.LdapNameBuilder;
import org.springframework.ldap.support.LdapUtils;

import ldap.domain.Person;

public class PersonRepoImpl {

	/*
	@Autowired
	private LdapTemplate ldapTemplate;
	
	@Autowired
	private Person person;

	public void setLdapTemplate(LdapTemplate ldapTemplate) {
		this.ldapTemplate = ldapTemplate;
	}

	public void create(Person person) {
		DirContextAdapter context = new DirContextAdapter(buildDn(person));
		mapToContext(person, context);
		ldapTemplate.bind(context);
	}

	public void update(Person person) {
		Name dn = buildDn(person);
		DirContextOperations context = ldapTemplate.lookupContext(dn);
		mapToContext(person, context);
		ldapTemplate.modifyAttributes(context);
	}

	public void delete(Person person) {
		ldapTemplate.unbind(buildDn(person));
	}

	public Person findByPrimaryKey(String name, String company, String country) {
		Name dn = buildDn(name, company, country);
		return (Person) ldapTemplate.lookup(dn, getContextMapper());
	}

	@SuppressWarnings("unchecked")
	public List findByName(String name) {
		LdapQuery query = query().where("objectclass").is("person").and("cn").whitespaceWildcardsLike("name");

		return ldapTemplate.search(query, getContextMapper());
	}

	protected ContextMapper getContextMapper() {
		return new PersonContextMapper();
	}

	protected Name buildDn(Person person) {
		return buildDn(person.getFullName(), person.getCompany(), null);
	}

	protected Name buildDn(String fullname, String company, String country) {
		return LdapNameBuilder.newInstance().add("c", country).add("ou", company).add("cn", fullname).build();
	}

	protected void mapToContext(Person person, DirContextOperations context) {
		context.setAttributeValues("objectclass", new String[] { "top", "person" });
		context.setAttributeValue("cn", person.getFullName());
		context.setAttributeValue("sn", person.getLastName());
		context.setAttributeValue("description", person.getDescription());
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