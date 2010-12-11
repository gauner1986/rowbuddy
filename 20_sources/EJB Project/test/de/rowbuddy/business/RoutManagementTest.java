package de.rowbuddy.business;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.matchers.IsCollectionOf.equalsList;
import static org.junit.Assert.assertThat;

import java.util.LinkedList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.rowbuddy.entities.GpsPoint;
import de.rowbuddy.entities.Route;
import de.rowbuddy.exceptions.RowBuddyException;
import de.rowbuddy.util.Ejb;
import de.rowbuddy.util.EjbTestBase;

public class RoutManagementTest extends EjbTestBase {

	private RouteManagement routeMgmt;
	private Route incompleteRoute;
	private Route normalRoute;
	private Route gpsRoute;

	@Before
	public void setup() throws RowBuddyException {
		db.setupMembers();
		routeMgmt = Ejb.lookUp(RouteManagement.class, RouteManagement.class);

		// Normal route
		normalRoute = new Route();
		normalRoute.setLengthKM(15);
		normalRoute.setMutable(true);
		normalRoute.setName("Normal Testroute 1");
		normalRoute
				.setShortDescription("This is a nice route along the river Rhein");

		// Gps route
		gpsRoute = new Route();
		gpsRoute.setMutable(true);
		gpsRoute.setName("GPS Testroute 1");
		gpsRoute.setShortDescription("This is a nice route along the river Rhein");
		List<GpsPoint> p = new LinkedList<GpsPoint>();
		p.add(new GpsPoint(51.25509323774028, 6.182534694671631));
		p.add(new GpsPoint(51.353364886551454, 6.153985261917114));
		gpsRoute.setWayPoints(p);

		// incomplete route
		incompleteRoute = new Route();
	}

	@After
	public void tearDown() {
		routeMgmt = null;
		incompleteRoute = null;
		normalRoute = null;
		gpsRoute = null;
	}

	@Test
	public void canAddRoute() throws RowBuddyException {
		Route added = routeMgmt.addRoute(normalRoute, db.getMembers().get(0));

		assertThat(added, is(notNullValue()));
		assertThat(added.getId(), is(notNullValue()));
		assertThat(added.getLastEditor(), is(db.getMembers().get(0)));
		assertThat(added.getName(), is(normalRoute.getName()));
		assertThat(added.getLengthKM(), is(normalRoute.getLengthKM()));
		assertThat(added.getShortDescription(),
				is(normalRoute.getShortDescription()));
		assertThat(added.getWayPoints(), equalsList(normalRoute.getWayPoints()));
		assertThat(added.isDeleted(), is(false));
		assertThat(added.isMutable(), is(normalRoute.isMutable()));
	}

	@Test
	public void canAddGpsRoute() throws RowBuddyException {
		Route added = routeMgmt.addRoute(gpsRoute, db.getMembers().get(0));
		assertThat(added.getWayPoints(), equalsList(gpsRoute.getWayPoints()));
		assertThat(added.getLengthKM(), is(gpsRoute.getLengthKM()));
	}

	@Test(expected = RowBuddyException.class)
	public void cannotAddIncompleteRoute() throws RowBuddyException {
		routeMgmt.addRoute(incompleteRoute, db.getMembers().get(0));
	}

	@Test(expected = RowBuddyException.class)
	public void cannotAddRouteWithId() throws RowBuddyException {
		normalRoute.setId(new Long(12));
		routeMgmt.addRoute(normalRoute, db.getMembers().get(0));
	}

	@Test(expected = RowBuddyException.class)
	public void cannotAddRouteWithoutMember() throws RowBuddyException {
		routeMgmt.addRoute(normalRoute, null);
	}

	@Test(expected = RowBuddyException.class)
	public void cannotAddDeletedRoute() throws RowBuddyException {
		normalRoute.setDeleted(true);
		routeMgmt.addRoute(normalRoute, db.getMembers().get(0));
	}

	@Test(expected = RowBuddyException.class)
	public void cannotAddRouteWithLastEditor() throws RowBuddyException {
		normalRoute.setLastEditor(db.getMembers().get(0));
		routeMgmt.addRoute(normalRoute, db.getMembers().get(0));
	}

	@Test(expected = RowBuddyException.class)
	public void cannotAddRouteWithParentId() throws RowBuddyException {
		normalRoute.setParentId(new Long(12));
		routeMgmt.addRoute(normalRoute, db.getMembers().get(0));
	}

	@Test
	public void canGetRouteList() throws RowBuddyException {
		// given
		db.setupRoutes();

		// when
		List<Route> routes = routeMgmt.getRouteList();

		// then
		List<Route> wantedRoutes = new LinkedList<Route>();
		for (Route r : db.getRoutes()) {
			if (!r.isDeleted()) {
				wantedRoutes.add(r);
			}
		}
		assertThat(routes, equalsList(wantedRoutes));
	}
}
