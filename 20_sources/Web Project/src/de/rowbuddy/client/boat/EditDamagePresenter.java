package de.rowbuddy.client.boat;

import java.util.logging.Logger;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.HasEnabled;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

import de.rowbuddy.boundary.dtos.MemberDTO;
import de.rowbuddy.client.ServerRequestHandler;
import de.rowbuddy.client.Presenter;
import de.rowbuddy.client.events.ListDamageEvent;
import de.rowbuddy.client.events.StatusMessageEvent;
import de.rowbuddy.client.model.StatusMessage;
import de.rowbuddy.client.model.StatusMessage.Status;
import de.rowbuddy.client.services.BoatRemoteServiceAsync;
import de.rowbuddy.entities.BoatDamage;
import de.rowbuddy.entities.Role.RoleName;
import de.rowbuddy.exceptions.RowBuddyException;

public class EditDamagePresenter implements Presenter {

	public interface Display {
		HasValue<String> getBoatName();

		HasValue<String> getDescription();

		HasValue<String> getAddInformation();

		HasValue<Boolean> getFixed();
		
		HasEnabled getUiFixed();

		HasClickHandlers getSaveButton();

		HasClickHandlers getCancelButton();

		Widget asWidget();
	}

	private Display view;
	private Long id;
	private BoatRemoteServiceAsync service;
	private EventBus eventBus;
	private BoatDamage damage;
	private Logger logger = Logger.getLogger(EditDamagePresenter.class
			.getName());
	private MemberDTO member;

	public EditDamagePresenter(Long id, Display view,
			BoatRemoteServiceAsync service, EventBus eventBus, MemberDTO member) {
		this.view = view;
		this.id = id;
		this.service = service;
		this.eventBus = eventBus;
		this.member = member;
	}

	@Override
	public void start(HasWidgets container) {
		container.clear();
		container.add(view.asWidget());
		bind();
		fetchDamage();
	}

	private void bind() {
		view.getSaveButton().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent arg0) {

				try {
					damage.setAdditionalInformation(view.getAddInformation()
							.getValue());
					damage.setFixed(view.getFixed().getValue());
					damage.setDamageDescription(view.getDescription()
							.getValue());
					if (service == null) {
						logger.info("service null");
					}
					
					service.updateDamage(damage, new ServerRequestHandler<Void>(eventBus, "Schaden aktualisieren", new ListDamageEvent(), null));
					
//					service.updateDamage(damage, new AsyncCallback<Void>() {
//
//						@Override
//						public void onSuccess(Void arg0) {
//							eventBus.fireEvent(new ListDamageEvent());
//							StatusMessage msg = new StatusMessage(false);
//							msg.setStatus(Status.POSITIVE);
//							msg.setMessage("Schaden erfolgreich bearbeitet");
//							eventBus.fireEvent(new StatusMessageEvent(msg));
//							eventBus.fireEvent(new ListDamageEvent());
//						}
//
//						@Override
//						public void onFailure(Throwable arg0) {
//							ServiceHolderFactory.handleSessionFailure(arg0);
//							logger.info("Cannot update");
//							logger.severe(arg0.getMessage());
//							StatusMessage msg = new StatusMessage(false);
//							msg.setStatus(Status.NEGATIVE);
//							msg.setMessage(arg0.getMessage());
//							arg0.printStackTrace();
//							eventBus.fireEvent(new StatusMessageEvent(msg));
//						}
//					});
				} catch (RowBuddyException ex) {
					StatusMessage msg = new StatusMessage(false);
					msg.setStatus(Status.NEGATIVE);
					msg.setMessage(ex.getMessage());
					logger.severe(ex.getMessage());
					eventBus.fireEvent(new StatusMessageEvent(msg));
				}

			}
		});

		view.getCancelButton().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent arg0) {
				eventBus.fireEvent(new ListDamageEvent());
			}
		});
		setPermissons();
	}
	
	private void setPermissons(){
		if(!member.isInRole(RoleName.ADMIN))
			view.getUiFixed().setEnabled(false);
	}

	private void fetchDamage() {
		
		service.getDamage(id, new ServerRequestHandler<BoatDamage>(eventBus, "Schaden anzeigen", null, new ListDamageEvent()){
			@Override
			public void onSuccess(BoatDamage arg0) {
				damage = arg0;
				view.getBoatName().setValue(damage.getBoat().getName());
				view.getDescription().setValue(damage.getDamageDescription());
				view.getAddInformation().setValue(
						damage.getAdditionalInformation());
				view.getFixed().setValue(damage.isFixed());
			}
		});
		
//		service.getDamage(id, new AsyncCallback<BoatDamage>() {
//
//			@Override
//			public void onSuccess(BoatDamage arg0) {
//				damage = arg0;
//				view.getBoatName().setValue(damage.getBoat().getName());
//				view.getDescription().setValue(damage.getDamageDescription());
//				view.getAddInformation().setValue(
//						damage.getAdditionalInformation());
//				view.getFixed().setValue(damage.isFixed());
//			}
//
//			@Override
//			public void onFailure(Throwable arg0) {
//				ServiceHolderFactory.handleSessionFailure(arg0);
//				logger.severe(arg0.getMessage());
//				eventBus.fireEvent(new ListDamageEvent());
//				StatusMessage message = new StatusMessage(false);
//				message.setStatus(Status.NEGATIVE);
//				message.setMessage("Schaden existiert nicht");
//				eventBus.fireEvent(new StatusMessageEvent(message));
//			}
//		});
	}

}
