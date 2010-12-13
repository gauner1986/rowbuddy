package de.rowbuddy.util;


import org.junit.Before;

import de.rowbuddy.EntityManagerBean;
import de.rowbuddy.EntityManagerBeanLocal;

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
