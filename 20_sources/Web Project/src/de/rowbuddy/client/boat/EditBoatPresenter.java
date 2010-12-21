package de.rowbuddy.client.boat;

import java.util.logging.Logger;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

import de.rowbuddy.client.Presenter;
import de.rowbuddy.client.ServiceHolderFactory;
import de.rowbuddy.client.events.ListBoatsEvent;
import de.rowbuddy.client.events.StatusMessageEvent;
import de.rowbuddy.client.model.StatusMessage;
import de.rowbuddy.client.model.StatusMessage.Status;
import de.rowbuddy.client.services.BoatRemoteServiceAsync;
import de.rowbuddy.entities.Boat;
import de.rowbuddy.exceptions.RowBuddyException;

public class EditBoatPresenter implements Presenter {
	public interface Display {
		HasClickHandlers getSubmitButton();

		HasClickHandlers getCancelButton();

		HasValue<String> getName();

		HasValue<String> getNumberOfSeats();

		HasValue<Boolean> isCoxed();

		HasValue<Boolean> isLocked();

		Widget asWidget();

		HasClickHandlers getPopUpButton();

		void showPopUp();

		HasClickHandlers getDeleteButton();

		void closeDialog();
	}

	private Display view;
	private BoatRemoteServiceAsync boatService;
	private EventBus eventBus;
	private Boat boat;
	private Long id;
	private Logger logger = Logger.getLogger(EditBoatPresenter.class.getName());

	public EditBoatPresenter(Display view, BoatRemoteServiceAsync boatService,
			EventBus eventBus, Long id) {
		this.view = view;
		this.boatService = boatService;
		this.eventBus = eventBus;
		this.id = id;
	}

	@Override
	public void start(HasWidgets container) {
		// TODO Auto-generated method stub
		container.clear();
		bind();
		fetchBoat();
		container.add(view.asWidget());

	}

	private void fetchBoat() {
		logger.info("Get Boat id:" + id);
		boatService.getBoat(id, new AsyncCallback<Boat>() {

			@Override
			public void onSuccess(Boat arg0) {
				boat = arg0;
				logger.info("Boat feteched! id:" + boat.getId());
				view.getName().setValue(boat.getName());
				view.getNumberOfSeats().setValue(
						String.valueOf(boat.getNumberOfSeats()));
				view.isCoxed().setValue(boat.isCoxed());
				view.isLocked().setValue(boat.isLocked());
			}

			@Override
			public void onFailure(Throwable arg0) {
				ServiceHolderFactory.handleSessionFailure(arg0);
				logger.severe("Cannot fetch boat:" + arg0.getMessage());
				eventBus.fireEvent(new ListBoatsEvent());
				StatusMessage message = new StatusMessage(false);
				message.setStatus(Status.NEGATIVE);
				message.setMessage("Boot existiert nicht");
				eventBus.fireEvent(new StatusMessageEvent(message));
			}
		});
	}

	private boolean updateBoat() {
		boolean success = false;
		try {
			boat.setName(view.getName().getValue());
			boat.setCoxed(view.isCoxed().getValue());
			int seats = Integer.valueOf(view.getNumberOfSeats().getValue());

			boat.setNumberOfSeats(seats);
			boat.setLocked(view.isLocked().getValue());
			logger.info("Update boat with id:" + boat.getId());
			success = true;
		} catch (NumberFormatException ex) {
			logger.warning("Seat is not a number");
			StatusMessage message = new StatusMessage(false);
			message.setMessage(ex.getMessage());
			eventBus.fireEvent(new StatusMessageEvent(message));
		} catch (RowBuddyException ex) {
			logger.warning(ex.getMessage());
			StatusMessage message = new StatusMessage(false);
			message.setMessage(ex.getMessage());
			eventBus.fireEvent(new StatusMessageEvent(message));
		}
		return success;
	}

	private void bind() {
		view.getSubmitButton().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent arg0) {
				if (updateBoat()) {
					boatService.updateBoat(boat, new AsyncCallback<Void>() {

						@Override
						public void onSuccess(Void arg0) {
							logger.info("Submit successful GoTo ListBoats");
							eventBus.fireEvent(new ListBoatsEvent());
							StatusMessage message = new StatusMessage(false);
							message.setStatus(Status.POSITIVE);
							message.setMessage("Boot erfolgreich geändert");
							eventBus.fireEvent(new StatusMessageEvent(message));
						}

						@Override
						public void onFailure(Throwable arg0) {
							ServiceHolderFactory.handleSessionFailure(arg0);
							logger.warning("Cannout update Boat:"
									+ arg0.getMessage());
							StatusMessage message = new StatusMessage(false);
							message.setStatus(Status.NEGATIVE);
							message.setMessage("Fehler beim Ändern: "
									+ arg0.getMessage());
							eventBus.fireEvent(new StatusMessageEvent(message));
						}
					});

				}
			}
		});

		view.getCancelButton().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent arg0) {
				eventBus.fireEvent(new ListBoatsEvent());
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
				boatService.deleteBoat(id, new AsyncCallback<Void>() {

					@Override
					public void onFailure(Throwable arg0) {
						ServiceHolderFactory.handleSessionFailure(arg0);
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
						msg.setMessage("Boat deleted");
						msg.setStatus(Status.POSITIVE);
						eventBus.fireEvent(new StatusMessageEvent(msg));
					}
				});
			}
		});
	}

}
