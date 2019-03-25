package ldap.domain;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.query.LdapQueryBuilder;

public class OdmPersonRepo {
	
@Autowired
private LdapTemplate ldapTemplate;

public Person create(Person person) {
   ldapTemplate.create(person);
   return person;
}

public Person findByUid(String uid) {
   return ldapTemplate.findOne(LdapQueryBuilder.query().where("uid").is(uid), Person.class);
}

public void update(Person person) {
   ldapTemplate.update(person);
}

public void delete(Person person) {
   ldapTemplate.delete(person);
}

public List<Person> findAll() {
   return ldapTemplate.findAll(Person.class);
}

public List<Person> findByLastName(String lastName) {
   return ldapTemplate.find( LdapQueryBuilder.query().where("sn").is(lastName), Person.class);
}
}