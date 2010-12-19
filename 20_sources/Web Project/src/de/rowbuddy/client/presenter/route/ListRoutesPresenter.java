package de.rowbuddy.client.presenter.route;

import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

import de.rowbuddy.client.ServiceHolderFactory;
import de.rowbuddy.client.events.AddRouteEvent;
import de.rowbuddy.client.events.DetailsRouteEvent;
import de.rowbuddy.client.presenter.Presenter;
import de.rowbuddy.client.services.RouteRemoteServiceAsync;
import de.rowbuddy.entities.Route;

public class ListRoutesPresenter implements Presenter {

	public interface Display {
		Widget asWidget();

		HasClickHandlers getAddButton();

		HasClickHandlers getTable();

		void setData(Collection<Route> routes);

		int getClickedRow(ClickEvent event);
	}

	private Display view;
	private RouteRemoteServiceAsync routeService;
	private EventBus eventBus;
	private List<Route> fetchedRoutes;
	private static Logger logger = Logger.getLogger(ListRoutesPresenter.class.getName());

	public ListRoutesPresenter(RouteRemoteServiceAsync routeService, Display view,
			EventBus eventBus) {
		this.view = view;
		this.routeService = routeService;
		this.eventBus = eventBus;
	}

	private void bind() {
		view.getAddButton().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent arg0) {
				eventBus.fireEvent(new AddRouteEvent());
			}
		});

		view.getTable().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				int selectedRow = view.getClickedRow(event);
				logger.info("Row " + selectedRow + " selected");
				if (selectedRow > 0) {
					Long id = fetchedRoutes.get(selectedRow - 1).getId();

					logger.info("Fire EditRouteEvent id:" + id);
					eventBus.fireEvent(new DetailsRouteEvent(id));
				}
			}
		});
	}

	@Override
	public void start(HasWidgets container) {
		bind();
		container.clear();
		container.add(view.asWidget());
		fetchRoutes();
	}

	private void fetchRoutes() {
		routeService.getRouteList(new AsyncCallback<List<Route>>() {

			@Override
			public void onFailure(Throwable arg0) {
				ServiceHolderFactory.handleSessionFailure(arg0);
				logger.severe("Cannot get routes:" + arg0.getMessage());
			}

			@Override
			public void onSuccess(List<Route> arg0) {
				fetchedRoutes = arg0;
				view.setData(fetchedRoutes);
			}
		});
	}

}
