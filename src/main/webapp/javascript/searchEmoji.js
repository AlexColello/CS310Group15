
/**
 * Javascript for the emoji button
 */


function changeEmoji(form){
		var image = document.getElementById('emoji');
		image.src = "https://buildahead.com/wp-content/uploads/2017/02/happy-emoji-smaller.png";
		var userInput = document.getElementById('userInput').value;
		console.log(userInput);
		if (userInput == null || userInput.length == 0){
			
		}
		else{
			form.action = "/FeedMe/results";
		}
} 
	