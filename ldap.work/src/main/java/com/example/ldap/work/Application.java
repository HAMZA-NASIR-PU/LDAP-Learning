package com.example.ldap.work;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.ldap.core.*;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.directory.*;
import java.util.Hashtable;
import java.util.List;

@SpringBootApplication
public class Application {

//	@Autowired
//	private LdapTemplate ldapTemplate;
//
//	@Autowired
//	private LdapClient ldapClient;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner() {
		return runner -> {

			//			List<Object> list = ldapTemplate.search("", "(objectClass=person)", (Attributes attrs) -> {
//				return attrs.get("cn").get();
//			});

//			List<String> list = ldapTemplate.search(
//					query()
//							.where("objectClass").is("person"),
//					new AttributesMapper<String>() {
//						public String mapFromAttributes(Attributes attrs) throws NamingException {
//							Attribute dn = attrs.get("cn");
//							System.out.println(dn);
//							return attrs.get("cn").toString();
//						}
//					}
//			);

//			List list = ldapTemplate.search("", "(objectClass=*)", new AttributesMapper() {
//
//				public Object mapFromAttributes(Attributes attributes) throws NamingException {
//					return attributes.get("objectclass").get();
//				}
//			});

//			List list = ldapTemplate
//					.search(
//							"",
//							"cn=*",
//							(AttributesMapper<String>) attrs -> (String) attrs.get("cn").get());




//			LdapClient.SearchSpec searchSpec =  ldapClient.search()
//					.query(query().where("objectClass").is("person"));
//			List list = ldapClient.search()
//					.query(query().where("objectClass").is("person"))
//					.toList((Attributes attrs) -> {
//						return (String) attrs.get("cn").get();
//					});




//			DirContextAdapter context = new DirContextAdapter();
//
//			SearchControls constraints = new SearchControls();
//			constraints.setSearchScope(SearchControls.SUBTREE_SCOPE);
//
//			context.setAttributeValues("objectclass", new String[] {"top", "person", "inetOrgPerson", "organizationalPerson"});
//			context.setAttributeValue("cn", "springboot@email.com");
//			context.setAttributeValue("sn", "Spring Boot");
//			context.setAttributeValue("description", "This is done by spring-ldap-core");
//
//			Name bindDn = LdapNameBuilder.newInstance()
//					.add("ou", "users")
//					.add("cn", "springboot@email.com").build();
//
//			ldapClient.bind(bindDn).object(context).execute();




//				Name dn = LdapNameBuilder.newInstance()
//						.add("ou", "system")
//						.add("ou", "users")
//						.add("cn", "springboot@email.com")
//						.build();
//
//
//				DirContextAdapter context = new DirContextAdapter(dn);
//
//				context.setAttributeValues(
//						"objectclass",
//						new String[]
//								{ "top",
//										"person",
//										"organizationalPerson",
//										"inetOrgPerson" });
//				context.setAttributeValue("cn", "springboot@email.com");
//				context.setAttributeValue("sn", "Spring Boot");
//				context.setAttributeValue
//						("userPassword", "springboot@email.com\"springboot@email.com\"");
//
//				ldapTemplate.bind(context);

			this.search();


			// Below is the working example
			/*
			List list = ldapTemplate.search(
					"ou=users,ou=system",
					"objectClass=person",
					(AttributesMapper) attrs -> {
						String employeeType = attrs.get("employeeType") != null ? attrs.get("employeeType").get().toString() : null;
						String cn = attrs.get("cn") != null ? attrs.get("cn").get().toString() : null;
						String sn = attrs.get("sn") != null ? attrs.get("sn").get().toString() : null;
						String password = attrs.get("userPassword") != null ? new String((byte[]) attrs.get("userPassword").get()) : null;
						return cn;
					}
			);
			for (Object obj : list) {
				System.out.println(obj);
			} */
		};
	}

	private boolean search() {
		try {

			Hashtable<String, String> env = new Hashtable<>();

			env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
			env.put(Context.PROVIDER_URL, "ldap://localhost:10388");
			env.put(Context.SECURITY_PRINCIPAL, "uid=admin,ou=system");
			env.put(Context.SECURITY_CREDENTIALS, "secret");

			DirContext ctx = new InitialDirContext(env);
			String base = "ou=users,ou=system";

			SearchControls sc = new SearchControls();
			sc.setSearchScope(SearchControls.SUBTREE_SCOPE);

			String filter = "(objectclass=person)";

			NamingEnumeration<SearchResult> results = ctx.search(base, filter, sc);


			while (results.hasMore()) {
				SearchResult sr = (SearchResult) results.next();
				Attributes attrs = sr.getAttributes();

				System.out.println( "Attributes: " + attrs);

				Attribute employeeType = attrs.get("employeeType");
				Attribute userPassword = attrs.get("userPassword");
				Attribute sn = attrs.get("sn");
				Attribute cn = attrs.get("cn");
			}
			ctx.close();

			return true;
		} catch (Exception e) {
			System.out.println(e);
			return false;
		}
	}
}
