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
<<<<<<< HEAD
    	UserList recipeVal = (UserList) request.getAttribute("listVal");
=======
	UserList recipeVal = (UserList) request.getAttribute("listVal");
    String listName = (String) request.getAttribute("listName");
>>>>>>> d86e38f4806d40b7df00a329d1323a8c02544bfc
    %>
    <!-- Title -->
    <title>Recipe Details</title>
  </head>
	
    <!-- Title -->
    <title>List Management</title>
  </head>
  <body>
  
  <div class="container">
		<!-- Row for collage and buttons -->
		<div class="row mb-2">
			 <div class="col-md-6">
        <h1 id="listTitle"><%= listName %> List</h1>
        <ul id="listItems">
          
          		<%

          		for(int i = 0; i < 5; i++){
          		%>
         			<div class="row no-gutters border rounded overflow-hidden flex-md-row mb-4 shadow-sm h-md-250 position-relative">
        			<div class="col p-4 d-flex flex-column position-static">
          			<div class="container">
  						<div class="row">
    						<div class="col-sm">

      							<strong>Name:</strong> <br><p>name </p>
   							</div>

    					<div class="col-sm">
     	 						<strong>Stars:</strong> <br> <p> stars</p>
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
      							<strong>Minutes:</strong> <br> <p>minutes </p>
   							</div>

    					<div class="col-sm">
     	 						<strong>Address: </strong><br> <p>address</p>
    					</div>
  						</div>
					</div>


					
          			<a href="/FeedMe/restaurantDetails?arrNum=<%=i%>" class="stretched-link"></a>
        			</div>
        			<div class="col-auto d-none d-lg-block">
        		
        		
          			<svg class="bd-placeholder-img" width="100" height="280" xmlns="http://www.w3.org/2000/svg" preserveAspectRatio="xMidYMid slice" focusable="false" role="img" aria-label="Placeholder: $$$"><title>$$</title><rect width="100%" height="100%" fill="#55595c"></rect><text x="20%" y="50%" fill="#eceeef" dy=".3em">Price </text></svg>
        			</div>
      				</div>
          		<% } %>
       		 </ul>

    	</div>

			<div class="col-md-4 order-md-2 mb-4">
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

					 <form action="/FeedMe/results" method="POST">
        				<button id="backToResults" class="bttn">Back To Results</button>
      				</form>
      				<form action ="/FeedMe/jsp/search.jsp">
      					<button id="backtoResults" onclick="javascript:location.href = this.value;">Return to Search</button>
      				</form>

		 		</div>
		 		
		 	</div>

    <!-- Homebrew JS -->
    <script>

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

  </style>
</html>