package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dbService.DBService;
import dbService.DBException;
import dbService.dataSets.UsersDataSet;


public class SignInServlet extends HttpServlet {
  private final DBService dbService;

  public SignInServlet(DBService dbService) {
	this.dbService = dbService;
  }

  public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	String login = request.getParameter("login");
	String pass = request.getParameter("password");

	if (login == null || pass == null) {
	  response.setContentType("text/html;charset=utf-8");
	  response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
	  return;
	}

	UsersDataSet user;
	try {
		user = dbService.getUser(login, pass);
	} catch (DBException e) {
	  response.setContentType("text/html;charset=utf-8");
	  response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
	  return;
	}

	if (user == null) {
	  response.setContentType("text/html;charset=utf-8");
	  response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
	  return;
	}

	response.setContentType("text/html;charset=utf-8");
	response.getWriter().println("Authorized: " + user.getName());
	response.setStatus(HttpServletResponse.SC_OK);
  }
}
