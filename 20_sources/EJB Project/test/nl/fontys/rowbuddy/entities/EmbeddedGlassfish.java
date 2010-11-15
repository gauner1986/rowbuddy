package nl.fontys.rowbuddy.entities;

import javax.ejb.embeddable.EJBContainer;
import javax.naming.Context;

/**
 * 
 * @author Georg Fleischer
 */
public class EmbeddedGlassfish {

	private static EmbeddedGlassfish singleton = null;
	private EJBContainer container;
	private Context context;

	private EmbeddedGlassfish() {
		this.container = EJBContainer.createEJBContainer(); // properties);
		this.context = container.getContext();
	}

	public static EmbeddedGlassfish getInstance() {
		if (singleton == null) {
			singleton = new EmbeddedGlassfish();
		}
		return singleton;
	}

	public EJBContainer getContainer() {
		return container;
	}

	public Context getContext() {
		return context;
	}
}
