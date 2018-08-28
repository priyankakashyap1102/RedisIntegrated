package com.medicine.main.context;

import io.jsonwebtoken.Claims;

public class AuthContext {
	
	 private static ThreadLocal<Claims> claims = new ThreadLocal<>();

	  
	    public static void addClaims(Claims claims) {
	        AuthContext.claims.set(claims);
	    }

	   
	    public static Claims getClaims() {
	        return AuthContext.claims.get();
	    }

	   
	    public static void removeClaims() {
	        AuthContext.claims.remove();
	    }

}
