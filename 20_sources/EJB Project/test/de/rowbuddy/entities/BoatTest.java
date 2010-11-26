package de.rowbuddy.entities;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.rowbuddy.exceptions.RowBuddyException;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class BoatTest {

	Boat boat1;

	@Before
	public void setup() {
		boat1 = new Boat();
	}

	@After
	public void tearDown() {
		boat1 = null;
	}

	@Test
	public void canCreateBoat() {
		assertNull(boat1.getId());
		assertThat(boat1.getName(), is(""));
		assertThat(boat1.getNumberOfSeats(), is(0));
		assertNotNull(boat1.getBoatDamages());
		assertNotNull(boat1.getBoatReservations());
	}

	@Test(expected = NullPointerException.class)
	public void cannotSetNullName() throws RowBuddyException {
		boat1.setName(null);
	}

	@Test(expected = RowBuddyException.class)
	public void cannotSetBlankName() throws RowBuddyException {
		boat1.setName("");
	}

	@Test(expected = RowBuddyException.class)
	public void cannotSetZeroSeats() throws RowBuddyException {
		boat1.setNumberOfSeats(0);
	}
	
	@Test(expected=RowBuddyException.class)
	public void cannotSetNegativeSeats() throws RowBuddyException{
		boat1.setNumberOfSeats(-10);
	}
}
