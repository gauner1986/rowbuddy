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

	private static <Sync, Async> Async createService(Class<Sync> syncType,
			Class<Async> asyncType, String serverImplementation) {
		Async service = GWT.create(syncType);
		((ServiceDefTarget) service).setServiceEntryPoint(GWT
				.getHostPageBaseURL() + serverImplementation);
		logger.info("Service registerd: " + GWT.getHostPageBaseURL()
				+ serverImplementation);
		return service;
	}

	public static BoatRemoteServiceAsync getBoatService() {
		if (boatService == null) {
			boatService = createService(BoatRemoteService.class,
					BoatRemoteServiceAsync.class, "BoatRemoteServiceImpl");
		}
		return boatService;
	}

	public static LogbookRemoteServiceAsync getLogbookService() {
		if (logbookService == null) {
			logbookService = createService(LogbookRemoteService.class,
					LogbookRemoteServiceAsync.class, "LogbookRemoteServiceImpl");
		}
		return logbookService;
	}

	public static RouteRemoteServiceAsync getRouteService() {
		if (routeService == null) {
			routeService = createService(RouteRemoteService.class,
					RouteRemoteServiceAsync.class, "RouteRemoteServiceImpl");
		}
		return routeService;

	}

	public static MemberRemoteServiceAsync getMemberService() {
		if (memberService == null) {
			memberService = createService(MemberRemoteService.class,
					MemberRemoteServiceAsync.class, "MemberRemoteServiceImpl");
		}
		return memberService;

	}

	public static StatisticRemoteServiceAsync getStatisticService() {
		if (statisticService == null) {
			statisticService = createService(StatisticRemoteService.class,
					StatisticRemoteServiceAsync.class,
					"StatisticRemoteServiceImpl");
		}
		return statisticService;
	}

	public static SessionManagerAsync getSessionManager() {
		if (sessionManager == null) {
			sessionManager = createService(SessionManager.class,
					SessionManagerAsync.class, "SessionManagerImpl");
		}
		return sessionManager;

	}

	public static void fetchSessionMember(final Runnable run) {
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

	public static void handleSessionFailure(Throwable arg0) {
		if (arg0 instanceof NotLoggedInException) {
			Window.Location.assign(GWT.getHostPageBaseURL() + "Login.jsp");
		}
	}

}
