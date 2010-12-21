package de.rowbuddy.client;

import java.util.logging.Logger;

import com.google.gwt.core.client.GWT;
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

	private static void registerService(ServiceDefTarget service,
			String serverImplementation) {
		service.setServiceEntryPoint(GWT.getHostPageBaseURL()
				+ serverImplementation);
		logger.info("Service registerd: " + GWT.getHostPageBaseURL()
				+ serverImplementation);
	}

	public static BoatRemoteServiceAsync getBoatService() {
		if (boatService == null) {
			boatService = GWT.create(BoatRemoteService.class);
			registerService((ServiceDefTarget) boatService,
					"BoatRemoteServiceImpl");
		}
		return boatService;
	}

	public static LogbookRemoteServiceAsync getLogbookService() {
		if (logbookService == null) {
			logbookService = GWT.create(LogbookRemoteService.class);
			registerService((ServiceDefTarget) logbookService,
					"LogbookRemoteServiceImpl");
		}
		return logbookService;
	}

	public static RouteRemoteServiceAsync getRouteService() {
		if (routeService == null) {
			routeService = GWT.create(RouteRemoteService.class);
			registerService((ServiceDefTarget) routeService,
					"RouteRemoteServiceImpl");
		}
		return routeService;

	}

	public static MemberRemoteServiceAsync getMemberService() {
		if (memberService == null) {
			memberService = GWT.create(MemberRemoteService.class);
			registerService((ServiceDefTarget) memberService,
					"MemberRemoteServiceImpl");
		}
		return memberService;

	}

	public static StatisticRemoteServiceAsync getStatisticService() {
		if (statisticService == null) {
			statisticService = GWT.create(StatisticRemoteService.class);
			registerService((ServiceDefTarget) statisticService,
					"StatisticRemoteServiceImpl");
		}
		return statisticService;
	}

	public static SessionManagerAsync getSessionManager() {
		if (sessionManager == null) {
			sessionManager = GWT.create(SessionManager.class);
			registerService((ServiceDefTarget) sessionManager,
					"SessionManagerImpl");
		}
		return sessionManager;

	}

	public static void fetchSessionMember(final Runnable run) {
		ServiceHolderFactory.getSessionManager().getMember(
				new ServerRequestHandler<MemberDTO>(null,
						"Eingeloggten Member laden", null, null) {

					@Override
					public void onSuccess(MemberDTO arg0) {
						sessionMember = arg0;
						logger.info("Member fetched");
						run.run();
					}
				});
	}
}
