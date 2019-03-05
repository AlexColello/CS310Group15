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
        <ol id="instructions"><li>Instr. 1</li></ol>
      </div>
    </div>
    <!-- Holds all the buttons -->
    <div id="buttons">
      <!-- Brings user to a printable version of the page -->
      <button id="printButton">Printable Version</button>
      <!-- Brings user back to results page -->
      <button id="backtoResults">Back To Results</button>
      <!-- This is the drop-down menu -->
      <select id="dropDownBar">
        <option disabled selected value> -- select an option -- </option>
        <option value="favorites" >Favorites</option>
        <option value="toExplore">To Explore</option>
        <option value="doNotShow">Do Not Show</option>
      </select>
      <!-- Button to add item to selected list, doesn't do anything if choice is empty -->
      <button id="addToList">Add to List</button>
    </div>
    <!-- Homebrew JS -->
    
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