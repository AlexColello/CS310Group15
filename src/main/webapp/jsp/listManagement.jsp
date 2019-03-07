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
    request.getSession().setAttribute("resultsOrList", "list");
    String listName = "";
    String listID = (String) request.getAttribute("listName");
    
    if(listID == null){
    	listName = "error";
    }
    else{
    	listName = listID;
    }

    UserList lists = (UserList) request.getAttribute("listVal");
    ArrayList<Restaurant> restaurantArr = null;
    ArrayList<Recipe> recipeArr = null;
    if(lists != null){
    	restaurantArr = lists.getRestaurants();
        recipeArr = lists.getRecipes();
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
          <% if(recipeArr != null && restaurantArr != null){ %>
          <% for(int i = 0, j = 0; i < restaurantArr.size() || j < recipeArr.size(); ++i, ++j){ %>
            <% if(i < restaurantArr.size()){ %>
            <div id="Restaurant<%=i%>" class="row no-gutters border rounded overflow-hidden flex-sm-row shadow-sm h-sm-250 position-relative w-65 clearfix">
        		<div class="col d-flex flex-column position-static">
	          		<div class="container moveItem">
    	    			<a href="/FeedMe/restaurantDetails?arrNum=<%=i%>">
  						<div class="row">
    						<div class="col-sm">
      							<strong>Name:</strong> <br><p><%=restaurantArr.get(i).getName() %></p>
   							</div>
	    				<div class="col-sm">
     		 					<strong>Stars:</strong> <br> <p> <%=restaurantArr.get(i).getRating() %> </p>
    					</div>
  						</div>
  						<div class="row">
   							<div class="col-sm">
	   						</div>
	    					<div class="col-sm">
	    					</div>
  						</div>
  						<div class="row">
    						<div class="col-sm">
      							<strong>Minutes:</strong> <br> <p><%=restaurantArr.get(i).getDrivingTime() %> </p>
   							</div>
	    					<div class="col-sm">
    	 	 					<strong>Address: </strong><br> <p><%=restaurantArr.get(i).getAddress() %></p>
    						</div>
    				
  						</div>
  						
					</div>
    	    	</div>
    	    		<div class="container moveItem">
						<div class="row p-0">
   							<div class="col-sm">
	   						</div>
	   						<%
        					String restaurantPrice = "";
        					int price = (int)restaurantArr.get(i).getPrice();
        					if (price == 1){
        						restaurantPrice = "$";
        					}
        					else if (price == 2){
        						restaurantPrice = "$$";
        					}
        					else{
        						restaurantPrice = "$$$";
        					}
        					%>
	    					<div class="col-sm"><p class="p-0 w-10"><%=restaurantPrice %></p></div>
	    					<div class="col-sm">
	    					</div>
  						</div>
					</div>
        		</a>
              	<form style="display:flex;flex-direction:column;justify-content:center;" method="POST" action="/FeedMe/listManagement">
             		<input type="hidden" name="listName" value="<%=listName.toLowerCase().charAt(0)%>">
	 	            <input type="hidden" name="fromList" value="<%=listName.toLowerCase().charAt(0)%>">
    	            <input type="hidden" name="recOrRest" value="rest">
        	        <input type="hidden" name="arrNum" value="<%=i%>">
            	    <% request.setAttribute("item", restaurantArr.get(i)); %>
                	<select class="form-control" name="opType" >
	                	<option value="f">Favorites</option>
    	            	<option value="t">To Explore</option>
        	        	<option value="d">Do Not Show</option>
            	    	<option value="r">Trash</option>
                	</select>
	                <button class="form-control" type="submit">Move</button>
        			<br/>
        			<br/>
				</form>
			</div>
            <% } %>
            <% if(j < recipeArr.size()){ %>
            <div id="Recipe<%=j%>" style="height-max:200px;">
            <div class="row no-gutters border rounded overflow-hidden flex-sm-row shadow-sm h-sm-250 position-relative w-65 clearfix bg-dark" >
        		<div class="col d-flex flex-column position-static">
	          		<div class="container moveItem">
    	    			<a href="/FeedMe/recipeDetails?arrNum=<%=j%>">
  						<div class="row bg-dark">
    						<div class="col-sm bg-dark">
      							<strong>Name:</strong> <br><p><%=recipeArr.get(j).getName() %></p>
   							</div>

	    				<div class="col-sm">
    						<% String recipeRating = String.format("%.1f", recipeArr.get(j).getRating()); %>
     		 					<strong>Stars:</strong> <br> <p> <%=recipeRating %> </p>
    					</div>
  						</div>
  						<div class="row">
   							<div class="col-sm">
	   						</div>
	    					<div class="col-sm">
	    					</div>
  						</div>
  						<div class="row">
    						<div class="col-sm">
    							<%
    								double cookTime = recipeArr.get(j).getCookTime();
    								String renderCookTime = "";
   									if (cookTime < 0){
   										renderCookTime = "Not Available";
    								}
    								else{
    									renderCookTime = Double.toString(cookTime);
	    							}
    						
    							%>
      							<strong>Cook Time:</strong> <br> <p><%=renderCookTime %></p>
   							</div>
	    					<div class="col-sm">
    	 	 					<strong>Prep Time: </strong><br> <p><%=recipeArr.get(j).getPrepTime() %></p>
    						</div>
    				
  						</div>
  						
					</div>
    	    	</div>
    	    		<div class="container moveItem">
						<div class="row p-0">
   							<div class="col-sm">
	   						</div>
	    					<div class="col-sm"><p class="p-0 w-10">No Price</p></div>
	    					<div class="col-sm">
	    					</div>
  						</div>
					</div>
        		</a>
              	<form style="display:flex;flex-direction:column;justify-content:center;" method="POST" action="/FeedMe/listManagement">
             		<input type="hidden" name="listName" value="<%=listName.toLowerCase().charAt(0)%>">
	 	            <input type="hidden" name="fromList" value="<%=listName.toLowerCase().charAt(0)%>">
    	            <input type="hidden" name="recOrRest" value="rec">
        	        <input type="hidden" name="arrNum" value="<%=j%>">
            	    <% request.setAttribute("item", recipeArr.get(j)); %>
                	<select class="form-control" name="opType">
	                	<option value="f">Favorites</option>
    	            	<option value="t">To Explore</option>
        	        	<option value="d">Do Not Show</option>
            	    	<option value="r">Trash</option>
                	</select>
	                <button class="form-control" type="submit">Move</button>
				</form>
			</div>
			</div>
            <% } %>
          <% }} %>
        </ul>
      </div>
    </div>
    <div id="buttons" class="align-middle">
		<form name="list" onsubmit="return manageList(this);">
      	<select id="listname" name="listName">
      		<option disabled selected value>Select an option</option>
       		<option value ="f" >Favorites</option>
        	<option value ="t">To Explore</option>
        	<option value ="d">Do Not Show</option>
      	</select>
     	<!-- Button to add item to selected list, doesn't do anything if choice is empty -->
     	<button class="Button" id="addToList">Manage List</button> <br>

       </form>

      <form action ="/FeedMe/jsp/search.jsp">
      	<button class="Button" id="backtoResults" onclick="javascript:location.href = this.value;">Return to Search</button>
      </form>
	  <form action ="/FeedMe/results">
      	<button class="Button" id="backtoResults" onclick="javascript:location.href = this.value;">Return to Results</button>
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
	var userInput = document.getElementById('listname').value;
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