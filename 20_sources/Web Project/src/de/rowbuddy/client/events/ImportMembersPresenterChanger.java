package de.rowbuddy.client.events;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.GwtEvent.Type;
import com.google.gwt.user.client.ui.HasWidgets;

import de.rowbuddy.boundary.dtos.MemberDTO;
import de.rowbuddy.client.presenter.Presenter;
import de.rowbuddy.client.presenter.member.ImportMembersPresenter;
import de.rowbuddy.client.services.MemberRemoteServiceAsync;
import de.rowbuddy.client.views.member.ImportMembersView;

public class ImportMembersPresenterChanger extends PresenterChanger {

	private final MemberRemoteServiceAsync memberService;

	public ImportMembersPresenterChanger(HasWidgets targetWidget,
			EventBus eventBus, MemberRemoteServiceAsync memberService, MemberDTO member) {
		super(targetWidget, eventBus, member);

		this.memberService = memberService;
	}

	@Override
	public AbstractEvent<?> toEvent(String historyItem) {
		return new ImportMembersEvent();
	}

	@Override
	public Presenter createPresenter(AbstractEvent<?> event) {
		return new ImportMembersPresenter(memberService,
				new ImportMembersView(), eventBus);
	}

	@Override
	protected <T extends PresenterChanger> Type<T> getType() {
		return (Type<T>) ImportMembersEvent.TYPE;
	}

	@Override
	protected String getHistoryIdentifier() {
		return ImportMembersEvent.HISTORY_IDENTIFIER;
	}

}
