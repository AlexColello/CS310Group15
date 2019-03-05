<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<%@page import="java.util.*" %>
	<%@page import="data.*"%>
	<%

	String[] imageUrlVec = (String[])request.getAttribute("imageUrlVec");
	String searchTerm =  (String) request.getAttribute("searchTerm");
	Integer resultCount =  (Integer) request.getAttribute("resultCount");
	Restaurant[] restaurantArr = (Restaurant[]) request.getAttribute("restaurantArr");
	Recipe[] recipeArr = (Recipe[]) request.getAttribute("recipeArr");
	%>
	  <!-- Bootstrap CSS  -->
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
	  <!-- Homebrew CSS  -->
    <link href="../css/buttons.css" rel="stylesheet" type="text/css">
    <link href="../css/details.css" rel="stylesheet" type="text/css">
    <link href="../css/list.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet"  href="/css/results.css"  type="text/css">

</head>

<body>
	<div class="container">
		<!-- Row for collage and buttons -->
		<div class = "row">
			<div class="col-md-4 order-md-2 mb-4">
		 		<div id="buttons">
		 			<form name="list" action="/FeedMe/listManagement" method="POST">
      					<select name="listName">
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
		 	</div>

		 	<div class="col-md-8 order-md-1">
		 	<% for (int i = 0; i < 10; i++) {
		 		Random rand = new Random();
		 		int angle = rand.nextInt(90) -45;
		 	%>
			<img style ="transform: rotate(<%=angle%>deg);" src="<%=imageUrlVec[i] %>" height="100" width="100">
		 	<% } %>
		 	<%--

					<% for (int i = 0; i < imageUrlVec.length; ++i) { %>
					<img src="<%=imageUrlVec[i] %>" height="100" width="100">
					<% } %>
		 	--%>

			</div>
		</div>

		<!-- Search For xx  -->
		<div class="py-5 text-center">
   			<h2>Results For <%=searchTerm %></h2>
   		</div>

   		<!-- Restaurants and Recipes lists  -->
   		<div class="row mb-2">

   			<div class="col-md-6">
      			<h2  class="text-center"> Restaurants</h2>
          		<%

          		for(int i = 0; i < resultCount; i++){
          		%>
         			<div class="row no-gutters border rounded overflow-hidden flex-md-row mb-4 shadow-sm h-md-250 position-relative">
        			<div class="col p-4 d-flex flex-column position-static">
          			<div class="container">
  						<div class="row">
    						<div class="col-sm">

      							<strong>Name:</strong> <br><p><%=restaurantArr[i].getName() %> </p>
   							</div>

    					<div class="col-sm">
     	 						<strong>Stars:</strong> <br> <p> <%=restaurantArr[i].getRating() %> </p>
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
      							<strong>Minutes:</strong> <br> <p><%=restaurantArr[i].getDrivingTime() %> </p>
   							</div>

    					<div class="col-sm">
     	 						<strong>Address: </strong><br> <p><%=restaurantArr[i].getAddress() %></p>
    					</div>
  						</div>
					</div>


					
          			<a href="/FeedMe/restaurantDetails?arrNum=<%=i%>" class="stretched-link"></a>
        			</div>
        			<div class="col-auto d-none d-lg-block">
        			<%
        				String restaurantPrice = "";
        				int price = (int)restaurantArr[i].getPrice();
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
          			<svg class="bd-placeholder-img" width="100" height="280" xmlns="http://www.w3.org/2000/svg" preserveAspectRatio="xMidYMid slice" focusable="false" role="img" aria-label="Placeholder: $$$"><title>$$</title><rect width="100%" height="100%" fill="#55595c"></rect><text x="30%" y="50%" fill="#eceeef" dy=".3em"><%=restaurantPrice %> </text></svg>
        			</div>
      				</div>
          		<% } %>
       		 </ul>

    	</div>

    	<!-- Recipes -->
    		<div class="col-md-6">
      			<h2 class= "text-center"> Recipes</h2>
          		<% for(int i = 0; i < resultCount; i++){ %>
         			<div class="row no-gutters border rounded overflow-hidden flex-md-row mb-4 shadow-sm h-md-250 position-relative">
        			<div class="col p-4 d-flex flex-column position-static">
          			<div class="container">
  						<div class="row">
    						<div class="col-sm">
      							<strong>Name:</strong> <br><p><%=recipeArr[i].getName() %></p>
   							</div>

    					<div class="col-sm">
    						<% String recipeRating = String.format("%.1f",recipeArr[i].getRating()); %>
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
      							<strong>Cook Time:</strong> <br> <p><%=recipeArr[i].getCookTime() %></p>
   							</div>

    					<div class="col-sm">
     	 						<strong>Prep Time: </strong><br> <p><%=recipeArr[i].getPrepTime() %></p>
    					</div>
  						</div>
					</div>



          			<a href="/FeedMe/recipeDetails?arrNum=<%=i%>" class="stretched-link"></a>
        			</div>
        			<div class="col-auto d-none d-lg-block">
          			<svg class="bd-placeholder-img" width="100" height="250" xmlns="http://www.w3.org/2000/svg" preserveAspectRatio="xMidYMid slice" focusable="false" role="img" aria-label="Placeholder: $$$"><title>$$</title><rect width="100%" height="100%" fill="#55595c"></rect><text x="30%" y="50%" fill="#eceeef" dy=".3em"></text></svg>
        			</div>
      				</div>
          		<% } %>
       		 </ul>

    	</div>

   		</div>

	</div>

</body>
