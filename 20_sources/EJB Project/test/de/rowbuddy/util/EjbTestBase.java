package de.rowbuddy.util;

import nl.fontys.rowbuddy.EntityManagerBean;
import nl.fontys.rowbuddy.EntityManagerBeanLocal;

import org.junit.Before;

/**
 *
 * @author Georg Fleischer
 */
public class EjbTestBase {

	protected final EmbeddedGlassfish glassfish;
    protected final EntityManagerBeanLocal em;
    protected final Database db;

    public EjbTestBase() {
        glassfish = EmbeddedGlassfish.getInstance();
        em = (EntityManagerBeanLocal) Ejb.lookUp(EntityManagerBean.class, EntityManagerBeanLocal.class);
        db = Database.getInstance();
    }

    @Before
    public void setupTestBase(){
    	db.clearDatabase();
    }
}
