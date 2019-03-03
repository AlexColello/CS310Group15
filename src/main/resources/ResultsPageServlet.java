
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import api.GoogleImageSearch;

/*
 * Receive search term from user via "q" parameter
 */
@WebServlet("/results")
public class ResultsPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String searchTerm = request.getParameter("q");
		
		GoogleImageSearch gis = new GoogleImageSearch();
		String[] imageUrlVec = gis.GetImagesFromGoogle(searchTerm);
		request.setAttribute("imageUrlVec", imageUrlVec);
		
		RequestDispatcher dispatch = request.getRequestDispatcher("/jsp/results.jsp");
		dispatch.forward(request,  response);			
	}
	
}