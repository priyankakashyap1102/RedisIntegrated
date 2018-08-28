/*package com.medicine.main.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.medicine.main.context.AuthContext;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

public class AuthInterceptor extends HandlerInterceptorAdapter {
	static final String SECRET = "ThisIsASecret";
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object object) throws Exception {
		String authHeader = request.getHeader("authorization");

		if (!"OPTIONS".equals(request.getMethod())) {
			if (authHeader == null || !authHeader.startsWith("Bearer ")) {
				System.out.println("Helloooo");
				throw new Exception();
			}

			String token = authHeader.substring(7);

			try {
				Claims claims = Jwts.parser()
						.setSigningKey(SECRET)
						.parseClaimsJws(token)
						.getBody();
				AuthContext.addClaims(claims);
			} catch (Exception e) {
				LOG.error("JWT parse error", e);
				System.out.println("Error"+e);
				throw new Exception(e);
			}
		}

		return true;
	}
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
			Object object, Exception ex) throws Exception {
		AuthContext.removeClaims();
	}

}
*/