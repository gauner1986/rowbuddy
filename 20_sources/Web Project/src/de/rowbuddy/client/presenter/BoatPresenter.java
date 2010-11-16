package de.rowbuddy.client.presenter;

import java.util.Collection;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

import de.rowbuddy.business.dtos.BoatOverview;
import de.rowbuddy.client.events.AddBoatEvent;
import de.rowbuddy.client.services.BoatRemoteServiceAsync;

public class BoatPresenter implements Presenter{
	public interface Display{
		Widget asWidget();
		HasClickHandlers getAddButton();
		void setData(Collection<BoatOverview> boats);
		int getClickedRow(ClickEvent event);
	}
	
	private Display view;
	private BoatRemoteServiceAsync boatService;
	private SimpleEventBus eventBus;
	private Collection<BoatOverview> fetchedBoats;
	
	public BoatPresenter(BoatRemoteServiceAsync boatService, Display view, SimpleEventBus eventBus) {
		this.view = view;
		this.boatService = boatService;
		this.eventBus = eventBus;
	}
	
	private void bind(){
		view.getAddButton().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent arg0) {
				eventBus.fireEvent(new AddBoatEvent());
			}
		});
	}

	@Override
	public void start(HasWidgets container) {
		bind();
		container.clear();
		container.add(view.asWidget());
		fetchBoats();
	}
	
	private void fetchBoats(){
		boatService.getBoatOverview(new AsyncCallback<Collection<BoatOverview>>() {
			
			@Override
			public void onSuccess(Collection<BoatOverview> arg0) {
				fetchedBoats = arg0;
				view.setData(fetchedBoats);
			} 
			
			@Override
			public void onFailure(Throwable arg0) {
				Window.alert("error");
			}
		});
	}
	
}
