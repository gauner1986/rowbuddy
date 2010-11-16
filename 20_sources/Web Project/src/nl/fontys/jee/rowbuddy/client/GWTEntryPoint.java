package nl.fontys.jee.rowbuddy.client;


import nl.fontys.jee.rowbuddy.client.ui.trip.LogDamagedBoat;
import nl.fontys.jee.rowbuddy.client.ui.trip.LogTrip;
import nl.fontys.jee.rowbuddy.client.ui.trip.OpenTrip;
import nl.fontys.jee.rowbuddy.client.ui.trip.StartTrip;
import nl.fontys.jee.rowbuddy.client.ui.trip.StopTrip;
import nl.fontys.jee.rowbuddy.client.ui.trip.ViewTrip;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;

public class GWTEntryPoint implements EntryPoint {

	public void onModuleLoad() {
		RootPanel rootPanel = RootPanel.get();
		
		
		
		rootPanel.add(new OpenTrip());
		rootPanel.add(new LogDamagedBoat());
		rootPanel.add(new LogTrip());
		rootPanel.add(new StartTrip());
		rootPanel.add(new StopTrip());
		rootPanel.add(new ViewTrip());
	}
}
