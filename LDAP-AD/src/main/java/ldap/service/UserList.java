package ldap.service;


import java.util.Properties;
import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;

public class UserList {



public void totalUser() throws NamingException
{
	
	Properties initialProperties = new Properties();
			initialProperties.put(Context.INITIAL_CONTEXT_FACTORY,"com.sun.jndi.ldap.LdapCtxFactory");
			initialProperties.put(Context.PROVIDER_URL, "ldap://i2br02:389");
			initialProperties.put(Context.SECURITY_PRINCIPAL, "sis_ldap");
			initialProperties.put(Context.SECURITY_CREDENTIALS, "@cssAdmnstrdrLdp10");
	DirContext  context = new InitialDirContext(initialProperties);
	
	String searchFilter="(objectClass=person)";
	String[] requiredAttributes={"sn","cn","sis_ldap"  };
	
	SearchControls controls=new SearchControls();
	
	controls.setSearchScope(SearchControls.SUBTREE_SCOPE);
	controls.setReturningAttributes(requiredAttributes);
	
	NamingEnumeration users=context.search("ou=Sistemas", searchFilter, controls);
	
	SearchResult searchResult=null;
			String commonName=null;
			String surName=null;
			String employeeNum=null;
	while(users.hasMore())
	{
		
		searchResult=(SearchResult) users.next();
		Attributes attr=searchResult.getAttributes();
		
		commonName=attr.get("cn").get(0).toString();
		surName=attr.get("sn").get(0).toString();
		employeeNum=attr.get("employeeNumber").get(0).toString();
		System.out.println("Name = "+commonName);
		System.out.println("Surname  = "+surName);
		System.out.println("Employee number = "+employeeNum);
		System.out.println("-------------------------------------------");
		
	}
	
}
	public static void main(String[] args) throws NamingException  
{
	UserList sample = new UserList();
	sample.totalUser();
	
}

}