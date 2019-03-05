
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
		HttpSession session = request.getSession();
		UserList[] userLists = (UserList[]) session.getAttribute("userLists");
		if (userLists == null) {
			RequestDispatcher dispatch = request.getRequestDispatcher("/jsp/search.jsp");
			dispatch.forward(request,  response);
		}
		String listType = request.getParameter("listName");
		System.out.println(listType);
		
		String op = request.getParameter("opType");
		if (op != null) {
			int arrNum;
			arrNum = Integer.parseInt(request.getParameter("arrNum"));
			if (op.equals("move")) {
				
			}
			else if (op.equals("remove")) {
				
			}
		}
		// Pass list to display to jsp
		switch (listType.charAt(0)) {
		case 'f':
			request.setAttribute("listVal", userLists[0]);
			break;
		case 'd':
			request.setAttribute("listVal", userLists[1]);
			break;
		case 't':
			request.setAttribute("listVal", userLists[2]);
			break;
		case 'n':
			RequestDispatcher dispatch = request.getRequestDispatcher("/jsp/listManagement.jsp");
			dispatch.forward(request,  response);	
			break;
		}
		
		RequestDispatcher dispatch = request.getRequestDispatcher("/jsp/listManagement.jsp");
		dispatch.forward(request,  response);			
	}

}
