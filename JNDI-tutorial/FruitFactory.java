import javax.naming.*;
import javax.naming.spi.ObjectFactory;
import java.util.Hashtable;

/**
 * This is an object factory that when given a reference for a Fruit
 * object, will create an instance of the corresponding Fruit.
 */
public class FruitFactory implements ObjectFactory {

    public FruitFactory() {
    }

    public Object getObjectInstance(Object obj,
                                    Name name,
                                    Context ctx,
                                    Hashtable<?, ?> env)
            throws Exception {

        if (obj instanceof Reference) {
            Reference ref = (Reference)obj;

            if (ref.getClassName().equals(Fruit.class.getName())) {
                RefAddr addr = ref.get("fruit");
                if (addr != null) {
                    return new Fruit((String)addr.getContent());
                }
            }
        }
        return null;
    }
}
