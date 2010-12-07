package de.rowbuddy.server.web;

import javax.servlet.annotation.WebServlet;

import de.rowbuddy.client.services.SessionManager;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.rowbuddy.boundary.dtos.MemberDTO;
import de.rowbuddy.business.RowBuddyFacade;

/**
 * Servlet implementation class SessionManaager
 */
@WebServlet("/SessionManager")
public class SessionManagerImpl extends RemoteServiceServlet implements SessionManager{
	private static final long serialVersionUID = 1L;
       
    /**
     * @see RemoteServiceServlet#RemoteServiceServlet()
     */
    public SessionManagerImpl() {
        super();
        // TODO Auto-generated constructor stub
    }
       
    /**
     * @see RemoteServiceServlet#RemoteServiceServlet(Object)
     */
    public SessionManagerImpl(Object delegate) {
        super(delegate);
        // TODO Auto-generated constructor stub
    }
    
    public MemberDTO getMember(){
    	return ((RowBuddyFacade) this.getThreadLocalRequest().getSession().getAttribute("rbf")).getMember();
    }

}
