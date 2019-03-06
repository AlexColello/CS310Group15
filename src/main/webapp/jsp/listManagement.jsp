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
    String listID = (String) request.getAttribute("listName");
    
    if(listID == null){
    	listName = "error";
    }
    else{
    	listName = listID;
    }

    UserList lists = (UserList) request.getAttribute("listVal");
    ArrayList<Restaurant> rest = null;
    ArrayList<Recipe> rec = null;
    if(lists != null){
        rest = lists.getRestaurants();
        rec = lists.getRecipes();
    }
    request.setAttribute("restaurants", rest);
    request.setAttribute("recipes", rec);
    
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
             <div class="container">
             <form id="resForm" method="POST" action="/FeedMe/jsp/restaurantDetails.jsp" onclick="return restaurantRedirect(this)">
             	<input type="hidden" name="restaurantVal" value="<%= rest.get(i) %>">
             	<input type="hidden" name="arrNum" value="<%= i %>">
                <div class="itemLeft">
                  <div class="p1"><p><%=rest.get(i).getName()%></p></div>
                  <div class="p1"><p><%=rest.get(i).getRating()%></p></div>
                  <div class="p1"><p><%=rest.get(i).getDrivingTime()%></p></div>
                  <div class="p1"><p><%=rest.get(i).getAddress()%></p></div>
                </div>
                <div class="itemRight">
                  <div class="p2"><p>Price</p></div>
                </div>
              </form>
              </div>
              <div class="moveItem">
              	<form method="POST" action="/FeedMe/listManagement">
              		<input type="hidden" name="listName" value="<%=listName.toLowerCase().charAt(0)%>">
                	<input type="hidden" name="fromList" value="<%=listName.toLowerCase().charAt(0)%>">
                	<input type="hidden" name="recOrRest" value="rest">
                	<input type="hidden" name="arrNum" value="<%=i%>">
                	<% request.setAttribute("item", rest.get(i)); %>
                	<select name="opType">
                		<option value="f">Favorites</option>
                		<option value="t">To Explore</option>
                		<option value="d">Do Not Show</option>
                		<option value="r">Trash</option>
                	</select>
                	<button type="submit">Move</button>
                </form>
               </div>
            </li>
            <% } %>
            <% if(j < rec.size()){ %>
            <li class="item">
             <div class="container">
             <form id="recForm" action="/FeedMe/jsp/recipeDetails.jsp" method="POST" onclick="return recipeRedirect(this)">
		        <input type="hidden" name="arrNum" value="<%= j %>">
             	<!-- Holds the recipe highlights -->
                <div class="itemLeft">
                  <div class="p1"><p><%=rec.get(j).getName() %></p></div>
                  <div class="p1"><p><%=rec.get(j).getRating() %></p></div>
                  <div class="p1"><p><%=rec.get(j).getPrepTime() %></p></div>
                  <div class="p1"><p><%=rec.get(j).getCookTime() %></p></div>
                </div>
                <!-- Holds the recipe price -->
                <div class="itemRight">
                  <div class="p2"><p>Price</p></div>
                </div>
              </form>
                <!-- Holds the task bar to removing or moving item from list -->
              <div class="moveItem">
              	<form method="POST" action="/FeedMe/listManagement">
              		<input type="hidden" name="listName" value="<%=listName.toLowerCase().charAt(0)%>">
                	<input type="hidden" name="fromList" value="<%=listName.toLowerCase().charAt(0)%>">
                	<input type="hidden" name="recOrRest" value="rec">
                	<input type="hidden" name="arrNum" value="<%=j%>">
                	<% request.setAttribute("item", rec.get(j)); %>
                	<select name="opType">
                		<option value="f">Favorites</option>
                		<option value="t">To Explore</option>
                		<option value="d">Do Not Show</option>
                		<option value="r">Trash</option>
                	</select>
                	<button type="submit">Move</button>
                </form>
               </div>
              </div>              
            </li>
            <% } %>
          <% }} %>
        </ul>
      </div>
    </div>
    <div id="buttons">
		<form name="list" onsubmit="return manageList(this);">
      	<select id="listname" name="listName">
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
	  <form action ="/FeedMe/results">
      	<button id="backtoResults" onclick="javascript:location.href = this.value;">Return to Results</button>
      </form>
	
	</div>

    <!-- Homebrew JS -->
    <script>
function restaurantRedirect(form){
	form.submit();
}
function recipeRedirect(form){
	form.submit();
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