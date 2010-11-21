package de.rowbuddy.util;
//package de.rowbuddy.entities;
//
//
//import java.util.HashMap;
//import java.util.Map;
//
//import javax.persistence.EntityManager;
//import javax.persistence.EntityManagerFactory;
//import javax.persistence.Persistence;
//
//import static org.eclipse.persistence.config.PersistenceUnitProperties.*;
//
//public class Persistor {
//
//	private static Persistor singleton;
//
//	public static Persistor getInstance() {
//		if (singleton == null) {
//			singleton = new Persistor();
//		}
//		return singleton;
//	}
//	
//	private final EntityManager entityManager;
//
//	private Persistor() {
//		
//		Map<String, String> properties = new HashMap<String, String>();
//		PersistenceUnitProperties.
//		properties.put(TRANSACTION_TYPE, "RESOURCE_LOCAL");
//		properties.put("provider","org.eclipse.persistence.jpa.PersistenceProvider");
//		properties.put("eclipselink.jdbc.driver", "com.mysql.jdbc.Driver");
//		properties.put("eclipselink.jdbc.url",
//				"jdbc:derby://localhost:1527/sun-appserv-samples");
//		properties.put("eclipselink.jdbc.user", "APP");
//		properties.put("eclipselink.jdbc.password", "");
//		properties.put("eclipselink.ddl-generation", "drop-and-create-tables");
//
//		EntityManagerFactory emf = Persistence.createEntityManagerFactory("new-unit", properties);
//		entityManager = emf.createEntityManager();
//	}
//	
//	public EntityManager getEntityManager() {
//		return entityManager;
//	}
//}
