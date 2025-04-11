package com.example.ldap.work.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.ldap.core.support.*;
import org.springframework.ldap.core.*;

@Configuration
public class LdapConfiguration {



//    @Bean
//    public LdapContextSource contextSource() {
//        LdapContextSource contextSource = new LdapContextSource();
//
//        contextSource.setUrl("ldap://localhost:10388");
////        contextSource.setBase("dc=example,dc=com");
////        contextSource.setBase("ou=users,ou=system");
//        contextSource.setUserDn("uid=admin,ou=system");
//        contextSource.setPassword("secret");
//        return contextSource;
//    }
//
//    @Bean
//    public LdapClient ldapClient() {
//        return LdapClient.create(contextSource());
//    }
//
//    @Bean
//    public LdapTemplate ldaptemplate() {
//        return new LdapTemplate(contextSource());
//    }

}
