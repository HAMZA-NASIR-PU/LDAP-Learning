import javax.naming.*;

/**
 * This class is used by the Bind example.
 * It is a referenceable class that can be stored by service
 * providers like the LDAP and file system providers.
 */
public class Fruit implements Referenceable {
    String fruit;

    public Fruit(String f) {
        fruit = f;
    }

    public Reference getReference() throws NamingException {

        return new Reference(
                Fruit.class.getName(),
                new StringRefAddr("fruit", fruit),
                FruitFactory.class.getName(),
                null);          // factory location
    }

    public String toString() {
        return fruit;
    }
}