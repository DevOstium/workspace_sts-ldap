package ldap.repositories;

import java.util.ArrayList;
import java.util.List;

import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.LdapContextSource;
import org.springframework.ldap.filter.AndFilter;
import org.springframework.ldap.filter.EqualsFilter;

import ldap.domain.Person;
import ldap.domain.PersonAttributesMapper;
import ldap.domain.PersonDAO;


public class PersonDAOImpl implements PersonDAO{

    @SuppressWarnings("unused")
	private LdapTemplate getConnectLdap() throws Exception {
		LdapTemplate ldapTemplate = new LdapTemplate();
		LdapContextSource contextSource = new LdapContextSource();

		contextSource.setUrl("ldap://i2br02:389/");
		contextSource.setBase("OU=CSC,DC=i2br,DC=local");
		contextSource.setUserDn("sis_ldap");
		contextSource.setPassword("@cssAdmnstrdrLdp10");	

		contextSource.afterPropertiesSet();
		ldapTemplate.setContextSource( contextSource );
		ldapTemplate.afterPropertiesSet();
		   
		return ldapTemplate;
	}
	

    @SuppressWarnings("unchecked")
	@Override
    public List<Person> getAllPersons() {
    	
    	List<Person> persons = new ArrayList<Person>();

    	try {
        	LdapTemplate ldapTemplate = getConnectLdap();
        	
            @SuppressWarnings({ "unchecked", "rawtypes" })
            List search = ldapTemplate.search("", "(objectClass=person)", new PersonAttributesMapper());
            
            	persons.addAll(search);
            	
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return persons;
    }

    @Override
    public List findUserByCommonName(String commonName) {
          
    	LdapTemplate ldapTemplate;
    	AndFilter andFilter = new AndFilter();
		try {
			ldapTemplate = 	getConnectLdap();
							andFilter.and(new EqualsFilter("objectclass","person"));
							andFilter.and(new EqualsFilter("sAMAccountName", commonName));
			return ldapTemplate.search("", andFilter.encode(), new PersonAttributesMapper());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
    }
}