package com.example.guestbook;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.googlecode.objectify.ObjectifyService;

public class SignGuestbookServlet extends HttpServlet{

	private static final long serialVersionUID = 2104102699220957246L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		Greeting greeting;
		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();
		
		String guestbookName = req.getParameter("guestbookName");
		String content = req.getParameter("content");
		if(user != null){
			greeting = new Greeting(guestbookName, content, user.getUserId(), user.getEmail());
		}else{
			greeting = new Greeting(guestbookName, content);
		}
		ObjectifyService.ofy().save().entity(greeting).now();
		resp.sendRedirect("/guestbook.jsp?guestbookName=" + guestbookName);
	}

}
