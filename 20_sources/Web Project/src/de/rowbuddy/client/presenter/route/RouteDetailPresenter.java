package de.rowbuddy.client.presenter.route;

import java.util.logging.Logger;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

import de.rowbuddy.client.events.EditBoatEvent;
import de.rowbuddy.client.events.ListRoutesEvent;
import de.rowbuddy.client.presenter.Presenter;
import de.rowbuddy.client.presenter.boat.BoatDetailPresenter;
import de.rowbuddy.client.services.RouteRemoteServiceAsync;
import de.rowbuddy.entities.Route;

public class RouteDetailPresenter implements Presenter {

	public interface Display {
		void setName(String name);

		void setLenght(String lenght);

		void setDescription(String description);

		void setMutable(boolean value);

		HasClickHandlers getEditButton();

		HasClickHandlers getCancelButton();

		Widget asWidget();
	}

	private Display view;
	private RouteRemoteServiceAsync routeService;
	private static Logger logger = Logger.getLogger(BoatDetailPresenter.class
			.getName());
	private Long id;
	private EventBus eventBus;

	public RouteDetailPresenter(Display view,
			RouteRemoteServiceAsync routeService, EventBus eventBus, Long id) {
		this.view = view;
		this.routeService = routeService;
		this.id = id;
		this.eventBus = eventBus;
	}

	@Override
	public void start(HasWidgets container) {
		bind();
		container.clear();
		container.add(view.asWidget());
		fetchRoute();
	}

	private void fetchRoute() {
		routeService.getRoute(id, new AsyncCallback<Route>() {

			@Override
			public void onSuccess(Route arg0) {
				view.setName(arg0.getName());
				view.setLenght("" + arg0.getLengthKM());
				view.setDescription(arg0.getShortDescription());
				view.setMutable(arg0.isMutable());
			}

			@Override
			public void onFailure(Throwable arg0) {
				logger.severe(arg0.getMessage());
			}
		});
	}
	
	private void bind(){
		view.getEditButton().addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent arg0) {
				eventBus.fireEvent(new EditBoatEvent(id));
			}
		});
		
		view.getCancelButton().addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent arg0) {
				eventBus.fireEvent(new ListRoutesEvent());
			}
		});
	}

}
