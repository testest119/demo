package adan.login;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import adan.data.UserDB;

public class Login extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = req.getParameter("action");
		String url = "/index.html";
		if(action == null)
			action = "join";
		if(action.equals("join")){
			url = "/index.html";
		}else if(action.equals("add")){
			String acc = req.getParameter("acc");
			String pass = req.getParameter("pass");
//			System.out.println("DEBUGGGGGGGGG   acc="+acc);
//			System.out.println("DEBUGGGGGGGGG   pass="+pass);
			
			String valid = UserDB.login(acc, pass);
			if(valid.equals("Y"))
				url = "/WEB-INF/page1.html";
		}
		getServletContext()
        .getRequestDispatcher(url)
        .forward(req, resp);

		
	}
	
	

}
