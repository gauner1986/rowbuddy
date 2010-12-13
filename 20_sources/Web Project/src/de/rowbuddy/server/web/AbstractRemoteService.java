package de.rowbuddy.server.web;

import javax.naming.InitialContext;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.rowbuddy.boundary.RowBuddyFacade;
import de.rowbuddy.entities.Member;

public abstract class AbstractRemoteService extends RemoteServiceServlet {
	private static final long serialVersionUID = 1L;

	protected RowBuddyFacade getRowBuddyFacade() {
		try {
			RowBuddyFacade rbf = ((RowBuddyFacade) this.getThreadLocalRequest().getSession().getAttribute("rbf"));
			if(rbf==null)
				return createTestLogin();
			return rbf;
		} catch (NullPointerException npe) {
			return createTestLogin();
		}
	}
	
	private RowBuddyFacade createTestLogin(){
		try {
			InitialContext ic = new InitialContext();

			RowBuddyFacade rbf = (RowBuddyFacade) ic.lookup("java:global/Ear_Project/EJB_Project/RowBuddyFacade");
			Member m = new Member();
			m.setEmail("admin@bla.de");
			m.setPassword("blubb");
			rbf.login(m);
			return rbf;
		} catch (Exception e) {
			e.printStackTrace();
			throw new NullPointerException();
		}
	}
}
