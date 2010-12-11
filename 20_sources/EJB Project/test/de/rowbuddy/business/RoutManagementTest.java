package de.rowbuddy.business;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.matchers.IsCollectionOf.equalsList;
import static org.junit.Assert.assertThat;

import java.util.LinkedList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.rowbuddy.entities.GpsPoint;
import de.rowbuddy.entities.Member;
import de.rowbuddy.entities.Route;
import de.rowbuddy.exceptions.RowBuddyException;
import de.rowbuddy.util.Ejb;
import de.rowbuddy.util.EjbTestBase;

public class RoutManagementTest extends EjbTestBase {

	private RouteManagement routeMgmt;
	private Route incompleteRoute;
	private Route normalRoute;
	private Route gpsRoute;
	private Route deletedRoute;

	@Before
	public void setup() throws RowBuddyException {
		db.setupMembers();
		db.setupBoats();
		db.setupRoutes();
		db.setupTrips();
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

		// Delted route
		deletedRoute = new Route();
		deletedRoute.setLengthKM(15);
		deletedRoute.setMutable(true);
		deletedRoute.setDeleted(true);
		deletedRoute.setLastEditor(db.getMembers().get(0));
		deletedRoute.setName("Delete route");
		deletedRoute.setShortDescription("Deleted");
		em.persist(deletedRoute);

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
		// when
		Route added = routeMgmt.addRoute(normalRoute, db.getMembers().get(0));

		// then
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
		// when
		Route added = routeMgmt.addRoute(gpsRoute, db.getMembers().get(0));
		// then
		assertThat(added.getWayPoints(), equalsList(gpsRoute.getWayPoints()));
		assertThat(added.getLengthKM(), is(gpsRoute.getLengthKM()));
	}

	@Test(expected = RowBuddyException.class)
	public void cannotAddIncompleteRoute() throws RowBuddyException {
		// when
		routeMgmt.addRoute(incompleteRoute, db.getMembers().get(0));
	}

	@Test(expected = RowBuddyException.class)
	public void cannotAddRouteWithId() throws RowBuddyException {
		// given
		normalRoute.setId(new Long(12));
		// when
		routeMgmt.addRoute(normalRoute, db.getMembers().get(0));
	}

	@Test(expected = RowBuddyException.class)
	public void cannotAddRouteWithoutMember() throws RowBuddyException {
		// when
		routeMgmt.addRoute(normalRoute, null);
	}

	@Test(expected = RowBuddyException.class)
	public void cannotAddDeletedRoute() throws RowBuddyException {
		// given
		normalRoute.setDeleted(true);
		// when
		routeMgmt.addRoute(normalRoute, db.getMembers().get(0));
	}

	@Test(expected = RowBuddyException.class)
	public void cannotAddRouteWithLastEditor() throws RowBuddyException {
		// given
		normalRoute.setLastEditor(db.getMembers().get(0));
		// when
		routeMgmt.addRoute(normalRoute, db.getMembers().get(0));
	}

	@Test(expected = RowBuddyException.class)
	public void cannotAddRouteWithParentId() throws RowBuddyException {
		// given
		normalRoute.setParentId(new Long(12));

		// when
		routeMgmt.addRoute(normalRoute, db.getMembers().get(0));
	}

	@Test
	public void canGetRouteList() throws RowBuddyException {
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

	@Test
	public void canGetRoute() throws RowBuddyException {
		// given
		Route dbRoute = db.getRoutes().get(0);

		// when
		Route route = routeMgmt.getRoute(dbRoute.getId());
		assertThat(route, is(notNullValue()));
		assertThat(route.getId(), is(notNullValue()));
		assertThat(route.getLastEditor(), is(dbRoute.getLastEditor()));
		assertThat(route.getName(), is(dbRoute.getName()));
		assertThat(route.getLengthKM(), is(dbRoute.getLengthKM()));
		assertThat(route.getShortDescription(),
				is(dbRoute.getShortDescription()));
		assertThat(route.getWayPoints(), equalsList(dbRoute.getWayPoints()));
		assertThat(route.isDeleted(), is(dbRoute.isDeleted()));
		assertThat(route.isMutable(), is(dbRoute.isMutable()));
	}

	@Test
	public void canGetAddedRoute() throws RowBuddyException {

		// given
		Route added = routeMgmt.addRoute(normalRoute, db.getMembers().get(0));

		// when
		Route route = routeMgmt.getRoute(added.getId());

		// then
		assertThat(route, is(notNullValue()));
		assertThat(route.getId(), is(notNullValue()));
		assertThat(route.getLastEditor(), is(normalRoute.getLastEditor()));
		assertThat(route.getName(), is(normalRoute.getName()));
		assertThat(route.getLengthKM(), is(normalRoute.getLengthKM()));
		assertThat(route.getShortDescription(),
				is(normalRoute.getShortDescription()));
		assertThat(route.getWayPoints(), equalsList(normalRoute.getWayPoints()));
		assertThat(route.isDeleted(), is(normalRoute.isDeleted()));
		assertThat(route.isMutable(), is(normalRoute.isMutable()));
	}

	@Test
	public void canGetAddedGpsRoute() throws Exception {
		// given
		Route added = routeMgmt.addRoute(gpsRoute, db.getMembers().get(0));

		// when
		Route route = routeMgmt.getRoute(added.getId());

		// then
		assertThat(route, is(notNullValue()));
		assertThat(route.getId(), is(notNullValue()));
		assertThat(route.getLastEditor(), is(gpsRoute.getLastEditor()));
		assertThat(route.getName(), is(gpsRoute.getName()));
		assertThat(route.getLengthKM(), is(gpsRoute.getLengthKM()));
		assertThat(route.getShortDescription(),
				is(gpsRoute.getShortDescription()));
		assertThat(route.getWayPoints(), equalsList(gpsRoute.getWayPoints()));
		assertThat(route.isDeleted(), is(gpsRoute.isDeleted()));
		assertThat(route.isMutable(), is(gpsRoute.isMutable()));
	}

	@Test(expected = RowBuddyException.class)
	public void cannotGetNonExistingRoute() throws RowBuddyException {
		// when
		routeMgmt.getRoute(new Long(9999));
	}

	@Test
	public void canGetDeletedRoute() throws RowBuddyException {
		// when
		Route route = routeMgmt.getRoute(deletedRoute.getId());

		// then
		assertThat(route, is(notNullValue()));
	}

	@Test
	public void canEditRouteWithoutReferences() throws RowBuddyException {
		// given
		Route noRefRoute = db.getRoutes().get(1);
		Member editor = db.getMembers().get(1);

		// when
		noRefRoute.setName("New name");
		Route r = routeMgmt.editRoute(noRefRoute, editor);

		// then
		assertThat(r, notNullValue());
		assertThat(r.getId(), is(noRefRoute.getId()));
		assertThat(r.getName(), is("New name"));
		assertThat(r.getLastEditor(), is(editor));
	}

	@Test
	public void canEditRouteWithReferences() throws RowBuddyException {
		// given
		Route refRoute = routeMgmt.getRoute(db.getRoutes().get(0).getId());
		assertThat(refRoute.isDeleted(), is(false));
		Member editor = db.getMembers().get(1);
		List<GpsPoint> newPoints = new LinkedList<GpsPoint>();
		newPoints.add(new GpsPoint(51.25509323774028, 6.182534694671631));
		newPoints.add(new GpsPoint(51.353364886551454, 6.153985261917114));

		// when
		refRoute.setName("New name");
		refRoute.setWayPoints(newPoints);
		Route newVersion = routeMgmt.editRoute(refRoute, editor);

		// then
		assertThat(newVersion, notNullValue());
		assertThat(newVersion.getId(), is(not(refRoute.getId())));
		assertThat(newVersion.getName(), is("New name"));
		assertThat(newVersion.getLastEditor(), is(editor));
		assertThat(newVersion.getWayPoints().size(), is(newPoints.size()));

		Route oldVersion = routeMgmt.getRoute(db.getRoutes().get(0).getId());
		assertThat(oldVersion.getId(), is(db.getRoutes().get(0).getId()));
		assertThat(oldVersion.getWayPoints().size(), is(db.getRoutes().get(0)
				.getWayPoints().size()));
		assertThat(oldVersion.isDeleted(), is(true));
		assertThat(newVersion.getParentId(), is(oldVersion.getId()));
	}
}
