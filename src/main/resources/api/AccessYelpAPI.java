package api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Vector;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import data.Restaurant;

public class AccessYelpAPI {
	
	static String API_KEY = "w3PGnJQ06Zd1DcF_c-hrn_ZBL4mt-qQ6t3R4ytCJF5bbYJB2ORyniUL4XKZIRPDw2N9d5poklzraRrvC75Sw4LOPuxMPumVmqKFKxnqHmUxIunkHy3l-M-3wVz57XHYx";
	static String CLIENT_ID = "YourA2mR9_8h-uctIT2HFg";
	
	static double ttLat = 34.020807;	//Latitude & Longitude of tommy trojan
	static double ttLong = -118.284668;
		
//	public static void main (String args[]) {
//		////////////////////////////////////////////
//		//main method is just for testing purposes//
//		////////////////////////////////////////////
//		
//		String searchTerm = "tofu";
//		
//		Vector<Restaurant> output = YelpRestaurantSearch(searchTerm, 20);
//		for(int i = 0; i < output.size(); i++) {
//			Restaurant tempObj = output.get(i);
//			String name = tempObj.getName();
//			String url = tempObj.getwebsiteUrl();
//			int price = tempObj.getPrice();
//			String address = tempObj.getAddress();
//			String phone = tempObj.getPhoneNumber();
//			double rating = tempObj.getRating();
//			int drivingTime = tempObj.getDrivingTime();
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
	
	public static Vector<Restaurant> YelpRestaurantSearch(String searchTerm, int resultCount) {
		
		try {
			searchTerm = URLEncoder.encode(searchTerm, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		String GET_URL = "https://api.yelp.com/v3/businesses/search?"
				+ "term=_____" // Search Term
				+ "&latitude=34.020807&longitude=-118.284668" // Coordinates of Tommy Trojan
				+ "&sort_by=distance" // Sort by distance
				+ "&categories=restaurants";
		GET_URL = GET_URL.replace("_____", searchTerm);
		
									
		Vector<Restaurant> resultsAL = new Vector<Restaurant>();
		
		String name = "NULL";
		String websiteUrl = "NULL";
		int price = -1;
		String price_string = "NULL";
		String phoneNumber = "NULL";
		double rating = -1;
		int drivingTime = -1;
		
		String address1 = "NULL";
		String address2 = "NULL";
		String address3 = "NULL";
		String address4 = "NULL";
		
		double restLat = -1;
		double restLong = -1;
		
		int resultsLeft = resultCount;
		int limit, offset = 0;
		while (resultsLeft > 0) {
			if (resultsLeft >= 50) {
				limit = 50;
				resultsLeft -= 50;
			}
			else {
				limit = resultsLeft;
				resultsLeft = 0;
			}
			try {
				URL url = new URL(GET_URL + "&limit=" + limit + "&offset=" + offset);
				offset += limit;
				HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
				httpCon.setRequestMethod("GET");
				httpCon.setRequestProperty("Content-Type", "application/json");
				httpCon.setRequestProperty("Authorization", "Bearer" + " " +  API_KEY);	//request object, set authorization key to access yelp API
				httpCon.connect();
				
			    BufferedReader br  = new BufferedReader(new InputStreamReader(httpCon.getInputStream()));
			    
			    JsonParser parser = new JsonParser(); 
			    
			    JsonObject jsonObj = (JsonObject)parser.parse(br); 
			    JsonArray jsonArr = (JsonArray) jsonObj.get("businesses");
		
			    for(int i = 0; i < limit; i++) {		//change i to get desired # of search terms
			    	JsonObject jsonobj_1 = null;
			    	try {
			    		jsonobj_1 = (JsonObject)jsonArr.get(i);
			    	} catch (IndexOutOfBoundsException e) {
			    		System.out.println("Could not get " + resultCount + " results from the Yelp API for query\n" + GET_URL);	
			    		break;
			    	}
			    	
			    	if(jsonobj_1.get("name") != null) {
			    		name = jsonobj_1.get("name").toString();
			    		name = name.replace("\"", "");
			    	}
			    	
			    	if(jsonobj_1.get("url") != null) {		//returns URL to yelp restaurant page
			    		websiteUrl = jsonobj_1.get("url").toString();	//no way to grab URL of restaurant website
			    		websiteUrl = websiteUrl.replace("\"", "");
			    	}
			    	
			    	if(jsonobj_1.get("price") != null) {
			    		price_string = jsonobj_1.get("price").getAsString();
			    		price_string = price_string.replace("\"", "");
			    		price = price_string.length();		//price represents the number of dollar signs ($) returned from Yelp
			    	}
			    	
			    	if(jsonobj_1.get("phone") != null && jsonobj_1.get("phone").getAsString().length() > 0) {
			    		phoneNumber = jsonobj_1.get("phone").toString();
			    		phoneNumber = phoneNumber.replace("\"", "");
			    		phoneNumber = phoneNumber.substring(1, 1) + "(" + phoneNumber.substring(2,5) + ")-" + phoneNumber.substring(5,8) + "-" + phoneNumber.substring(8,phoneNumber.length());
			    	}
			    	
			    	if(jsonobj_1.get("rating") != null) {
			    		rating = jsonobj_1.get("rating").getAsDouble();
			    		// rating = rating.replace("\"", "");
			    	}
			    	
			    	if(jsonobj_1.get("distance") != null) {	//yelp returns the straight line distance from tommy trojan to the 
			    		drivingTime = jsonobj_1.get("distance").getAsInt(); //restaurant in meters...
			    		// drivingTime = drivingTime.replace("\"", "");
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
			    	
			    	
			    	if(jsonobj_1.get("coordinates") != null) {
			    		JsonObject jsonobj_3 = (JsonObject) jsonobj_1.get("coordinates");
			    		restLat = jsonobj_3.get("latitude").getAsDouble();
			    		restLong = jsonobj_3.get("longitude").getAsDouble();
			    	}
			    	
			    	GoogleDirections gd = new GoogleDirections();
			    	drivingTime = gd.getDrivingTime(ttLat, ttLong, restLat, restLong);
			    	drivingTime = drivingTime/60;
	
			    	Restaurant restaurantObj = new Restaurant(name, websiteUrl, price, fullAddress, phoneNumber, rating, drivingTime);
			    	resultsAL.add(restaurantObj);
			    }
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		
		return resultsAL;		//returns ArrayList of restaurant objects
	}

	//Uncomment main method and run as Java Application to test
	/*
	public static void main(String[] args) {
		int count = 60;
		Vector<Restaurant> arr = AccessYelpAPI.YelpRestaurantSearch("mexican", count);
		for (int i = 0; i < count; i++) {
			System.out.println(i);
			System.out.println("name: " + arr.get(i).getName());
			System.out.println("driveTime: " + arr.get(i).getDrivingTime());
		}
	}
	*/
}