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
  <body style="background-color:whitesmoke;">
    <div id="main" class="d-inline-flex p-1">
      <div class="p-2 ml-2">
      <!-- Restaurants and Recipes lists  -->
      	<h1><%=listName %> List</h1>
      		<%
   			int j = 0;
          	while(j < recipeArr.size()){
          	String colorStyle = "";
        	if (j%2 == 0){
          		colorStyle = "silver";
        	}
          	else{
          		colorStyle = "grey";
          	}
          	%>
          	<div class="col-12">
         			<div class="row no-gutters border rounded overflow-hidden flex-md-row mb-8 shadow-sm h-md-250 position-relative">
        			<div style="background-color:<%=colorStyle %>;"class="col p-4 d-flex flex-column position-static">
          			<div class="container">
  						<div class="row">
    					<div class="col-sm">
							<strong>Name:</strong> <br><p> <%= restaurantArr.get(j).getName() %> </p>
   						</div>

    					<div class="col-sm">
     	 						<strong>Stars:</strong> <br> <p> <%=restaurantArr.get(j).getRating() %> </p>
    					</div>
    					<div class="col-sm">
     	 						
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
      							<strong>Minutes:</strong> <br> <p><%=restaurantArr.get(j).getDrivingTime() %> </p>
   							</div>

    					<div class="col-sm">
     	 						<strong>Address: </strong><br> <p><%=restaurantArr.get(j).getAddress() %></p>
    					</div>
    					<div class="col-sm text-right">
    							<%
        							String restaurantPrice = "";
        							int price = (int)restaurantArr.get(j).getPrice();
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
     	 						<strong>Price: <%=restaurantPrice%></strong>
    					</div>
  						</div>
					</div>

          			<a href="/FeedMe/restaurantDetails?arrNum=<%=j%>" class="stretched-link"></a>
        			</div>
      				</div>
      				<form style="display:flex;flex-direction:column;justify-content:center;" method="POST" action="/FeedMe/listManagement">
             			<input type="hidden" name="listName" value="<%=listName.toLowerCase().charAt(0)%>">
	 	            	<input type="hidden" name="fromList" value="<%=listName.toLowerCase().charAt(0)%>">
    	            	<input type="hidden" name="recOrRest" value="rest">
        	        	<input type="hidden" name="arrNum" value="<%=j%>">
            	    	<% request.setAttribute("item", restaurantArr.get(j)); %>
                		<select class="form-control" name="opType">
	                		<option value="f">Favorites</option>
    	            		<option value="t">To Explore</option>
        	        		<option value="d">Do Not Show</option>
            	    		<option value="r">Trash</option>
                		</select>
	                	<button class="form-control" type="submit">Move</button>
					</form>
    	</div>
         <% j++;} %>

    	<!-- Recipes -->
    		<%
   			int k = 0;
          	while(k < recipeArr.size()){
          	String colorStyle = "";
        	if (k%2 == 0 && restaurantArr.size() % 2 == 0){
          		colorStyle = "grey";
        	}
          	else{
          		colorStyle = "silver";
          	}
          	if(k%2 == 0 && restaurantArr.size()%2 != 0){
          		colorStyle = "silver";
          	}
          	else{
          		colorStyle = "grey";	
          	}
          	%>
    		<div class="col-12">
         			<div class="row no-gutters border rounded overflow-hidden flex-md-row mb-8 shadow-sm h-md-250 position-relative">
        			<div style="background-color:<%=colorStyle %>;" class="col p-4 d-flex flex-column position-static">
          			<div class="container">
  						<div class="row">
    						<div class="col-sm">
      							<strong>Name:</strong> <br><p><%=recipeArr.get(k).getName() %></p>
   							</div>

    					<div class="col-sm">
    						<% String recipeRating = String.format("%.1f",recipeArr.get(k).getRating()); %>
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
    								double cookTime = recipeArr.get(k).getCookTime();
    								String renderCookTime = "";
    								if (cookTime < 0){
    									renderCookTime = "Not Available";
    								}
    								else{
    									renderCookTime = Double.toString(cookTime);
    								}
    								
    								double prepTime = recipeArr.get(k).getPrepTime();
    								String renderPrepTime = "";
    								if (prepTime < 0){
    									renderPrepTime = "Not Available";
    								}
    								else{
    									renderPrepTime = Double.toString(prepTime);
    								}			
    							%>
      							<strong>Cook Time:</strong> <br> <p><%=renderCookTime %></p>
   							</div>

    					<div class="col-sm">
     	 						<strong>Prep Time: </strong><br> <p><%=renderPrepTime%></p>
    					</div>
    				
  						</div>
					</div>



          			<a href="/FeedMe/recipeDetails?arrNum=<%=k%>" class="stretched-link"></a>
        			</div>
        			<div class="col-auto d-none d-lg-block">
          			</div>
      				</div>
      				<form style="display:flex;flex-direction:column;justify-content:center;" method="POST" action="/FeedMe/listManagement">
             			<input type="hidden" name="listName" value="<%=listName.toLowerCase().charAt(0)%>">
	 	            	<input type="hidden" name="fromList" value="<%=listName.toLowerCase().charAt(0)%>">
    	            	<input type="hidden" name="recOrRest" value="rec">
        	        	<input type="hidden" name="arrNum" value="<%=k%>">
            	    	<% request.setAttribute("item", recipeArr.get(k)); %>
                		<select class="form-control" name="opType">
	                		<option value="f">Favorites</option>
    	            		<option value="t">To Explore</option>
        	        		<option value="d">Do Not Show</option>
            	    		<option value="r">Trash</option>
                		</select>
	                	<button class="form-control" type="submit">Move</button>
					</form>
    	</div>
    	
        <%k++; }%>
        </div>

   	<div id="buttons" class="align-middle p-1">
		<form name="list" onsubmit="return manageList(this);">
      	<select id="listname" name="listName" class="dropDownBar">
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
  </style>
</html>