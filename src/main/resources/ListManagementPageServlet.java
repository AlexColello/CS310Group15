
import java.io.IOException;
import java.util.ArrayList;

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
		System.out.println("ListType: " + listType.charAt(0));
		
		String op = request.getParameter("opType");
//		Check if user wants to move or remove an item from a list
		if (op != null) {
//			Get Variables to help move the item
			String recOrRest = request.getParameter("recOrRest");
			String fromList = request.getParameter("fromList");
			int listNum = 0;
//			Map the letter name of list to an int
			if(fromList.equals("f")) {
				listNum = 0;
			}
			else if(fromList.equals("d")) {
				listNum = 1;
			}
			else {
				listNum = 2;
			}
//			Get position of item in question from current list
			int arrNum = Integer.parseInt(request.getParameter("arrNum"));
//			Get either recipe lists or restaurant lists from the Userlists in question
			UserList arr1 = userLists[listNum];
			System.out.println("arrNum: " + arrNum);
			System.out.println("recOrRest: " + recOrRest);
			System.out.println("fromList: " + fromList);
			System.out.println("OP: " + op);
			System.out.println("Arr1 size: " + arr1.getRecipes().size());
//			Check if we're moving the item to the Favorites list
			if(op.equals("f")) {
				if(recOrRest.equals("rec")) { // Check if it is a recipe item
					if(!userLists[0].contains(arr1.getRecipes().get(arrNum))) { // Check if the list already has the item
						userLists[0].add(arr1.getRecipes().get(arrNum)); // Add the item to the new list
						arr1.remove(arr1.getRecipes().get(arrNum)); // Remove the item from the old list
					}
				}
				else { // Restaurant Item
					if(!userLists[0].contains(arr1.getRestaurants().get(arrNum))) {
						userLists[0].add(arr1.getRestaurants().get(arrNum));
						arr1.remove(arr1.getRestaurants().get(arrNum));
					}
				}
			}
			else if(op.equals("t")) { // Check if we're moving item to To Explore list
				if(recOrRest.equals("rec")) {
					if(!userLists[2].contains(arr1.getRecipes().get(arrNum))) {
						userLists[2].add(arr1.getRecipes().get(arrNum));
						arr1.remove(arr1.getRecipes().get(arrNum));
					}
				}
				else {
					if(!userLists[2].contains(arr1.getRestaurants().get(arrNum))) {
						userLists[2].add(arr1.getRestaurants().get(arrNum));
						arr1.remove(arr1.getRestaurants().get(arrNum));
					}
				}
			}
			else if(op.equals("d")) {
				if(recOrRest.equals("rec")) {
					if(!userLists[1].contains(arr1.getRecipes().get(arrNum))) {
						userLists[1].add(arr1.getRecipes().get(arrNum));
						arr1.remove(arr1.getRecipes().get(arrNum));
					}
				}
				else {
					if(!userLists[1].contains(arr1.getRestaurants().get(arrNum))) {
						userLists[1].add(arr1.getRestaurants().get(arrNum));
						arr1.remove(arr1.getRestaurants().get(arrNum));
					}
				}
			}
			else {
				if(recOrRest.equals("rec")) {
					arr1.remove(arr1.getRecipes().get(arrNum));
				}
				else {
					arr1.remove(arr1.getRestaurants().get(arrNum));
				}	
			}
			
		}
		// Pass list to display to jsp
		if (listType != null) {
			switch (listType.charAt(0)) {
			case 'f':
				request.setAttribute("listVal", userLists[0]);
				request.setAttribute("listName", "Favorites");
				System.out.println("favorites");
				break;
			case 'd':
				request.setAttribute("listVal", userLists[1]);
				request.setAttribute("listName", "Don't Show");
				System.out.println("Do Not Show");
				break;
			case 't':
				request.setAttribute("listVal", userLists[2]);
				request.setAttribute("listName", "To Explore");
				System.out.println("To Explore");
				break;
			}			
		}
		
		RequestDispatcher dispatch = request.getRequestDispatcher("/jsp/listManagement.jsp");
		dispatch.forward(request,  response);			
	}

}
