<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">    
    <meta charset="ISO-8859-1">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <!-- Homebrew CSS FeedMe/css/buttons.css ${pageContext.request.contextPath}/css/Login.css" -->
    <link href="/css/buttons.css" rel="stylesheet" type="text/css">
    <link href="/css/details.css" rel="stylesheet" type="text/css">
    <link href="/css/list.css" rel="stylesheet" type="text/css">
    <link href="/css/listManagement.css" rel="stylesheet" type="text/css">
	<%@page import="java.util.*" %>
	<%@page import="data.*"%>
  <% 
    String listName = "";
    Object listID = request.getAttribute("listID");
    if(listID == null){
    	listName = "error";
    }
    else{
    	if(listID.toString().charAt(0) == 'f'){
    		listName = "Favorites";
    	}else if(listID.toString().charAt(0) == 'd'){
    		listName = "Do Not Show";
    	}else if(listID.toString().charAt(0) == 't'){
    		listName = "To Explore";
    	}else{
    		listName = "Error";
    	}
    }
    UserList lists = (UserList) request.getAttribute("listVal");
    ArrayList<Restaurant> rest = null;
    ArrayList<Recipe> rec = null;
    if(lists != null){
        rest = lists.getRestaurants();
        rec = lists.getRecipes();
    }
  %>

    <!-- Title -->
    <title>List Management</title>
  </head>
  <body>
    <div id="main">
      <!-- Holds the list name, and items -->
      <div id="listDetails">
        <h1 id="listTitle"><%= listName %></h1>
        <ul id="listItems">
          <% if(rec != null && rest != null){ %>
          <% for(int i = 0, j = 0; i < rest.size() || j < rec.size(); ++i, ++j){ %>
            <% if(i < rest.size()){ %>
            <li class="item">
             <form id="restItem<%=i%>" method="post" onsubmit="return restaurantRedirect(this)">
             <input type="hidden" name="listID" value="<%= listID %>">
             <input type="hidden" name="restaurantID" value="<%= i %>">
             <div class="container" onclick="document.getElementByID(restItem<%=i%>).submit()">
                <div class="itemLeft">
                  <div class="p1"><p><%=rest.get(i).getName()%></p></div>
                  <div class="p1"><p><%=rest.get(i).getRating()%></p></div>
                  <div class="p1"><p><%=rest.get(i).getDrivingTime()%></p></div>
                  <div class="p1"><p><%=rest.get(i).getAddress()%></p></div>
                </div>
                <div class="itemRight">
                  <div class="p2"><p>Price</p></div>
                </div>
              </div>
              </form>
            </li>
            <% } %>
            <% if(j < rec.size()){ %>
            <li class="item">
             <form id="recItem<%=j%>" method="post" onsubmit="return recipeRedirect(this)">
             <input type="hidden" name="listID" value="<%= listID %>">
             <input type="hidden" name="recipeID" value="<%= j %>">
             <div class="container" onclick="document.getElementByID(recItem<%=j%>).submit()">
                <div class="itemLeft">
                  <div class="p1"><p><%=rec.get(j).getName() %></p></div>
                  <div class="p1"><p><%=rec.get(j).getRating() %></p></div>
                  <div class="p1"><p><%=rec.get(j).getPrepTime() %></p></div>
                  <div class="p1"><p><%=rec.get(j).getCookTime() %></p></div>
                </div>
                <div class="itemRight">
                  <div class="p2"><p>Price</p></div>
                </div>
              </div>
              </form>
            </li>
            <% } %>
          <% }} %>
        </ul>
      </div>
    </div>
    <div id="buttons">
		 			<form name="list" onsubmit="return manageList(this);">
      					<select id="listName" name="listName">
      					<option disabled selected value> -- select an option -- </option>
       				    <option value ="f" >Favorites</option>
        				<option value ="t">To Explore</option>
        				<option value ="d">Do Not Show</option>
      					</select> <br>
      					<!-- Button to add item to selected list, doesn't do anything if choice is empty -->
     					<button id="addToList">Manage Lists</button> <br>

      				</form>


      				<form action ="/FeedMe/jsp/search.jsp">
      					<button id="backtoResults" onclick="javascript:location.href = this.value;">Return to Search</button>
      				</form>

		 		</div>

    <!-- Homebrew JS -->
    <script>
function restaurantRedirect(form){
	
}
function recipeRedirect(form){
	
}
function manageList(form){
	var userInput = document.getElementById('listName').value;
	console.log(userInput);
	if (userInput == null || userInput.length == 0){
		return false;	
	}
	else{
		
		form.action = "/FeedMe/listManagement";
	}
	

} 
</script>
  </body>
  <style>
    <%@ include file="/css/buttons.css"%>
    <%@ include file="/css/list.css"%>
    <%@ include file="/css/listManagement.css"%>
    <%@ include file="/css/details.css"%>
  </style>
</html>