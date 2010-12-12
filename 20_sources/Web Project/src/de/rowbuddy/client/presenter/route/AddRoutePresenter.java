package de.rowbuddy.client.presenter.route;

import java.util.logging.Logger;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

import de.rowbuddy.client.events.StatusMessageEvent;
import de.rowbuddy.client.model.StatusMessage;
import de.rowbuddy.client.model.StatusMessage.Status;
import de.rowbuddy.client.presenter.Presenter;
import de.rowbuddy.client.services.RouteRemoteServiceAsync;
import de.rowbuddy.entities.Route;

public class AddRoutePresenter implements Presenter {

	public interface Display {
		Widget asWidget();

		HasClickHandlers getAddButton();

		HasValue<String> getName();

		HasValue<String> getAuthor();

		HasValue<String> getLengthKM();

		void reset();
	}

	private Display view = null;
	private RouteRemoteServiceAsync routeService = null;
	private EventBus eventBus;
	private static Logger logger = Logger.getLogger(AddRoutePresenter.class.getName());

	public AddRoutePresenter(Display view,
			RouteRemoteServiceAsync routeService, EventBus eventBus) {
		this.view = view;
		this.routeService = routeService;
		this.eventBus = eventBus;
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
						logger.severe(arg0.getMessage());
					}

					@Override
					public void onSuccess(Route arg0) {
						logger.info("Route successful added; Reset View");
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
	}

	private void addRoute(AsyncCallback<Route> action) {
		Route route = new Route();
		try {
			route.setName(view.getName().getValue());
			//TODO: searchMember and set
			route.setLastEditor(null);
			route.setLengthKM(Double.valueOf(view.getLengthKM().getValue()));
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
