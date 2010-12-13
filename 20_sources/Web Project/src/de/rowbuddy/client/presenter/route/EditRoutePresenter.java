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

import de.rowbuddy.client.events.ListBoatsEvent;
import de.rowbuddy.client.events.ListRoutesEvent;
import de.rowbuddy.client.events.StatusMessageEvent;
import de.rowbuddy.client.model.StatusMessage;
import de.rowbuddy.client.model.StatusMessage.Status;
import de.rowbuddy.client.presenter.Presenter;
import de.rowbuddy.client.services.RouteRemoteServiceAsync;
import de.rowbuddy.entities.Route;
import de.rowbuddy.exceptions.RowBuddyException;

public class EditRoutePresenter implements Presenter {

	public interface Display {
		HasClickHandlers getSubmitButton();

		HasClickHandlers getCancelButton();

		HasValue<String> getName();

		HasValue<String> getLength();

		HasValue<String> getDescription();

		HasValue<Boolean> isMutable();

		Widget asWidget();

		HasClickHandlers getPopUpButton();

		void showPopUp();

		HasClickHandlers getDeleteButton();

		void closeDialog();
	}

	private Display view;
	private RouteRemoteServiceAsync routeService;
	private EventBus eventBus;
	private Route route;
	private Long id;
	private Logger logger = Logger
			.getLogger(EditRoutePresenter.class.getName());

	public EditRoutePresenter(Display view,
			RouteRemoteServiceAsync routeService, EventBus eventBus, Long id) {
		this.view = view;
		this.routeService = routeService;
		this.eventBus = eventBus;
		this.id = id;
	}

	@Override
	public void start(HasWidgets container) {
		container.clear();
		bind();
		fetchRoute();
		container.add(view.asWidget());
	}

	private void fetchRoute() {
		logger.info("Get Route id:" + id);
		routeService.getRoute(id, new AsyncCallback<Route>() {

			@Override
			public void onSuccess(Route arg0) {
				route = arg0;
				logger.info("Route feteched! id:" + route.getId());
				view.getName().setValue(route.getName());
				view.getLength().setValue("" + route.getLengthKM());
				view.getDescription().setValue(route.getShortDescription());
				view.isMutable().setValue(route.isMutable());
			}

			@Override
			public void onFailure(Throwable arg0) {
				logger.severe("Cannot fetch route:" + arg0.getMessage());
				eventBus.fireEvent(new ListRoutesEvent());
				StatusMessage message = new StatusMessage(false);
				message.setStatus(Status.NEGATIVE);
				message.setMessage("Route existiert nicht");
				eventBus.fireEvent(new StatusMessageEvent(message));
			}
		});
	}

	private boolean updateRoute() {
		boolean success = false;
		try {
			route.setName(view.getName().getValue());
			route.setLengthKM(Double.parseDouble(view.getLength().getValue()));
			route.setShortDescription(view.getDescription().getValue());
			route.setMutable(view.isMutable().getValue());
			logger.info("Update route with id:" + route.getId());
			success = true;
		} catch (RowBuddyException ex) {
			logger.warning(ex.getMessage());
			StatusMessage message = new StatusMessage(false);
			message.setMessage(ex.getMessage());
			eventBus.fireEvent(new StatusMessageEvent(message));
		}
		return success;
	}
	
	private void bind(){
		view.getSubmitButton().addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent arg0) {
				if(updateRoute()){
					routeService.canEditRoute(route, new AsyncCallback<Boolean>() {

						@Override
						public void onFailure(Throwable arg0) {
							logger.warning("Cannot update Route:" + arg0.getMessage());
							StatusMessage message = new StatusMessage(false);
							message.setStatus(Status.NEGATIVE);
							message.setMessage("Fehler beim Ändern: "
									+ arg0.getMessage());
							eventBus.fireEvent(new StatusMessageEvent(message));
						}

						@Override
						public void onSuccess(Boolean arg0) {
							logger.info("Submit successful GoTo ListRoutes");
							eventBus.fireEvent(new ListRoutesEvent());
							StatusMessage message = new StatusMessage(false);
							message.setStatus(Status.POSITIVE);
							message.setMessage("Boot erfolgreich geändert");
							eventBus.fireEvent(new StatusMessageEvent(message));
						}
					});
				}
			}
		});
		
		view.getCancelButton().addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent arg0) {
				eventBus.fireEvent(new ListRoutesEvent());
			}
		});
		
		view.getPopUpButton().addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent arg0) {
				view.showPopUp();
			}
		});
		
		view.getDeleteButton().addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent arg0) {
				view.closeDialog();
				routeService.deleteRoute(id, new AsyncCallback<Void>() {

					@Override
					public void onFailure(Throwable arg0) {
						logger.severe("Cannot delete boat: "
								+ arg0.getMessage());
						StatusMessage msg = new StatusMessage(false);
						msg.setMessage(arg0.getMessage());
						msg.setStatus(Status.NEGATIVE);
						eventBus.fireEvent(new StatusMessageEvent(msg));
					}

					@Override
					public void onSuccess(Void arg0) {
						eventBus.fireEvent(new ListBoatsEvent());
						StatusMessage msg = new StatusMessage(false);
						msg.setMessage("Route deleted");
						msg.setStatus(Status.POSITIVE);
						eventBus.fireEvent(new StatusMessageEvent(msg));
					}
				});
			}
		});
	}

}
