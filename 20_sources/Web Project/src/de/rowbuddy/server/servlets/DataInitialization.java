package de.rowbuddy.server.servlets;

import java.io.IOException;

import javax.ejb.CreateException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.rowbuddy.business.BoatManagement;
import de.rowbuddy.entities.Boat;
import de.rowbuddy.exceptions.RowBuddyException;

/**
 * Servlet implementation class DataInitialization
 */
@WebServlet("/DataInitialization")
public class DataInitialization extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB
	private BoatManagement boatManagement;
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
		} catch (Exception e) { //is not important on fail
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
