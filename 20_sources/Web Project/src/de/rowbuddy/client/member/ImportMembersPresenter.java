package de.rowbuddy.client.member;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

import de.rowbuddy.client.ServerRequestHandler;
import de.rowbuddy.client.Presenter;
import de.rowbuddy.client.events.ListMembersEvent;
import de.rowbuddy.client.services.MemberRemoteServiceAsync;

public class ImportMembersPresenter implements Presenter {

	private Display view;
	private EventBus eventBus;
	private MemberRemoteServiceAsync memberService;

	public interface Display {
		Widget asWidget();

		HasClickHandlers getImportButton();

		String getImportData();
	}

	public ImportMembersPresenter(MemberRemoteServiceAsync memberService,
			Display view, EventBus eventBus) {
		this.memberService = memberService;
		this.view = view;
		this.eventBus = eventBus;
	}

	@Override
	public void start(HasWidgets container) {
		bind();
		container.clear();
		container.add(view.asWidget());
	}

	private void bind() {
		view.getImportButton().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent arg0) {
				String importData = view.getImportData();

				memberService.importMembers(importData, new ServerRequestHandler<Integer>(eventBus, "Mitglieder importieren", new ListMembersEvent(), null));
				
//				memberService.importMembers(importData,
//						new AsyncCallback<Integer>() {
//
//							@Override
//							public void onSuccess(Integer membersImported) {
//								StatusMessage message = new StatusMessage();
//								message.setMessage(membersImported
//										+ " MemberDTO erfolgreich importiert");
//								message.setStatus(Status.POSITIVE);
//								message.setAttached(false);
//								eventBus.fireEvent(new ListMembersEvent());
//								eventBus.fireEvent(new StatusMessageEvent(
//										message));
//							}
//
//							@Override
//							public void onFailure(Throwable arg0) {
//								ServiceHolderFactory.handleSessionFailure(arg0);
//								StatusMessage message = new StatusMessage();
//								message.setMessage(arg0.getMessage());
//								message.setStatus(Status.NEGATIVE);
//								message.setAttached(false);
//								eventBus.fireEvent(new StatusMessageEvent(
//										message));
//							}
//						});
			}
		});

	}
}
