import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.directory.*;
import java.util.Hashtable;


public class LdapUserCreator {
    public static void main(String[]  args) {
        // Set up the environment for creating the initial context
        Hashtable<String, Object> env = new Hashtable<>();
        env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
        env.put(Context.PROVIDER_URL, "ldap://localhost:10388/o=JNDITutorial");
        env.put(Context.SECURITY_PRINCIPAL, "uid=admin,ou=system");
        env.put(Context.SECURITY_CREDENTIALS, "secret");

        try {
            DirContext context = new InitialDirContext(env);

            // DN of the new user.
            String userDn = "uid=ldapUser,ou=People";

            // Set user attributes
            Attributes attrs = new BasicAttributes(true);
            Attribute objClass = new BasicAttribute("objectClass");
            objClass.add("inetOrgPerson");
            attrs.put(objClass);
            attrs.put("uid", "ldapUser");
            attrs.put("sn", "Ldap User sn");
            attrs.put("cn", "Ldap User cn");
            attrs.put("userPassword", "password");

            // Create user
            context.createSubcontext(userDn, attrs);

            System.out.println("User created successfully.");
            context.close();
        }catch (NamingException e) {
            e.printStackTrace();
        }

    }
}
