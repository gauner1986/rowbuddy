package nl.fontys.rowbuddy.entities;

import javax.naming.NamingException;

import nl.fontys.rowbuddy.EntityManagerBean;
import nl.fontys.rowbuddy.EntityManagerBeanLocal;

/**
 *
 * @author Georg Fleischer
 */
public class EjbTestBase {

	protected final EmbeddedGlassfish glassfish;
    protected final EntityManagerBeanLocal em;

    public EjbTestBase() {
        glassfish = EmbeddedGlassfish.getInstance();
        em = (EntityManagerBeanLocal) lookUp(EntityManagerBean.class, EntityManagerBeanLocal.class);
    }

    protected Object lookUp(Class classType , Class interfaceType){

        StringBuilder lookUpName = new StringBuilder();
        lookUpName.append("java:global/classes/");
        lookUpName.append(classType.getSimpleName());
        lookUpName.append("!");
        lookUpName.append(interfaceType.getName());

        try {
            return glassfish.getContext().lookup(lookUpName.toString());
        } catch (NamingException ex) {
            throw new RuntimeException(ex);
        }
    }
}
