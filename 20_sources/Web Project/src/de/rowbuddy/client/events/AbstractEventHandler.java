package de.rowbuddy.client.events;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent.Type;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.HasWidgets;

import de.rowbuddy.client.FadeAnimation;
import de.rowbuddy.client.presenter.Presenter;

public abstract class AbstractEventHandler implements EventHandler,
		ValueChangeHandler<String> {

	private HasWidgets targetWidget;
	protected final EventBus eventBus;

	public AbstractEventHandler(HasWidgets targetWidget, EventBus eventBus) {
		this.targetWidget = targetWidget;
		this.eventBus = eventBus;

		this.eventBus.addHandler(getType(), this);
		History.addValueChangeHandler(this);

		// TODO: notify appController for status bar clearing
	}

	public void processEvent(AbstractEvent<?> event) {
		History.newItem(toHistoryItem(event));
		Presenter presenter = createPresenter(event);
		FadeAnimation fade = new FadeAnimation(targetWidget, presenter);
		fade.run(400);
	}

	public void processHistoryEntry(String historyEntry) {
		AbstractEvent<?> event = toEvent(historyEntry);
		Presenter presenter = createPresenter(event);
		FadeAnimation fade = new FadeAnimation(targetWidget, presenter);
		fade.run(200);
	}

	@Override
	public void onValueChange(ValueChangeEvent<String> arg0) {
		String historyToken = arg0.getValue();
		if (historyToken.startsWith(getHistoryIdentifier())) {
			processHistoryEntry(historyToken);
		}
	}

	public abstract String toHistoryItem(AbstractEvent<?> event);

	public abstract AbstractEvent<?> toEvent(String historyItem);

	public abstract Presenter createPresenter(AbstractEvent<?> event);

	protected abstract <T extends AbstractEventHandler> Type<T> getType();

	protected abstract String getHistoryIdentifier();

}
