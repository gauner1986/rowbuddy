package de.rowbuddy.client.services;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

import de.rowbuddy.boundary.dtos.BoatDTO;
import de.rowbuddy.boundary.dtos.DamageDTO;
import de.rowbuddy.entities.Boat;
import de.rowbuddy.entities.BoatDamage;

public interface BoatRemoteServiceAsync {
	void getBoatOverview(AsyncCallback<List<BoatDTO>> callback);

	void addBoat(Boat boat, AsyncCallback<Void> callback);

	void updateBoat(Boat boat, AsyncCallback<Void> callback);

	void getBoat(Long id, AsyncCallback<Boat> callback);

	void deleteBoat(Long id, AsyncCallback<Void> callback);

	void getOpenDamages(AsyncCallback<List<DamageDTO>> callback);

	void getAllDamages(AsyncCallback<List<DamageDTO>> callback);

	void getDamage(Long id, AsyncCallback<BoatDamage> callback);

	void updateDamage(BoatDamage damage, AsyncCallback<Void> callback);

	void search(String query, AsyncCallback<List<BoatDTO>> callback);
}
