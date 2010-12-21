package de.rowbuddy.boundary;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateful;
import javax.interceptor.ExcludeClassInterceptors;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.sun.xml.bind.StringInputStream;

import de.rowbuddy.boundary.converter.MemberDTOConverter;
import de.rowbuddy.boundary.dtos.BoatDTO;
import de.rowbuddy.boundary.dtos.DamageDTO;
import de.rowbuddy.boundary.dtos.MemberDTO;
import de.rowbuddy.boundary.dtos.MonthsStatisticDTO;
import de.rowbuddy.boundary.dtos.PersonalTripDTO;
import de.rowbuddy.boundary.dtos.RouteDTO;
import de.rowbuddy.boundary.dtos.TripDTO;
import de.rowbuddy.boundary.dtos.TripMemberDTO;
import de.rowbuddy.business.BoatManagement;
import de.rowbuddy.business.Logbook;
import de.rowbuddy.business.Logbook.ListType;
import de.rowbuddy.business.MemberManagement;
import de.rowbuddy.business.MemberReader;
import de.rowbuddy.business.RouteManagement;
import de.rowbuddy.entities.Boat;
import de.rowbuddy.entities.BoatDamage;
import de.rowbuddy.entities.Member;
import de.rowbuddy.entities.Role;
import de.rowbuddy.entities.Role.RoleName;
import de.rowbuddy.entities.Route;
import de.rowbuddy.entities.Trip;
import de.rowbuddy.entities.TripMember;
import de.rowbuddy.exceptions.NotLoggedInException;
import de.rowbuddy.exceptions.RowBuddyException;

/**
 * Session Bean implementation class RowBuddyFacade
 */
@Interceptors(PermissionInterceptor.class)
@Stateful
@LocalBean
public class RowBuddyFacade {

	private Member member;

	@PersistenceContext
	EntityManager em;
	@EJB
	private BoatManagement boatManagement;
	@EJB
	private BoatBoundary boatBoundary;
	@EJB
	private Logbook logbook;
	@EJB
	private LogbookBoundary logbookBoundary;
	@EJB
	private RouteManagement routeManagement;
	@EJB
	private MemberManagement memberMgmt;
	@EJB
	private MemberBoundary memberBoundary;
	@EJB
	private RouteBoundary routeBoundary;
	
	private MemberDTOConverter memberConv = new MemberDTOConverter();

	/**
	 * Default constructor.
	 */
	public RowBuddyFacade() {
	}

	@ExcludeClassInterceptors
	public void login(String email, String password)
			throws NotLoggedInException {
		this.member = memberMgmt.checkLogin(email, password);
	}

	public MemberDTO getMember() {
		return memberConv.getDto(member);
	}

	@ExcludeClassInterceptors
	public void logout() {
		member = null;
	}

	@ExcludeClassInterceptors
	public boolean isLoggedIn() {
		return (member != null);
	}

	public List<BoatDTO> getBoatOverview() {
		return boatBoundary.getBoatOverview();
	}

	public Boat getBoat(Long id) throws RowBuddyException {
		return boatManagement.getBoat(id);
	}

	@AllowedRoles(values = { Role.RoleName.ADMIN })
	public Boat addBoat(Boat addBoat) throws RowBuddyException {
		return boatManagement.addBoat(addBoat);
	}

	@AllowedRoles(values = { Role.RoleName.ADMIN })
	public Boat updateBoat(Boat updateBoat) throws RowBuddyException {
		return boatManagement.updateBoat(updateBoat);
	}

	@AllowedRoles(values = { Role.RoleName.ADMIN })
	public void deleteBoat(Long id) throws RowBuddyException {
		boatManagement.deleteBoat(id);
	}

	public void logRowedTrip(TripDTO rowedTrip, long boatId, long routeId, List<TripMemberDTO> tripMembersDTO) throws RowBuddyException {
		Trip trip = new Trip();
		trip.setEndDate(rowedTrip.getEndDate());
		trip.setStartDate(rowedTrip.getStartDate());
		trip.setFinished(true);
		trip.setBoat(getBoat(boatId));
		trip.setRoute(getRoute(routeId));
		List<TripMember> tripMembers = new LinkedList<TripMember>();
		for (TripMemberDTO dto : tripMembersDTO) {
			TripMember tm = new TripMember();
			tm.setMember(memberMgmt.getMember(dto.getMember().getId()));
			tm.setTripMemberType(dto.getTripMemberType());
			tripMembers.add(tm);
		}
		trip.setTripMembers(tripMembers);
		logbook.logRowedTrip(trip, this.member);
	}

	public void startTrip(TripDTO rowedTrip, long boatId, long routeId, List<TripMemberDTO> tripMembersDTO) throws RowBuddyException {
		Trip trip = new Trip();
		trip.setStartDate(rowedTrip.getStartDate());
		trip.setFinished(true);
		trip.setBoat(getBoat(boatId));
		trip.setRoute(getRoute(routeId));
		List<TripMember> tripMembers = new LinkedList<TripMember>();
		for (TripMemberDTO dto : tripMembersDTO) {
			TripMember tm = new TripMember();
			tm.setMember(memberMgmt.getMember(dto.getMember().getId()));
			tm.setTripMemberType(dto.getTripMemberType());
			tripMembers.add(tm);
		}
		trip.setTripMembers(tripMembers);
		logbook.startTrip(trip, this.member);
	}

	public Trip getTrip(Long id) throws RowBuddyException {
		return logbook.getTrip(id);
	}

	public List<TripDTO> getOpenTrips() {
		return logbookBoundary.getOpenTrips(this.member);
	}

	public void finishTrip(Trip openTrip) throws RowBuddyException {
		logbook.finishTrip(openTrip, this.member);
	}

	public List<PersonalTripDTO> getPersonalTrips() {
		return logbookBoundary.getPersonalTrips(this.member, ListType.All);
	}

	public List<PersonalTripDTO> getPersonalOpenTrips() {
		return logbookBoundary.getPersonalTrips(this.member, ListType.OpenOnly);
	}

	public List<DamageDTO> getOpenDamages() {
		return boatBoundary.getOpenDamages();
	}

	public List<DamageDTO> getAllDamages() {
		return boatBoundary.getAllDamages();
	}

	public BoatDamage getDamage(Long id) throws RowBuddyException {
		return boatManagement.getDamage(id);
	}

	public void updateDamage(BoatDamage damage) {
		try {
			boatManagement.updateDamage(damage);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public List<BoatDTO> searchBoat(String search) {
		return boatBoundary.searchBoat(search);
	}

	public List<BoatDTO> searchBoatNotLocked(String search) {
		return boatBoundary.searchBoatNotLocked(search);
	}

	public List<RouteDTO> searchRoute(String search) {
		return routeBoundary.searchRoute(search);
	}

	public List<MemberDTO> searchMember(String search) {
		return memberBoundary.searchMember(search);
	}

	public void addDamage(BoatDamage damage, Long boatId)
			throws RowBuddyException {
		boatManagement.addDamage(damage, member, boatId);
	}

	public Route addRoute(Route newRoute) throws RowBuddyException {
		return routeManagement.addRoute(newRoute, member);
	}

	public List<Route> getRouteList() throws RowBuddyException {
		return routeManagement.getRouteList();
	}

	public Route editRoute(Route route) throws RowBuddyException {
		return routeManagement.editRoute(route, member);
	}

	public Route getRoute(Long id) throws RowBuddyException {
		return routeManagement.getRoute(id);
	}

	public void deleteRoute(Long id) throws RowBuddyException {
		routeManagement.deleteRoute(id, member);
	}

	public boolean canEditRoute(Route route) {
		return routeManagement.canEditRoute(route, member);
	}

	@AllowedRoles(values = { Role.RoleName.ADMIN })
	public Member addMember(Member addMember, RoleName... roles)
			throws RowBuddyException {
		return memberMgmt.addMember(addMember, roles);
	}

	@AllowedRoles(values = { Role.RoleName.ADMIN })
	public Member updateMember(Member member) throws RowBuddyException {
		return memberMgmt.updateMember(member);
	}

	@AllowedRoles(values = { Role.RoleName.ADMIN })
	public int importMembers(String importData) throws RowBuddyException {

		List<Member> members = new LinkedList<Member>();
		try {
			StringInputStream sis = new StringInputStream(importData);
			MemberReader memberReader = new MemberReader(sis);
			Member member;
			while ((member = memberReader.readMember()) != null) {
				members.add(member);
			}
		} catch (IOException ex) {
			throw new RowBuddyException(ex.toString());
		} catch (NullPointerException ex) {
			throw new RowBuddyException("Bitte Daten zum Importieren einfügen.");
		}
		memberMgmt.importMembers(members, this.member);
		return members.size();
	}

	@AllowedRoles(values = { Role.RoleName.ADMIN })
	public List<MemberDTO> getMembers() {
		return memberBoundary.getMembers();
	}

	public Member getMember(Long id) throws RowBuddyException {
		return memberMgmt.getMember(id);
	}
	
	public MonthsStatisticDTO getMonthsStatistic(int year) {
		// TODO get from StatisticManagement
		System.out.println("RowBuddyFacade");
		MonthsStatisticDTO dto = new MonthsStatisticDTO();
		int[] ar = {150, 200, 349, 344, 235, 456, 327, 438, 259, 510, 411, 312};
		dto.setMonths(ar);
		return dto;
	}

}
