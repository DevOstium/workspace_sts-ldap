package ldap;

import java.util.List;

import ldap.repositories.PersonDAOImpl;
import ldap.repositories.ServiceUser;
import ldap.service.LdapService;

public class LDAPMain {


	public static void main(String[] args) throws Exception {
		ServiceUser service = new ServiceUser();
		
		//service.findByUid("jose", "jose");
		
		//service.authenticate("jose", "jose");
		service.tryLogarAD();
		
		//LdapService ldap = new LdapService();
		//ldap.findByName("jose");
		
		//String text = service.getText("Enviando");
		//System.out.println(text);
		
	
		//PersonDAOImpl dao = new PersonDAOImpl();
	
		//dao.getAllPersons();
		//List ret = dao.findUserByCommonName("jose");
		
	}

	
	

}
