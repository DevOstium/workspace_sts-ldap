package ldap.domain;


import java.util.List;

public interface PersonDAO {

    public List<Person> getAllPersons();

    public List findUserByCommonName(String commonName);
}
