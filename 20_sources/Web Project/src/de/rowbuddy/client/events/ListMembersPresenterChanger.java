package de.rowbuddy.client.events;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.GwtEvent.Type;
import com.google.gwt.user.client.ui.HasWidgets;

import de.rowbuddy.boundary.dtos.MemberDTO;
import de.rowbuddy.client.presenter.Presenter;
import de.rowbuddy.client.presenter.member.ListMembersPresenter;
import de.rowbuddy.client.services.MemberRemoteServiceAsync;
import de.rowbuddy.client.views.member.ListMembersView;

public class ListMembersPresenterChanger extends PresenterChanger {

	private MemberRemoteServiceAsync memberService;

	public ListMembersPresenterChanger(MemberRemoteServiceAsync memberService,
			HasWidgets targetWidget, EventBus eventBus, MemberDTO member) {
		super(targetWidget, eventBus, member);
		this.memberService = memberService;
	}

	@Override
	public AbstractEvent<?> toEvent(String historyItem) {
		return new ListMembersEvent();
	}

	@Override
	public Presenter createPresenter(AbstractEvent<?> event) {
		return new ListMembersPresenter(memberService, new ListMembersView(),
				eventBus);
	}

	@Override
	protected <T extends PresenterChanger> Type<T> getType() {
		return (Type<T>) ListMembersEvent.TYPE;
	}

	@Override
	protected String getHistoryIdentifier() {
		return ListMembersEvent.HISTORY_IDENTIFIER;
	}

}
