import javax.naming.*;
import javax.naming.directory.*;

import java.util.Hashtable;

/**
 * Demonstrates how to retrieve all attributes of a named object.
 *
 * usage: java GetAllAttrs
 */
class GetAllAttrs {
    static void printAttrs(Attributes attrs) {
        if (attrs == null) {
            System.out.println("No attributes");
        } else {
            /* Print each attribute */
            try {
                for (NamingEnumeration<? extends Attribute> ae = attrs.getAll();
                     ae.hasMore();) {
                    Attribute attr = (Attribute)ae.next();
                    System.out.println("attribute: " + attr.getID());

                    /* print each value */
                    for (NamingEnumeration e = attr.getAll();
                         e.hasMore();
                         System.out.println("value: " + e.next()));
                }
            } catch (NamingException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {

        // Set up the environment for creating the initial context
        Hashtable<String, Object> env = new Hashtable<>();
        env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
        env.put(Context.PROVIDER_URL, "ldap://localhost:10388/o=JNDITutorial");
        env.put(Context.SECURITY_PRINCIPAL, "uid=admin,ou=system");
        env.put(Context.SECURITY_CREDENTIALS, "secret");

        try {
            // Create the initial context
            DirContext ctx = new InitialDirContext(env);

            // Get all the attributes of named object
            Attributes answer = ctx.getAttributes("cn=Ted Geisel, ou=People");

            // Print the answer
            printAttrs(answer);

            // Close the context when we're done
            ctx.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}