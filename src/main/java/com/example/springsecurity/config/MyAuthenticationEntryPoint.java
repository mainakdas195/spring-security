package com.example.springsecurity.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class MyAuthenticationEntryPoint implements AuthenticationEntryPoint {
  @Override
  public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
      throws IOException, ServletException {
    // 401
    response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Authentication Failed");
  }

  @ExceptionHandler (value = {AccessDeniedException.class})
  public void commence(HttpServletRequest request, HttpServletResponse response,
      AccessDeniedException accessDeniedException) throws IOException {
    // 403
    //response.sendError(HttpServletResponse.SC_FORBIDDEN, "Authorization Failed : " + accessDeniedException.getMessage());
    JSONObject jsonObject = new JSONObject();
    try {
		response.addHeader("WWW-Authenticate", "Basic Realm - ");
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		response.setContentType("application/json");
		response.getWriter()
				.println(jsonObject.put("exception", "HTTP Status 401 - " + accessDeniedException.getMessage()));
	} catch (JSONException e) {
		e.printStackTrace();
	}
  }

  @ExceptionHandler (value = {Exception.class})
  public void commence(HttpServletRequest request, HttpServletResponse response,
      Exception exception) throws IOException {
     // 500
    response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Internal Server Error : " + exception.getMessage());
  }

}