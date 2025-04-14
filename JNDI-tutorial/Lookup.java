import javax.naming.*;
import javax.naming.ldap.LdapContext;
import java.util.Hashtable;


public class Lookup {
    public static void main(String[] args) {
        try {
            Hashtable<String, Object> env = new Hashtable<>();
            env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
            env.put(Context.PROVIDER_URL, "ldap://localhost:10388/o=JNDITutorial");
            env.put(Context.SECURITY_PRINCIPAL, "uid=admin,ou=system");
            env.put(Context.SECURITY_CREDENTIALS, "secret");

            Context ctx = new InitialContext(env);

//            DirContext dirContext = new InitialDirContext(env);

            Object obj = ctx.lookup("cn=Rosanna Lee,ou=People");
            LdapContext ldapContext = (LdapContext) obj;

            System.out.println(obj);
            ctx.close();
        }catch (NamingException e) {
            System.out.println(e);
        }


    }
}