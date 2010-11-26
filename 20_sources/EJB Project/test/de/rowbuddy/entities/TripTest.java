package de.rowbuddy.entities;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.rowbuddy.exceptions.RowBuddyException;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class TripTest {

	Trip trip1;

	@Before
	public void setup() {
		trip1 = new Trip();
	}

	@After
	public void tearDown() {
		trip1 = null;
	}
	
	@Test
	public void canCreateTrip(){
		assertNull(trip1.getBoat());
		assertNull(trip1.getEndDate());
		assertNull(trip1.getId());
		assertNull(trip1.getStartDate());
		assertNull(trip1.getLastEditor());
		assertNotNull(trip1.getTripMembers());
		assertThat(trip1.getTripMembers().size(), is(0));
	}	
}
