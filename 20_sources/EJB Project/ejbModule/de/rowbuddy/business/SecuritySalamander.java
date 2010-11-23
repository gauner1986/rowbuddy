package de.rowbuddy.business;

import java.lang.reflect.Field;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

import de.rowbuddy.entities.Member;
import de.rowbuddy.entities.Role;
import de.rowbuddy.exceptions.NotLoggedInException;

public class SecuritySalamander {
	
	@AroundInvoke
	public Object checkAuth(InvocationContext ic) throws Exception{
		RowBuddyFacade rbf = (RowBuddyFacade) ic.getTarget();
		Field memberField = RowBuddyFacade.class.getDeclaredField("member");
		memberField.setAccessible(true);
		Member member = (Member) memberField.get(rbf);
		if(member == null){
			throw new NotLoggedInException("You need to be logged in to call this method.");
		} else {
			// check roles
			AllowedRoles allowed = ic.getMethod().getAnnotation(AllowedRoles.class);
			if(allowed!=null){
				for(String role : allowed.values()){
					for(Role mrole : member.getRoles()){
						if(mrole.getName().equals(role)){
							System.out.println("User '"+member.getEmail()+"' is authenticated with role '"+role+"'");
							return ic.proceed();
						}
					}
				}
				throw new NotLoggedInException("You're not allowed to use this method"); // we did not return before, so we are not allowed to use the method.
			} else { // allow everyone by default
				System.out.println("User '"+member.getEmail()+"' is authenticated with role 'user' (default)");
				return ic.proceed();
			}
		}	
	}	

}
