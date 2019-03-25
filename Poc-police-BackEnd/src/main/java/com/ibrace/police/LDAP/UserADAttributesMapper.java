package com.ibrace.police.LDAP;

import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;

import org.springframework.ldap.core.AttributesMapper;

@SuppressWarnings("rawtypes")
public class UserADAttributesMapper implements AttributesMapper {

	@Override
	public Object mapFromAttributes(Attributes attributes) throws NamingException {
		UserAD user = new UserAD();

		Attribute name = attributes.get("name");
		if (name != null) {
			user.setName((String) name.get());
		}

		Attribute displayname = attributes.get("displayname");
		if (displayname != null) {
			user.setDisplayName((String) displayname.get());
		}

		Attribute lastname = attributes.get("sn");
		if (lastname != null) {
			user.setLastName((String) lastname.get());
		}

		Attribute firstname = attributes.get("givenname");
		if (firstname != null) {
			user.setFirstName((String) firstname.get());
		}

		Attribute mail = attributes.get("mail");
		if (mail != null) {
			user.setMail((String) mail.get());
		}

		Attribute userid = attributes.get("sAMAccountName");
		if (userid != null) {
			user.setLogin((String) userid.get()); 
		}

		return user;
	}
}