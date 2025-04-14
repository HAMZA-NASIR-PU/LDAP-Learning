import javax.naming.*;
import javax.naming.directory.*;

import java.util.Hashtable;

/**
 * Demonstrates how to perform a search by specifying a set of
 * attributes to be matched. Returns selected attributes of objects
 * that contain those matching attributes.
 *
 * usage: java Search
 */
class Search {
    public static void printSearchEnumeration(NamingEnumeration retEnum) {
        try {
            while (retEnum.hasMore()) {
                SearchResult sr = (SearchResult) retEnum.next();
                System.out.println(">>>" + sr.getName());
                GetAllAttrs.printAttrs(sr.getAttributes());
            }
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        // Set up the environment for creating the initial context
        Hashtable<String, Object> env = new Hashtable<>();
        env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
        env.put(Context.PROVIDER_URL, "ldap://localhost:10388/o=JNDITutorial");

        try {
            // Create initial context
            DirContext ctx = new InitialDirContext(env);

            // Specify the ids of the attributes to return
            String[] attrIDs = {"sn", "telephonenumber", "golfhandicap", "mail"};

            // Specify the attributes to match
            // Ask for objects that have the attribute
            // sn == Smith and the "mail" attribute.
            Attributes matchAttrs = new BasicAttributes(true); // ignore case
            matchAttrs.put(new BasicAttribute("cn", "Uri Geller"));
            matchAttrs.put(new BasicAttribute("mail"));

            // Search for objects that have those matching attributes
            NamingEnumeration answer =
                    ctx.search("ou=People", matchAttrs, attrIDs);

            // Print the answer
            printSearchEnumeration(answer);

            // Close the context when we're done
            ctx.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}