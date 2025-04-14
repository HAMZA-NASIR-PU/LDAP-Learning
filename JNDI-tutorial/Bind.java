import javax.naming.*;
import java.io.File;
import java.util.Hashtable;

/**
 * Demonstrates how to add a binding to a context.
 * (Use Rebind example to overwrite binding; use Unbind to remove binding.)
 *
 * usage: java Bind
 */

class Bind {
    public static void main(String[] args) {

        // Set up the environment for creating the initial context
        Hashtable<String, Object> env = new Hashtable<>();
        env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
        env.put(Context.PROVIDER_URL, "ldap://localhost:10388/o=JNDITutorial");
        env.put(Context.SECURITY_PRINCIPAL, "uid=admin,ou=system");
        env.put(Context.SECURITY_CREDENTIALS, "secret");

        try {
            // Create the initial context
            Context ctx = new InitialContext(env);

            // Create the object to be bound
            Fruit fruit = new Fruit("orange");

            // Perform the bind
            ctx.bind("cn=Favorite Fruit,ou=People", fruit);

            // Check that it is bound
            Object obj = ctx.lookup("cn=Favorite Fruit");
            System.out.println(obj);

            // Close the context when we're done
            ctx.close();
        } catch (NamingException e) {
            System.out.println("Operation failed: " + e);
        }
    }
}