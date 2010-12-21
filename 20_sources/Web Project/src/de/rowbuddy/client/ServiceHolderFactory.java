package de.rowbuddy.client;

import java.util.logging.Logger;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;

import de.rowbuddy.boundary.dtos.MemberDTO;
import de.rowbuddy.client.services.BoatRemoteService;
import de.rowbuddy.client.services.BoatRemoteServiceAsync;
import de.rowbuddy.client.services.LogbookRemoteService;
import de.rowbuddy.client.services.LogbookRemoteServiceAsync;
import de.rowbuddy.client.services.MemberRemoteService;
import de.rowbuddy.client.services.MemberRemoteServiceAsync;
import de.rowbuddy.client.services.RouteRemoteService;
import de.rowbuddy.client.services.RouteRemoteServiceAsync;
import de.rowbuddy.client.services.SessionManager;
import de.rowbuddy.client.services.SessionManagerAsync;
import de.rowbuddy.client.services.StatisticRemoteService;
import de.rowbuddy.client.services.StatisticRemoteServiceAsync;
import de.rowbuddy.exceptions.NotLoggedInException;

public class ServiceHolderFactory {

	private static Logger logger = Logger.getLogger(GWTEntryPoint.class
		.getName());

	private static SessionManagerAsync sessionManager = null;
	private static BoatRemoteServiceAsync boatService = null;
	private static LogbookRemoteServiceAsync logbookService = null;
	private static RouteRemoteServiceAsync routeService = null;
	private static MemberRemoteServiceAsync memberService = null;
	private static StatisticRemoteServiceAsync statisticService = null;
	private static MemberDTO sessionMember = null;

	public static MemberDTO getSessionMember() {
		return sessionMember;
	}

	public static BoatRemoteServiceAsync getBoatService() {
		if (boatService == null) {
			boatService = (BoatRemoteServiceAsync) GWT
				.create(BoatRemoteService.class);
			((ServiceDefTarget) boatService).setServiceEntryPoint(GWT
				.getHostPageBaseURL() + "BoatRemoteServiceImpl");
			logger.info("Service registerd: " + GWT.getHostPageBaseURL()
					+ "BoatRemoteServiceImpl");
			return boatService;
		} else {
			return boatService;
		}
	}

	public static LogbookRemoteServiceAsync getLogbookService() {
		if (logbookService == null) {
			logbookService = (LogbookRemoteServiceAsync) GWT
				.create(LogbookRemoteService.class);
			((ServiceDefTarget) logbookService).setServiceEntryPoint(GWT
				.getHostPageBaseURL() + "LogbookRemoteServiceImpl");
			logger.info("Service registerd: " + GWT.getHostPageBaseURL()
					+ "LogbookRemoteServiceImpl");
			return logbookService;
		} else {
			return logbookService;
		}
	}

	public static RouteRemoteServiceAsync getRouteService() {
		if (routeService == null) {
			routeService = (RouteRemoteServiceAsync) GWT
				.create(RouteRemoteService.class);
			((ServiceDefTarget) routeService).setServiceEntryPoint(GWT
				.getHostPageBaseURL() + "RouteRemoteServiceImpl");
			logger.info("Service registerd: " + GWT.getHostPageBaseURL()
					+ "RouteRemoteServiceImpl");
			return routeService;
		} else {
			return routeService;
		}
	}

	public static MemberRemoteServiceAsync getMemberService() {
		if (memberService == null) {
			memberService = (MemberRemoteServiceAsync) GWT
				.create(MemberRemoteService.class);
			((ServiceDefTarget) memberService).setServiceEntryPoint(GWT
				.getHostPageBaseURL() + "MemberRemoteServiceImpl");
			logger.info("Service registerd: " + GWT.getHostPageBaseURL()
					+ "MemberRemoteServiceImpl");
			return memberService;
		} else {
			return memberService;
		}
	}


	public static StatisticRemoteServiceAsync getStatisticService() {
		if (statisticService == null) {
			statisticService = (StatisticRemoteServiceAsync) GWT.create(StatisticRemoteService.class);
			((ServiceDefTarget) statisticService).setServiceEntryPoint(GWT
				.getHostPageBaseURL() + "StatisticRemoteServiceImpl");
			logger.info("Service registerd: " + GWT.getHostPageBaseURL()
					+ "StatisticRemoteServiceImpl");
			return statisticService;
		} else {
			return statisticService;
		}
	}
	public static void fetchSessionMember(final Runnable run){
		ServiceHolderFactory.getSessionManager().getMember(
			new AsyncCallback<MemberDTO>() {

				@Override
				public void onSuccess(MemberDTO arg0) {
					sessionMember = arg0;
					logger.info("Member fetched");
					run.run();
				}

				@Override
				public void onFailure(Throwable arg0) {
					handleSessionFailure(arg0);
					logger.severe("Failed to fetch member");
					logger.info(arg0.getMessage());
				}
			});
	}

	public static SessionManagerAsync getSessionManager() {
		if (sessionManager == null) {
			sessionManager = (SessionManagerAsync) GWT
				.create(SessionManager.class);
			((ServiceDefTarget) sessionManager).setServiceEntryPoint(GWT
				.getHostPageBaseURL() + "SessionManagerImpl");
			return sessionManager;
		} else {
			return sessionManager;
		}
	}
	
	public static void handleSessionFailure(Throwable arg0){
		if(arg0 instanceof NotLoggedInException){
			Window.Location.assign(GWT.getHostPageBaseURL() + "Login.jsp");
		}
	}

}
