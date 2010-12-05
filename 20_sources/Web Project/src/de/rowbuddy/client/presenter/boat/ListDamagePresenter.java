package de.rowbuddy.client.presenter.boat;

import java.util.List;
import java.util.logging.Logger;

import com.google.gwt.event.logical.shared.HasValueChangeHandlers;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

import de.rowbuddy.boundary.dtos.DamageDTO;
import de.rowbuddy.client.presenter.Presenter;
import de.rowbuddy.client.services.BoatRemoteServiceAsync;

public class ListDamagePresenter implements Presenter{
	
	public interface Display{
		HasValueChangeHandlers<Boolean> historyEnabled();
		void addDamageRow(String name, String reporter, String date, boolean isOpen);
		void clear();
		Widget asWidget();
	}
	
	private Display view;
	private BoatRemoteServiceAsync service;
	private SimpleEventBus eventBus;
	private Logger logger = Logger.getLogger(ListDamagePresenter.class.getName());
	private List<DamageDTO> fetchedDamages;
	
	public ListDamagePresenter(Display view, BoatRemoteServiceAsync service, SimpleEventBus eventBus){
		this.view = view;
		this.service = service;
		this.eventBus = eventBus;
	}
	
	@Override
	public void start(HasWidgets container) {
		container.clear();
		container.add(view.asWidget());
		bind();
		fetchDamages();
	}
	
	private void fetchDamages(){
		service.getDamages(new AsyncCallback<List<DamageDTO>>() {
			
			@Override
			public void onSuccess(List<DamageDTO> arg0) {
				fetchedDamages = arg0;
				for(DamageDTO damage : fetchedDamages){
					view.addDamageRow(damage.getBootName(), damage.getMember(), damage.getDate().toString(), damage.isFixed());
				}
			}
			
			@Override
			public void onFailure(Throwable arg0) {
				logger.severe(arg0.getMessage());
			}
		});
	}
	
	private void bind(){
		view.historyEnabled().addValueChangeHandler(new ValueChangeHandler<Boolean>() {
			
			@Override
			public void onValueChange(ValueChangeEvent<Boolean> arg0) {
				
			}
		});
	}
	
}
