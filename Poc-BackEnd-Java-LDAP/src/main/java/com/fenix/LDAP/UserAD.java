package com.fenix.LDAP;

public class UserAD {

	private String name;
	private String displayName;
	private String lastName;
	private String firstName;
	private String mail;
	private String login;

	public UserAD() {
	}

	public UserAD(String name, String displayName, String lastName, String firstName, String mail, String login) {
		super();
		this.name = name;
		this.displayName = displayName;
		this.lastName = lastName;
		this.firstName = firstName;
		this.mail = mail;
		this.login = login;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

}
