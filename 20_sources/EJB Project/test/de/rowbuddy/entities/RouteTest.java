package de.rowbuddy.entities;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

import java.util.LinkedList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.rowbuddy.exceptions.RowBuddyException;

public class RouteTest {

	private Route route1;

	@Before
	public void setup() {
		route1 = new Route();
	}

	@After
	public void tearDown() {
		route1 = null;
	}

	@Test
	public void canCreateInstance() {
		assertNull(route1.getId());
		assertNull(route1.getParentId());
		assertEquals(route1.getLengthKM(), 0, 0.00001);
		assertThat(route1.isMutable(), is(true));
		assertThat(route1.getName(), is(""));
		assertThat(route1.getShortDescription(), is(""));
		assertNotNull(route1.getWayPoints());
		assertThat(route1.getWayPoints().size(), is(0));
		assertThat(route1.isDeleted(), is(false));
	}

	@Test
	public void canSet2Waypoints() throws RowBuddyException {

		// given
		List<GpsPoint> p = new LinkedList<GpsPoint>();
		p.add(new GpsPoint(51.25509323774028, 6.182534694671631));
		p.add(new GpsPoint(51.353364886551454, 6.153985261917114));

		// when
		route1.setWayPoints(p);

		// then
		assertEquals(11.099108, route1.getLengthKM(), 0.000001);
	}

	@Test
	public void canSet3Waypoints() throws RowBuddyException {

		// given
		List<GpsPoint> p = new LinkedList<GpsPoint>();
		p.add(new GpsPoint(51.25509323774028, 6.182534694671631));
		p.add(new GpsPoint(51.353364886551454, 6.153985261917114));
		p.add(new GpsPoint(51.25509323774028, 6.182534694671631));

		// when
		route1.setWayPoints(p);

		// then
		assertEquals(11.099108 * 2, route1.getLengthKM(), 0.000001);
	}
}
