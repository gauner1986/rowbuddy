package de.rowbuddy.client.presenter.logbook;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.SuggestOracle;

import de.rowbuddy.boundary.dtos.RouteDTO;
import de.rowbuddy.client.services.LogbookRemoteServiceAsync;

public class RouteSuggestOracle extends SuggestOracle {

	private LogbookRemoteServiceAsync service;
	private Logger logger = Logger.getLogger(RouteSuggestOracle.class.getName());
	// private List<ItemSuggestion> suggestions;
	private HashMap<String, RouteDTO> suggestions;
	private Request request;
	private Callback callback;

	public RouteSuggestOracle(LogbookRemoteServiceAsync service) {
		this.service = service;
		suggestions = new HashMap<String, RouteDTO>();
	}

	private class ItemSuggestion implements Suggestion {

		private String replacement;

		public ItemSuggestion(String replacement) {
			this.replacement = replacement;
		}

		@Override
		public String getDisplayString() {
			// TODO Auto-generated method stub
			return replacement;
		}

		@Override
		public String getReplacementString() {
			// TODO Auto-generated method stub
			return replacement;
		}

	}

	public RouteDTO getSuggestion(String value) {
		return suggestions.get(value);
	}

	@Override
	public void requestSuggestions(Request req, Callback arg1) {
		this.request = req;
		this.callback = arg1;
		service.searchRoute(req.getQuery(), new AsyncCallback<List<RouteDTO>>() {

			@Override
			public void onSuccess(List<RouteDTO> arg0) {
				SuggestOracle.Response resp = new SuggestOracle.Response();
				logger.info("New suggestions received!");
				List<Suggestion> suggs = new LinkedList<SuggestOracle.Suggestion>();
				for (RouteDTO route : arg0) {
					suggestions.put(route.getName(), route);
					suggs.add(new ItemSuggestion(route.getName()));
				}
				resp.setSuggestions(suggs);
				callback.onSuggestionsReady(request, resp);
			}

			@Override
			public void onFailure(Throwable arg0) {
			}
		});
	}
}
