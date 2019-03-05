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
	UserList recipeVal = (UserList) request.getAttribute("listVal");
    %>
    <!-- Title -->
    <title>Recipe Details</title>
  </head>
	
    <!-- Title -->
    <title>List Management</title>
  </head>
  <body>
    <div id="main">
      <!-- Holds the list name, and items -->
      <div id="listDetails">
        <h1 id="listTitle">L List</h1>
        <ul id="listItems">
          <% for(int i = 0, j = 0; i < 10 && j < 10; ++i, ++j){ %>
          <li class="item">
            <div class="container">
              <div class="itemLeft">
                <div class="p1"><p>Name</p></div>
                <div class="p1"><p>$$</p></div>
                <div class="p1"><p>Minutes</p></div>
                <div class="p1"><p>Address</p></div>
              </div>
              <div class="itemRight">
                <div class="p2"><p>Price</p></div>
              </div>
            </div>
          </li>
          <% } %>
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