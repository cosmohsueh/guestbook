package com.example.guestbook;

import java.io.IOException;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

public class GuestbookServlet extends HttpServlet{

	private static final long serialVersionUID = 4835003121929650858L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		if(req.getParameter("testing") == null){
			resp.setContentType("text/plain");
			resp.getWriter().println("Hello, this is a testing servlet. \n\n");
			Properties p = System.getProperties();
			p.list(resp.getWriter());
		}else{
			UserService userService = UserServiceFactory.getUserService();
			User currentUser = userService.getCurrentUser();
			if(currentUser != null){
				resp.setContentType("text/plain");
		        resp.getWriter().println("Hello, " + currentUser.getNickname());
			}else{
				resp.sendRedirect(userService.createLoginURL(req.getRequestURI()));
			}
		}
	}
	
	

}
