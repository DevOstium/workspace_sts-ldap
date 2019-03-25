package ldap.repositories;

import java.util.List;

import javax.naming.NamingException;
import javax.naming.directory.DirContext;

import org.springframework.ldap.core.AuthenticatedLdapEntryContextMapper;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.ldap.core.LdapEntryIdentification;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.AbstractContextMapper;
import org.springframework.ldap.core.support.LdapContextSource;
import org.springframework.ldap.query.LdapQueryBuilder;
import org.springframework.stereotype.Service;


@Service
public class ServiceUser {

	public boolean tryLogarAD() {

		LdapTemplate ldapTemplate = new LdapTemplate();
		LdapContextSource contextSource = new LdapContextSource();

		try {
			contextSource.setUrl("ldap://i2br02:389/");
			contextSource.setBase("OU=CSC,DC=i2br,DC=local");
			contextSource.setUserDn("sis_ldap");
			contextSource.setPassword("@cssAdmnstrdrLdp10");

			contextSource.afterPropertiesSet();
			ldapTemplate.setContextSource(contextSource);
			ldapTemplate.afterPropertiesSet();
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		AuthenticatedLdapEntryContextMapper<DirContextOperations> mapper = new AuthenticatedLdapEntryContextMapper<DirContextOperations>() {
			public DirContextOperations mapWithContext(DirContext ctx,
					LdapEntryIdentification ldapEntryIdentification) {
				try {
					return (DirContextOperations) ctx.lookup(ldapEntryIdentification.getRelativeName());
				} catch (NamingException e) {
					throw new RuntimeException("Failed to lookup " + ldapEntryIdentification.getRelativeName(), e);
				}
			}
		};

			try {
				Object loginAD = ldapTemplate
						.authenticate(LdapQueryBuilder.query().where("sAMAccountName").is("joses"), "Del@2010", mapper)
						.getAttributes()
						.get("sAMAccountName")
						.get();
			
				System.out.println("O Login do AD : " + loginAD);

			} catch (NamingException e) {
				System.out.println("Erro ao tentar logar no AD : ");
				//new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + ModuloSistema.class.getName()) );
			}




		/*  Pegar todos os atributos
		 * NamingEnumeration<? extends Attribute> i = at.getAll(); try { while
		 * (i.hasMore()) { Attribute ret = i.next(); System.out.println(ret.getID() +
		 * " : " + ret.get()); } } catch (NamingException e) { e.printStackTrace(); }
		 * System.out.println("/**********************");
		 */

		return false;

	}

	public boolean authenticate(String userDn, String credentials) throws Exception {

		LdapTemplate ldapTemplate = new LdapTemplate();
		LdapContextSource contextSource = new LdapContextSource();

		contextSource.setUrl("ldap://i2br02:389/");
		contextSource.setBase("OU=CSC,DC=i2br,DC=local");
		contextSource.setUserDn("sis_ldap");
		contextSource.setPassword("@cssAdmnstrdrLdp10");

		contextSource.afterPropertiesSet();
		ldapTemplate.setContextSource(contextSource);
		ldapTemplate.afterPropertiesSet();

		@SuppressWarnings({ "unchecked", "rawtypes" })
		List<String> result = ldapTemplate.search(LdapQueryBuilder.query().where("sAMAccountName").is("jose"),
				new AbstractContextMapper() {
					protected String doMapFromContext(DirContextOperations ctx) {
						return ctx.getNameInNamespace();
					}
				});

		for (String s : result) {

			String[] split = s.split(",");

			for (int i = 0; i < split.length; i++) {
				System.out.println(split[i]);
			}
		}

		System.out.println("-- ");

		return false;

	}

	public void findByUid(String uid, String senha) throws Exception {

		LdapTemplate ldapTemplate = getConnectLdap();

		AuthenticatedLdapEntryContextMapper<DirContextOperations> mapper = new AuthenticatedLdapEntryContextMapper<DirContextOperations>() {

			public DirContextOperations mapWithContext(DirContext ctx,
					LdapEntryIdentification ldapEntryIdentification) {
				try {
					return (DirContextOperations) ctx.lookup(ldapEntryIdentification.getRelativeName());
				} catch (NamingException e) {

					throw new RuntimeException("Failed to lookup " + ldapEntryIdentification.getRelativeName(), e);

				}
			}
		};

		DirContextOperations auth = ldapTemplate
				.authenticate(LdapQueryBuilder.query().where("sAMAccountName").is("jose"), "Del@2010", mapper);
		System.out.println("************************");

		System.out.println(auth);

	}

	private LdapTemplate getConnectLdap() throws Exception {
		LdapTemplate ldapTemplate = new LdapTemplate();
		LdapContextSource contextSource = new LdapContextSource();

		contextSource.setUrl("ldap://i2br02:389/");
		contextSource.setBase("CN=José Fagner Lira Silva,OU=Desenvolvimento,OU=TI,OU=CSC,DC=i2br,DC=local");
		contextSource.setUserDn("sis_ldap");
		contextSource.setPassword("@cssAdmnstrdrLdp10");

		/*
		 * contextSource.setUrl("ldap://localhost:11389/");
		 * contextSource.setBase("dc=example,dc=com");
		 */

		contextSource.afterPropertiesSet();
		ldapTemplate.setContextSource(contextSource);
		ldapTemplate.afterPropertiesSet();

		return ldapTemplate;
	}

}
