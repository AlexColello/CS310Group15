
import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import api.GoogleImageSearch;
import data.Recipe;
import data.Restaurant;
import data.UserList;

/*
 * Receive search term from user via "q" parameter
 */
@WebServlet("/results")
public class ResultsPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		HttpSession session;
		if ((session = request.getSession(false)) == null) {
			session = request.getSession();
			UserList[] userLists = new UserList[3];
			for (int i = 0; i < 3; ++i) {
				userLists[i] = new UserList();
			}
			session.setAttribute("userLists", userLists);
		}
		UserList[] userLists = (UserList[]) session.getAttribute("userLists");
		UserList favoriteList = userLists[0];
		ArrayList<Restaurant> favoriteRestaurants = favoriteList.getRestaurants();
		ArrayList<Recipe> favoriteRecipes = favoriteList.getRecipes();
		UserList doNotShowList = userLists[1];
		ArrayList<Restaurant> doNotShowRestaurants = doNotShowList.getRestaurants();
		ArrayList<Recipe> doNotShowRecipes = doNotShowList.getRecipes();
		UserList ToExploreList = userLists[2];
		
		*/
		// input validation should be done on front end (empty string, non-integer for resultCount, etc.)
		String searchTerm = request.getParameter("q");
		/*
		int resultCount = Integer.parseInt(request.getParameter("n"));
		
		// Sort restaurants according to user lists
		Vector<Restaurant> restaurants = new Vector<Restaurant>(); // = GetRestaurantsFromYelp(searchTerm, resultCount + doNotShowRestaurants.size());
		Restaurant currRestaurant;
		int insertIndex = 0;
		for (int i = 0; i < restaurants.size(); ++i) {
			currRestaurant = restaurants.get(i);
			if (favoriteList.contains(currRestaurant)) {
				restaurants.add(insertIndex, currRestaurant);
				restaurants.remove(i+1);
				++insertIndex;
			}
		}
		for (int i = insertIndex; i < restaurants.size(); ++i) {
			currRestaurant = restaurants.get(i);
			if (doNotShowList.contains(currRestaurant)) {
				restaurants.remove(i);
				--i;
			}
		}
		
		// Sort recipes according to user lists
		Vector<Recipe> recipes = new Vector<Recipe>(); // = GetRecipes(searchTerm, resultCount + doNotShowRecipes.size());
		Recipe currRecipe;
		insertIndex = 0;
		for (int i = 0; i < recipes.size(); ++i) {
			currRecipe = recipes.get(i);
			if (favoriteList.contains(currRecipe)) {
				recipes.add(insertIndex, currRecipe);
				recipes.remove(i+1);
				++insertIndex;
			}
		}
		for (int i = insertIndex; i < recipes.size(); ++i) {
			currRecipe = recipes.get(i);
			if (doNotShowList.contains(currRecipe)) {
				recipes.remove(i);
				--i;
			}
		}
		
		// vector size should be resultCount (discard extra data)
		restaurants.setSize(resultCount);
		recipes.setSize(resultCount);
		// Make vectors into arrays (to pass to jsp)
		// pass to jsp as attributes
		Restaurant[] restaurantArr = new Restaurant[resultCount];
		Recipe[] recipeArr = new Recipe[resultCount];
		restaurants.toArray(restaurantArr);
		recipes.toArray(recipeArr);
		request.setAttribute("restaurantArr", restaurantArr);
		request.setAttribute("recipeArr", recipeArr);
		*/
		// Google Image Search to get collages
		// array of image URLs passed to jsp as "imageUrlVec"
		GoogleImageSearch gis = new GoogleImageSearch();
		String[] imageUrlVec = gis.GetImagesFromGoogle(searchTerm);
		request.setAttribute("imageUrlVec", imageUrlVec);
		request.setAttribute("searchTerm", searchTerm);
		
		RequestDispatcher dispatch = request.getRequestDispatcher("/jsp/results.jsp");
		dispatch.forward(request,  response);			
	}
}