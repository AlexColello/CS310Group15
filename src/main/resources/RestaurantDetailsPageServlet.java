
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import data.Restaurant;
import data.UserList;

@WebServlet("/restaurantDetails")
public class RestaurantDetailsPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String addToListParam;
		if ((addToListParam = request.getParameter("add")) != null) {
			HttpSession session = request.getSession();
			// if restaurant results are not stored in session (meaning session has expired), 
			//  send the user back to the search page
			Restaurant[] restaurantResults = (Restaurant[]) session.getAttribute("restaurantResults");
			if (restaurantResults == null) {
				RequestDispatcher dispatch = request.getRequestDispatcher("/jsp/search.jsp");
				dispatch.forward(request,  response);
				return;
			}
			// TODO: How to know which restaurant to add to a list
			/*
			UserList[] userLists = (UserList[]) session.getAttribute("userLists");
			switch (addToListParam.charAt(0)) {
			case 'f':
				userLists[0].add(r);
				break;
			case 'd':
				userLists[1].add(r);
				break;
			case 't':
				userLists[2].add(r);
				break;
			}
			return;
			*/
		}
		
		RequestDispatcher dispatch = request.getRequestDispatcher("/jsp/restaurantDetails.jsp");
		dispatch.forward(request,  response);			
	}

}
