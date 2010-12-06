package de.rowbuddy.server.servlets;

import java.io.IOException;
import java.util.Date;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.rowbuddy.business.BoatManagement;
import de.rowbuddy.business.MemberManagement;
import de.rowbuddy.entities.Boat;
import de.rowbuddy.entities.BoatDamage;
import de.rowbuddy.entities.Member;
import de.rowbuddy.exceptions.RowBuddyException;

/**
 * Servlet implementation class DataInitialization
 */
@WebServlet("/DataInitialization")
public class DataInitialization extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB
	private BoatManagement boatManagement;
	
	@EJB
	private MemberManagement memberManagement;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DataInitialization() {
        super();
        // TODO Auto-generated constructor stub
    }

    @Override
    public void init(){
    	Boat b1 = new Boat();
    	Member member = createTestMember();
    	try {
			b1.setName("Test Boot");
			b1.setCoxed(false);
	    	b1.setNumberOfSeats(3);
	    	boatManagement.addBoat(b1);
	    	b1.setId(null);
	    	b1.setName("Unsinkbar");
	    	boatManagement.addBoat(b1);
	    	b1.setId(null);
	    	b1.setName("Unsinkbar 2");
	    	b1.setCoxed(true);
	    	b1.setLocked(true);
	    	boatManagement.addBoat(b1);
	    	b1.setId(null);
	    	b1.setNumberOfSeats(10);
	    	b1.setName("Dicke Berta");
	    	b1.setLocked(false);
	    	boatManagement.addBoat(b1);
	    	b1.setId(null);
	    	b1.setNumberOfSeats(1);
	    	b1.setName("Sonderzeichen Boot šŠŸ§");
	    	b1.setLocked(true);
	    	b1.setCoxed(false);
	    	boatManagement.addBoat(b1);
	    	
	    	BoatDamage damage = new BoatDamage();
	    	damage.setBoat(b1);
	    	damage.setDamageDescription("Left side broken");
	    	boatManagement.addDamage(damage, member);

	    	BoatDamage damage2 = new BoatDamage();
	    	damage2.setBoat(b1);
	    	damage2.setFixed(true);
	    	damage2.setDamageDescription("Right side broken");
	    	boatManagement.addDamage(damage2, member);
		} catch (Exception e) { //is not important on fail
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
    }
    
    private Member createTestMember(){
    	Member testMember = null;
		try {
			testMember = new Member();
			testMember.setGivenname("Jan");
			testMember.setSurname("Trzeszkowski");
			testMember.setPassword("bla");
			testMember.setEmail("bla@bla.de");
			memberManagement.addMember(testMember);
		} catch (RowBuddyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return testMember;
	}
    
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
