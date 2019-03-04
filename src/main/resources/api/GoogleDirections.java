package api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class GoogleDirections {
	
	private final static String apiKey = "AIzaSyCVTgss5eiM_qBXwsnz_UgVK4d-FWiXZIM";
	
	public static double getDrivingTime(double startLatitude, double startLongitude, double endLatitude, double endLongitude) {
		
		String urlString = "https://maps.googleapis.com/maps/api/distancematrix/json?";
		urlString += "origins=" + Double.toString(startLatitude) + "," + Double.toString(startLongitude);
		urlString += "&destinations=" + Double.toString(endLatitude) + "," + Double.toString(endLongitude);
		urlString += "&key=" + apiKey;
		
		System.out.println("Distance Request URL: " + urlString);
		
		URL url;
		HttpURLConnection httpCon;
		BufferedReader br = null;
		try {
			
			url = new URL(urlString);
			httpCon = (HttpURLConnection) url.openConnection();			
			httpCon.connect();
			br = new BufferedReader(new InputStreamReader(httpCon.getInputStream()));
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
			
		double durationSeconds = -1;
		try {
		    JsonParser parser = new JsonParser();
		    JsonObject jsonObj = (JsonObject)parser.parse(br); 
		    JsonArray rowArr = (JsonArray) jsonObj.get("rows");
		    JsonObject firstObject = (JsonObject) rowArr.get(0);
		    JsonObject element = (JsonObject) ((JsonArray) firstObject.get("elements")).get(0);
		    JsonObject duration = (JsonObject) element.get("duration");
	   
		    String durationValue = duration.get("value").getAsString();
		    durationSeconds = Double.parseDouble(durationValue);	   
	    } catch (NullPointerException e) {
	    	System.out.println("Failed for request " + urlString);
	    	e.printStackTrace();
	    }
	    
		return durationSeconds;
	}

}
