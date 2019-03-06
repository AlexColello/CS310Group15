<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ page import="data.Recipe"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">    
    <meta charset="ISO-8859-1">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
  	 <%@page import="java.util.*" %>
	<%@page import="data.*"%>
    <% 
	Recipe recipeVal = (Recipe) request.getAttribute("recipeVal");
    int arrNum = Integer.parseInt((String) request.getParameter("arrNum"));
	if(recipeVal == null){
		ArrayList<Recipe> rest = (ArrayList<Recipe>) request.getSession().getAttribute("recipes");
		recipeVal = rest.get(arrNum);
	}
    %>
    <!-- Title -->
    <title>Recipe Details</title>
  </head>


  <body>
    <!-- Main Div -->
    <div id="main">
      <!-- Title -->
      <h1 id="recipeName"><%= recipeVal.getName() %></h1>
      <!-- Holds image, prep and cook time of recipe-->
      <div id="details">
      	<% String picUrl = recipeVal.getPictureUrl(); %>
        <img id="recipePicture" src="<%= picUrl %>" alt="Recipe Image"/>
        <p id="prepTime"><strong>Prep Time: </strong><%=recipeVal.getPrepTime() %></p>
        <p id="cookTime"><strong>Cook Time: </strong><%=recipeVal.getCookTime() %></p>
      </div>
      <!-- Ingredients -->
      <div id="ingredientsBloc">
        <h2>Ingredients</h2>
        <ul id=ingredients>
          <% ArrayList<String> ingredients = (ArrayList<String>) recipeVal.getIngredients();%>
          <% for(int i = 0; i < ingredients.size(); i++){ %>
          	<p>	<%=ingredients.get(i) %>></p>
          <% } %>
        </ul>
      </div>
      <!-- Instructions -->
      <div id="instructionsBloc">
        <h2>Instructions</h2>
        <ul id=instructions>
          <% ArrayList<String> ins = (ArrayList<String>) recipeVal.getInstructions();%>
          <% for(int i = 0; i < ins.size(); i++){ %>
          	<p>	<%=ins.get(i) %></p>
          <% } %>
        </ul>
      </div>
    </div>
    <!-- Holds all the buttons -->
    <div id="buttons">
      <!-- Brings user to a printable version of the page -->
      <form action="/FeedMe/recipeDetailsPagePrintableVersion?arrNum=<%= arrNum%>" method= "POST">
      	<button id="printButton">Printable Version</button>
      </form>
      
      <!-- Brings user back to results page -->
       <form action="/FeedMe/results" method="POST">
        <button id="backToResults" class="bttn">Back To Results</button>
      </form>
      <!-- This is the drop-down menu -->
      <form method="POST" onsubmit="return addToList(this)">
      <input type="hidden" name="arrNum" value="<%= arrNum %>">
      <select name="listType" id="dropDownBar">
        <option disabled selected value> -- select an option -- </option>
        <option value="f" >Favorites</option>
        <option value="t">To Explore</option>
        <option value="d">Do Not Show</option>
      </select>
      <!-- Button to add item to selected list, doesn't do anything if choice is empty -->
      <button type="submit" id="addToList">Add to List</button>
      </form>
    </div>
    <!-- Homebrew JS -->
    <script>
    function addToList(form){
    	var userInput = document.getElementById('listName').value;
    	console.log(userInput);
    	if (userInput == null || userInput.length == 0){
    		return false;	
    	}
    	else{
    		form.action = "/FeedMe/recipeDetails";
    	}
    }
    </script>
    <!-- Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
  </body>
  <!-- Homebrew CSS  -->
  <style>
    <%@ include file="/css/buttons.css"%>
    <%@ include file="/css/details.css"%>
  </style>
</html>