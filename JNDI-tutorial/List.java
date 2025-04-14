import javax.naming.*;
import java.util.Hashtable;



public class List {
    public static void main(String[] args) {
        try {
            Hashtable<String, Object> env = new Hashtable<>();
            env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
            env.put(Context.PROVIDER_URL, "ldap://localhost:10388/o=JNDITutorial");
            env.put(Context.SECURITY_PRINCIPAL, "uid=admin,ou=system");
            env.put(Context.SECURITY_CREDENTIALS, "secret");

            // Create the initial context
            Context ctx = new InitialContext(env);

            // Get listing of context
            NamingEnumeration<NameClassPair> list = ctx.list("ou=People");

            // Go through each item in list
            while (list.hasMore()) {
                NameClassPair nc = (NameClassPair)list.next();
                System.out.println(nc);
            }

            // Close the context when we're done
            ctx.close();

        }catch (NamingException e) {
            System.out.println(e);
        }
    }
}
