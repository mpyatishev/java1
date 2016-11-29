package servlets;

import dbService.DBService;
import dbService.DBException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class SignUpServlet extends HttpServlet {
  private final DBService dbService;

  public SignUpServlet(DBService dbService) {
	this.dbService = dbService;
  }

  public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	String login = request.getParameter("login");
	String pass = request.getParameter("password");

	System.out.println(login + " " + pass);

	if (login == null || pass == null) {
	  response.setContentType("text/html;charset=utf-8");
	  response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
	  return;
	}

	try {
		dbService.addUser(login, pass);
	} catch (DBException e) {
	  response.setContentType("text/html;charset=utf-8");
	  response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
	  System.out.println(e.toString());
	  return;
	}

	response.setContentType("text/html;charset=utf-8");
	response.setStatus(HttpServletResponse.SC_OK);
  }
}
