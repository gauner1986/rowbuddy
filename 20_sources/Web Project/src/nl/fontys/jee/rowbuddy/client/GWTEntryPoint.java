package nl.fontys.jee.rowbuddy.client;


import nl.fontys.jee.rowbuddy.client.ui.trip.OpenTrip;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;

public class GWTEntryPoint implements EntryPoint {

	public void onModuleLoad() {
		RootPanel rootPanel = RootPanel.get();
		rootPanel.add(new OpenTrip());
	}
}
