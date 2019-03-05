package api;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Scanner;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/* 
 * Google Search Class
 * API Key / Search Engine ID from Jiho's Google Account
 * Limited to 100 queries a day
 */
public class GoogleImageSearch {

	static final String API_KEY = "AIzaSyD1JgSwkEQEGgW3tDCFa2pAhYGGYM52Nmw";
	static final String SEARCH_ENGINE_ID = "012879953607576427254:2cidu_it4hw";

	
	// Returns an array of image urls using the search term
	public static String[] GetImagesFromGoogle(String searchTerm) {
		
		try {
			searchTerm = URLEncoder.encode(searchTerm, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		String query = "https://www.googleapis.com/customsearch/v1?searchType=image&imgType=photo&key=" + API_KEY
				+ "&cx=" + SEARCH_ENGINE_ID + "&q=" + searchTerm;
		
		// Parse JSON
		String json = jsonGetRequest(query);
		
		if(json == null) {
			System.out.println("Google Image query failed!");
			return null;
		}
		
		JsonElement root = new JsonParser().parse(json);
		JsonObject rootObj = root.getAsJsonObject();
		int count = rootObj.getAsJsonObject("queries").getAsJsonArray("request").get(0)
					.getAsJsonObject().getAsJsonPrimitive("count").getAsInt();
		JsonArray items = rootObj.getAsJsonArray("items");
		
		// Maximum value of count is 10 (API default)
		String[] imageVec = new String[10];
		for (int i = 0; i < count; ++i) {
			imageVec[i] = items.get(i).getAsJsonObject().getAsJsonPrimitive("link").getAsString();
		}
		return imageVec;
	}
	
	private static String jsonGetRequest(String urlQueryString) {
		String json = null;
		try {
			URL url = new URL(urlQueryString);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setDoOutput(true);
			connection.setInstanceFollowRedirects(false);
			connection.setRequestMethod("GET");
			connection.setRequestProperty("Content-Type", "application/json");
			connection.setRequestProperty("charset", "utf-8");
			connection.connect();
			InputStream inStream = connection.getInputStream();
			json = streamToString(inStream); // input stream to string
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return json;
	}

	private static String streamToString(InputStream inputStream) {
		Scanner scan = new Scanner(inputStream, "UTF-8");
		String text = scan.useDelimiter("\\Z").next();
		scan.close();
		return text;
	}
}