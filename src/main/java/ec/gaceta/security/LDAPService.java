package ec.gaceta.security;

import java.util.Hashtable;
import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;

public class LDAPService {

    private static final String LDAP_URL = "ldap://192.168.1.1";

    // MODO PRUEBA
    private static final boolean MODO_PRUEBA = true;

    public boolean autenticar(String usuario, String password) {

       
        if (MODO_PRUEBA) {
            return "admin".equals(usuario) && "1234".equals(password);
        }


        Hashtable<String, String> env = new Hashtable<>();

        env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
        env.put(Context.PROVIDER_URL, LDAP_URL);
        env.put(Context.SECURITY_AUTHENTICATION, "simple");
        env.put(Context.SECURITY_PRINCIPAL, usuario + "@iepi");
        env.put(Context.SECURITY_CREDENTIALS, password);

        try {
            DirContext ctx = new InitialDirContext(env);
            ctx.close();
            return true;

        } catch (NamingException e) {
            System.out.println("Error autenticando LDAP: " + e.getMessage());
            return false;
        }
    }
}
