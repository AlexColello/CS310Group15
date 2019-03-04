
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import data.UserList;

@WebServlet("/restaurantDetails")
public class RestaurantDetailsPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session;
		if ((session = request.getSession(false)) == null) {
			session = request.getSession();
			UserList[] userLists = new UserList[3];
			for (int i = 0; i < 3; ++i) {
				userLists[i] = new UserList();
			}
			session.setAttribute("userLists", userLists);
		}
		
		RequestDispatcher dispatch = request.getRequestDispatcher("/jsp/restaurantDetails.jsp");
		dispatch.forward(request,  response);			
	}

}
