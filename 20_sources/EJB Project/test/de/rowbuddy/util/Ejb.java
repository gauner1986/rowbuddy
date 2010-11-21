package de.rowbuddy.util;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class Ejb {

    public static <T, I> I lookUp(Class<T> classType, Class<I> interfaceType) {

        StringBuilder lookUpName = new StringBuilder();
        lookUpName.append("java:global/classes/");
        lookUpName.append(classType.getSimpleName());
        lookUpName.append("!");
        lookUpName.append(interfaceType.getName());

        try {
            Context context = new InitialContext();
            return (I) context.lookup(lookUpName.toString());
        } catch (NamingException ex) {
            throw new RuntimeException(ex);
        }
    }

}
