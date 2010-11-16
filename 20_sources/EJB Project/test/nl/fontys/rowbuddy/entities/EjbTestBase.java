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
        em = (EntityManagerBeanLocal) Ejb.lookUp(EntityManagerBean.class, EntityManagerBeanLocal.class);
    }
}
