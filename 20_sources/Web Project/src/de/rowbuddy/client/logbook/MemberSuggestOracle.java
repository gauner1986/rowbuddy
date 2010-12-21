package de.rowbuddy.client.logbook;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.SuggestOracle;

import de.rowbuddy.boundary.dtos.MemberDTO;
import de.rowbuddy.client.services.LogbookRemoteServiceAsync;

public class MemberSuggestOracle extends SuggestOracle {

	private LogbookRemoteServiceAsync service;
	private HashMap<String, MemberDTO> suggestions;
	private Request request;
	private Callback callback;

	public MemberSuggestOracle(LogbookRemoteServiceAsync service) {
		this.service = service;
		suggestions = new HashMap<String, MemberDTO>();
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
	
	public int getNumberOfSuggestions() {
		return this.suggestions.size();
	}

	public MemberDTO getSuggestion(String value) {
		return suggestions.get(value);
	}

	@Override
	public void requestSuggestions(Request req, Callback arg1) {
		this.request = req;
		this.callback = arg1;
		service.searchMember(req.getQuery(), new AsyncCallback<List<MemberDTO>>() {

			@Override
			public void onSuccess(List<MemberDTO> arg0) {
				suggestions.clear();
				SuggestOracle.Response resp = new SuggestOracle.Response();
				List<Suggestion> suggs = new LinkedList<SuggestOracle.Suggestion>();
				for (MemberDTO member : arg0) {
					suggestions.put(member.getFullName(), member);
					suggs.add(new ItemSuggestion(member.getFullName()));
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
