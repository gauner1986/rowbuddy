package de.rowbuddy.server.web;

import java.util.Collection;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.rowbuddy.business.BoatOverview;
import de.rowbuddy.client.services.BoatRemoteService;

public class BoatRemoteServiceImpl extends RemoteServiceServlet implements BoatRemoteService{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public Collection<BoatOverview> getBoatOverview() {
		// TODO Auto-generated method stub
		System.out.println("Call");
		return null;
	}
	
	

}
