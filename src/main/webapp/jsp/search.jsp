
<html>
<head>
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
	<link href="../css/search.css" rel="stylesheet" type="text/css">
</head>

<body>

<div class="container">


    <h2>I'm Hungry</h2>

      <p>
    	<form action="/FeedMe/results">
  		<input type="text" name="q" value="">
  		 <input type="text" name="n" value="5" >
  			<br>
  			<img src="https://images.emojiterra.com/twitter/v11/512px/1f620.png" id ="emoji" height = 20 width = 20>
  			<button type="submit" value="Feed Me" onclick= "change();">Feed Me</button>
		</form>
    </p>




</div>

<script type = "text/javascript">
	
	function change(){
		var image = document.getElementById('emoji');
		image.src = "https://buildahead.com/wp-content/uploads/2017/02/happy-emoji-smaller.png";
	}
		

</script>


</body>
</html>
