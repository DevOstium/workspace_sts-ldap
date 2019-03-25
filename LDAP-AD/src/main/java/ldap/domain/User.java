package ldap.domain;

import javax.naming.Name;

import org.springframework.ldap.odm.annotations.Attribute;
import org.springframework.ldap.odm.annotations.DnAttribute;
import org.springframework.ldap.odm.annotations.Entry;
import org.springframework.ldap.odm.annotations.Id;
import org.springframework.ldap.odm.annotations.Transient;

import ldap.repositories.ServiceUser;

@Entry(objectClasses = { "person", "top" }, base="ou=someOu")
public class User {

	@Id
	private Name dn;
	
	@Attribute(name="cn")
	@DnAttribute(value="cn", index=1)
	private String fullName;

	// No @Attribute annotation means this will be bound to the LDAP attribute
	// with the same value
	private String description;

	@DnAttribute(value="ou", index=0)
	@Transient
	private String company;
	
	
	private String uid;
	private String password;

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
