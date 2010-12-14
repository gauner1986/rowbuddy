package de.rowbuddy.client.presenter.route;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.maps.client.event.MapClickHandler;
import com.google.gwt.maps.client.event.MapRightClickHandler;
import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.maps.client.overlay.Marker;
import com.google.gwt.maps.client.overlay.Polyline;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

import de.rowbuddy.client.events.ListRoutesEvent;
import de.rowbuddy.client.events.StatusMessageEvent;
import de.rowbuddy.client.model.StatusMessage;
import de.rowbuddy.client.model.StatusMessage.Status;
import de.rowbuddy.client.presenter.Presenter;
import de.rowbuddy.client.services.RouteRemoteServiceAsync;
import de.rowbuddy.entities.GpsPoint;
import de.rowbuddy.entities.Route;

public class AddRoutePresenter implements Presenter {

	public interface Display {
		Widget asWidget();

		HasClickHandlers getAddButton();

		HasClickHandlers getResetButton();

		HasValue<String> getName();

		HasValue<String> getLengthKM();
		
		HasValue<String> getShortDescription();

		MapWidget getMap();

		void reset();
	}

	private Display view = null;
	private RouteRemoteServiceAsync routeService = null;
	private EventBus eventBus;
	private static Logger logger = Logger.getLogger(AddRoutePresenter.class
			.getName());
	private List<LatLng> points;
	private Polyline polyline;

	public AddRoutePresenter(Display view,
			RouteRemoteServiceAsync routeService, EventBus eventBus) {
		this.view = view;
		this.routeService = routeService;
		this.eventBus = eventBus;
		points = new LinkedList<LatLng>();
	}

	@Override
	public void start(HasWidgets container) {
		bind();
		container.clear();
		container.add(view.asWidget());
	}

	private void bind() {
		view.getAddButton().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent arg0) {
				addRoute(new AsyncCallback<Route>() {

					@Override
					public void onFailure(Throwable arg0) {
						logger.severe("Cannot add route: " + arg0.getMessage());
					}

					@Override
					public void onSuccess(Route arg0) {
						logger.info("Route successful added; Reset View");
						eventBus.fireEvent(new ListRoutesEvent());
						StatusMessage message = new StatusMessage();
						message.setMessage("Route erfolgreich hinzugefügt");
						message.setStatus(Status.POSITIVE);
						message.setAttached(false);
						eventBus.fireEvent(new StatusMessageEvent(message));
						view.reset();
					}
				});
			}
		});

		view.getMap().addMapClickHandler(new MapClickHandler() {

			@Override
			public void onClick(MapClickEvent event) {
				view.getMap().addOverlay(new Marker(event.getLatLng()));
				points.add(event.getLatLng());
				LatLng[] latLngs = new LatLng[points.size()];
				if (polyline != null) {
					view.getMap().removeOverlay(polyline);
				}
				polyline = new Polyline(points.toArray(latLngs));
				view.getMap().addOverlay(polyline);
				view.getLengthKM().setValue("" + calcDistance());
				logger.info("Marker add: " + points.size());
			}
		});

		view.getMap().addMapRightClickHandler(new MapRightClickHandler() {

			@Override
			public void onRightClick(MapRightClickEvent event) {
				if (event.getOverlay() instanceof Marker) {
					Marker marker = (Marker) event.getOverlay();
					view.getMap().removeOverlay(marker);
					points.remove(marker.getLatLng());
					view.getMap().removeOverlay(polyline);
					LatLng[] latLngs = new LatLng[points.size()];
					polyline = new Polyline(points.toArray(latLngs));
					view.getMap().addOverlay(polyline);
					view.getLengthKM().setValue("" + calcDistance());
					logger.info("Marker remove: " + points.size());
				}
			}
		});

		view.getResetButton().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent arg0) {
				History.back();
			}
		});
	}

	private double calcDistance() {
		double lengthInMeter = 0.0;
		if (points.size() > 1) {
			Iterator<LatLng> iterator = points.iterator();
			LatLng point = iterator.next();
			while (iterator.hasNext()) {
				LatLng pointNext = iterator.next();
				lengthInMeter += point.distanceFrom(pointNext);
				point = pointNext;
			}
		}
		return Math.round((lengthInMeter / 1000.0) * 100.) / 100.;
	}

	private void addRoute(AsyncCallback<Route> action) {
		Route route = new Route();
		try {
			route.setName(view.getName().getValue());
			route.setLastEditor(null);
			route.setLengthKM(Double.valueOf(view.getLengthKM().getValue()));
			route.setShortDescription(view.getShortDescription().getValue());
			List<GpsPoint> wayPoints = new LinkedList<GpsPoint>();
			for (LatLng latlng : points) {
				wayPoints.add(new GpsPoint(latlng.getLatitude(), latlng
						.getLongitude()));
			}
			route.setWayPoints(wayPoints);
			route.validate();
			routeService.addRoute(route, action);
		} catch (Exception e) {
			StatusMessage message = new StatusMessage();
			message.setMessage(e.getMessage());
			message.setStatus(Status.NEGATIVE);
			message.setAttached(false);
			eventBus.fireEvent(new StatusMessageEvent(message));
			logger.warning(e.getMessage());

		}
	}

}
