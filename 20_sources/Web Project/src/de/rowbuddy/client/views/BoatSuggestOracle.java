package de.rowbuddy.client.views;

import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.SuggestOracle;

import de.rowbuddy.boundary.dtos.BoatDTO;
import de.rowbuddy.client.services.BoatRemoteServiceAsync;

public class BoatSuggestOracle extends SuggestOracle {

	private BoatRemoteServiceAsync service;
	private Logger logger = Logger.getLogger(BoatSuggestOracle.class.getName());
	private List<ItemSuggestion> suggestions;

	public BoatSuggestOracle(BoatRemoteServiceAsync service) {
		this.service = service;
		suggestions = new LinkedList<ItemSuggestion>();
	}

	public class ItemSuggestion implements Suggestion {
		private BoatDTO boat;

		public ItemSuggestion(BoatDTO boat) {
			this.boat = boat;
		}

		@Override
		public String getDisplayString() {
			// TODO Auto-generated method stub
			return boat.getName();
		}

		@Override
		public String getReplacementString() {
			// TODO Auto-generated method stub
			return boat.getName();
		}

		public BoatDTO getBoat() {
			return boat;
		}

	}

	public BoatDTO getSuggestion() {
		if (suggestions.size() > 1 || suggestions.isEmpty()) {
			throw new IllegalArgumentException("Multiple suggestions available");
		}
		return suggestions.get(0).getBoat();
	}

	@Override
	public void requestSuggestions(final Request req, final Callback arg1) {
		service.search(req.getQuery(), new AsyncCallback<List<BoatDTO>>() {

			@Override
			public void onSuccess(List<BoatDTO> arg0) {
				SuggestOracle.Response resp = new SuggestOracle.Response();
				suggestions.clear();
				for (BoatDTO boat : arg0) {
					logger.info("Add: " + boat.getName());
					suggestions.add(new ItemSuggestion(boat));
				}
				resp.setSuggestions(suggestions);
				arg1.onSuggestionsReady(req, resp);
			}

			@Override
			public void onFailure(Throwable arg0) {
			}
		});
	}
}
