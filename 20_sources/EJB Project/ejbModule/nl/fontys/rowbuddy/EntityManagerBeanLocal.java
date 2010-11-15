package nl.fontys.rowbuddy;
import javax.ejb.Local;

@Local
public interface EntityManagerBeanLocal {

    void persist(Object entity);
	
}
