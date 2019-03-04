package cs310GroupProject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class accessYelpAPI {
	
	static String API_KEY = "w3PGnJQ06Zd1DcF_c-hrn_ZBL4mt-qQ6t3R4ytCJF5bbYJB2ORyniUL4XKZIRPDw2N9d5poklzraRrvC75Sw4LOPuxMPumVmqKFKxnqHmUxIunkHy3l-M-3wVz57XHYx";
	static String CLIENT_ID = "YourA2mR9_8h-uctIT2HFg";
	
	static String GET_URL = "https://api.yelp.com/v3/businesses/search?term=_____&latitude=34.020807&longitude=-118.284668";
		
//	public static void main (String args[]) {
//		////////////////////////////////////////////
//		//main method is just for testing purposes//
//		////////////////////////////////////////////
//		
//		String searchTerm = "tofu";
//		
//		ArrayList<Restaurant> output = YelpRestaurantSearch(searchTerm);
//		
//		for(int i = 0; i < output.size(); i++) {
//			Restaurant tempObj = output.get(i);
//			String name = tempObj.getName();
//			String url = tempObj.getwebsiteUrl();
//			String price = tempObj.getPrice();
//			String address = tempObj.getAddress();
//			String phone = tempObj.getPhoneNumber();
//			String rating = tempObj.getRating();
//			String drivingTime = tempObj.getDrivingTime();
//			
//			System.out.println("	restaurant " + i);
//			System.out.println("	name: " + name);
//			System.out.println("	url: " + url);
//			System.out.println("	price: " + price);
//			System.out.println("	address: " + address);
//			System.out.println("	phone: " + phone);
//			System.out.println("	rating: " + rating);
//			System.out.println("	drivingTime: " + drivingTime);
//			System.out.println("");
//			System.out.println("");
//		}
//	}

	
	
	public static ArrayList<Restaurant> YelpRestaurantSearch(String searchTerm) {
				
		GET_URL = GET_URL.replace("_____", searchTerm);
		
									//System.out.println("YELP GET URL: " + GET_URL);
									
		ArrayList<Restaurant> resultsAL = new ArrayList<Restaurant>();
		
		String name = "NULL";
		String websiteUrl = "NULL";
		String price = "NULL";
		String phoneNumber = "NULL";
		String rating = "NULL";
		String drivingTime= "NULL";
		
		String address1 = "NULL";
		String address2 = "NULL";
		String address3 = "NULL";
		String address4 = "NULL";
		
		try {
			URL url = new URL(GET_URL);
			HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
			httpCon.setRequestMethod("GET");
			httpCon.setRequestProperty("Content-Type", "application/json");
			httpCon.setRequestProperty("Authorization", "Bearer" + " " +  API_KEY);	//request object, set authorization key to access yelp API
			httpCon.connect();
			
			
		    BufferedReader br  = new BufferedReader(new InputStreamReader(httpCon.getInputStream()));
		    
		    JsonParser parser = new JsonParser(); 
		    
		    JsonObject jsonObj = (JsonObject)parser.parse(br); 
		    JsonArray jsonArr = (JsonArray) jsonObj.get("businesses");
	
		    for(int i = 0; i < 20; i++) {		//change i to get desired # of search terms
		    	JsonObject jsonobj_1 = (JsonObject)jsonArr.get(i);
		    	
		    	if(jsonobj_1.get("name") != null) {
		    		name = jsonobj_1.get("name").toString();
		    		name = name.replace("\"", "");
		    	}
		    	
		    	if(jsonobj_1.get("url") != null) {		//returns URL to yelp restaurant page
		    		websiteUrl = jsonobj_1.get("url").toString();	//no way to grab URL of restaurant website
		    		websiteUrl = websiteUrl.replace("\"", "");
		    	}
		    	
		    	if(jsonobj_1.get("price") != null) {
		    		price = jsonobj_1.get("price").toString();
		    		price = price.replace("\"", "");
		    	}
		    	
		    	if(jsonobj_1.get("phone") != null) {
		    		phoneNumber = jsonobj_1.get("phone").toString();
		    		phoneNumber = phoneNumber.replace("\"", "");
		    		phoneNumber = phoneNumber.substring(1, 1) + "(" + phoneNumber.substring(2,5) + ")-" + phoneNumber.substring(5,8) + "-" + phoneNumber.substring(8,phoneNumber.length());
		    	}
		    	
		    	if(jsonobj_1.get("rating") != null) {
		    		rating = jsonobj_1.get("rating").toString();
		    		rating = rating.replace("\"", "");
		    	}
		    	
		    	if(jsonobj_1.get("distance") != null) {	//yelp returns the straight line distance from tommy trojan to the 
		    		drivingTime = jsonobj_1.get("distance").toString();//restaurant in meters...
		    		drivingTime = drivingTime.replace("\"", "");
		    	}
		    		
		    	if(jsonobj_1.get("location") != null) {
		    		JsonObject jsonobj_2 = (JsonObject) jsonobj_1.get("location");
		    		
		    		if(jsonobj_2.get("address1") != null) {
		    			address1 = jsonobj_2.get("address1").toString();
		    		}
		    		
		    		if(jsonobj_2.get("city") != null) {
		    			address2 = jsonobj_2.get("city").toString();
		    		}
		    		
		    		if(jsonobj_2.get("state") != null) {
		    			address3 = jsonobj_2.get("state").toString();
		    		}
			    	
		    		if(jsonobj_2.get("zip_code") != null) {
		    			address4 = jsonobj_2.get("zip_code").toString();
		    		}
		    		
		    	}
		    	
		    	
		    	String fullAddress = address1 + ", " + address2 + ", " + address3 + ", " + address4;
		    	fullAddress = fullAddress.replace("\"", "");
		 		    	
		    	Restaurant restaurantObj = new Restaurant(name, websiteUrl, price, fullAddress, phoneNumber, rating, drivingTime);
		    	resultsAL.add(restaurantObj);
		    }
		    
			
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		
		
		return resultsAL;		//returns ArrayList of restaurant objects

	}

}
