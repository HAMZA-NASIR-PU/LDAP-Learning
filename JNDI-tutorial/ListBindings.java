import javax.naming.*;
import java.util.Hashtable;

public class ListBindings {
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

            // Get listing of context
            NamingEnumeration<Binding> bindings = ctx.listBindings("ou=People");

            // Go through each item in list
            while (bindings.hasMore()) {
                Binding bd = (Binding)bindings.next();
                System.out.println(bd.getName() + ": " + bd.getObject());
            }

            // Close the context when we're done
            ctx.close();
        } catch (NamingException e) {
            System.out.println("List Bindings failed: " + e);
        }
    }
}
