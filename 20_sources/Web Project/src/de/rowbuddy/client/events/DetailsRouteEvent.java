package de.rowbuddy.client.events;

public class DetailsRouteEvent extends IdEvent<DetailsRoutePresenterChanger> {
	
	public static final Type<DetailsRoutePresenterChanger> TYPE = new Type<DetailsRoutePresenterChanger>();
	public static final String HISTORY_IDENTIFIER = "DetailsRoute";
	
	public DetailsRouteEvent(Long id){
		super(HISTORY_IDENTIFIER, id);
	}
	
	@Override
	public Type<DetailsRoutePresenterChanger> getAssociatedType(){
		return TYPE;
	}
	
}
