package de.rowbuddy.business;

import java.lang.reflect.Field;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

import de.rowbuddy.entities.Member;

public class SecuritySalamander {

	@AroundInvoke
	public Object checkLogin(InvocationContext ic) throws Exception{
		RowBuddyFacade rbf = (RowBuddyFacade) ic.getTarget();
		Field memberField = RowBuddyFacade.class.getDeclaredField("member");
		memberField.setAccessible(true);
		Member member = (Member) memberField.get(rbf);
		if(member == null){
			throw new NotLoggedInException("You need to be logged in to call this method.");
		} else {
			System.out.println("User '"+member.getEmail()+"' is authenticated.");
			return ic.proceed();
		}
		
		// TODO: Admin-Methoden; Rollen prüfen beim Member; RequiredRole Annotations
	}

}
