package de.rowbuddy.util;

import javax.ejb.EJBException;

/**
 *
 * @author Georg Fleischer
 */
public class EjbExceptionHandler {

    public EjbExceptionHandler() {
    }

    public void execute(Runnable runnable) throws Exception{
        try {
            runnable.run();
        } catch (EJBException ejbEx) {
            throw ejbEx.getCausedByException();
        }
    }
}
