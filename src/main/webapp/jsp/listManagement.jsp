<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">    
    <meta charset="ISO-8859-1">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <!-- Homebrew CSS  -->
    <link href="../css/buttons.css" rel="stylesheet" type="text/css">
    <link href="../css/details.css" rel="stylesheet" type="text/css">
    <link href="../css/list.css" rel="stylesheet" type="text/css">
    <link href="../css/listManagement.css" rel="stylesheet" type="text/css">

    <!-- Title -->
    <title>List Management</title>
  </head>
  <body>
    <div id="main">
      <!-- Holds the list name, and items -->
      <div id="listDetails">
        <h1 id="listName">L List</h1>
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
    <!-- Holds all the buttons -->
    <div id="buttons">
      <!-- Brings user to a printable version of the page -->
      <button id="printButton" class="bttn">Printable Version</button>
      <!-- Brings user back to results page -->
      <button id="backtoResults" class="bttn">Back To Results</button>
      <!-- This is the drop-down menu -->
      <select id="dropDownBar">
        <option disabled selected value> -- select an option -- </option>
        <option value="favorites" >Favorites</option>
        <option value="toExplore">To Explore</option>
        <option value="doNotShow">Do Not Show</option>
      </select>
      <!-- Button to add item to selected list, doesn't do anything if choice is empty -->
      <button id="addToList" class="bttn">Add to List</button>
    </div>
    <!-- Homebrew JS -->
    
    <!-- Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
  </body>
</html>