package de.rowbuddy.client.boat;

import java.util.logging.Logger;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

import de.rowbuddy.client.Presenter;
import de.rowbuddy.client.ServerRequestHandler;
import de.rowbuddy.client.events.ListBoatsEvent;
import de.rowbuddy.client.events.StatusMessageEvent;
import de.rowbuddy.client.model.StatusMessage;
import de.rowbuddy.client.model.StatusMessage.Status;
import de.rowbuddy.client.services.BoatRemoteServiceAsync;
import de.rowbuddy.entities.Boat;

public class AddBoatPresenter implements Presenter {

	public interface Display {
		Widget asWidget();

		HasClickHandlers getAddButton();

		HasClickHandlers getAdditionalBoat();

		HasClickHandlers getResetButton();

		HasValue<String> getName();

		HasValue<String> getNumberOfSeats();

		HasValue<Boolean> isCoxed();

		void reset();
	}

	private Display view = null;
	private BoatRemoteServiceAsync boatService = null;
	private EventBus eventBus;
	private static Logger logger = Logger.getLogger(AddBoatPresenter.class
			.getName());

	public AddBoatPresenter(Display view, BoatRemoteServiceAsync boatService,
			EventBus eventBus) {
		this.view = view;
		this.boatService = boatService;
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
				addBoat(new ServerRequestHandler<Void>(eventBus,
						"Boot anlegen", new ListBoatsEvent(), null));
			}
		});

		view.getResetButton().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent arg0) {
				History.back();
			}
		});

		view.getAdditionalBoat().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent arg0) {
				addBoat(new ServerRequestHandler<Void>(eventBus,
						"Boot anlegen", null, null) {
					@Override
					public void onSuccess(Void arg0) {
						super.onSuccess(arg0);
						view.reset();
					}
				});
			}
		});
	}

	private void addBoat(ServerRequestHandler<Void> action) {
		Boat boat = new Boat();
		boat.setCoxed(view.isCoxed().getValue());
		try {
			boat.setName(view.getName().getValue());
			boat.setNumberOfSeats(Integer.valueOf(view.getNumberOfSeats()
					.getValue()));
			boatService.addBoat(boat, action);
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
