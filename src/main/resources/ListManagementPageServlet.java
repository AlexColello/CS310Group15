
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import data.*;

@WebServlet("/listManagement")
public class ListManagementPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		String listType = request.getParameter("type");
		HttpSession session;
		if (request.getSession(false) == null) {
			session = request.getSession();
			
		}
		session = request.getSession();
		*/
		
		RequestDispatcher dispatch = request.getRequestDispatcher("/jsp/listManagement.jsp");
		dispatch.forward(request,  response);			
	}

}
