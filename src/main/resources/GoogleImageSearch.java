import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import java.util.Vector;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class GoogleImageSearch {
	String API_KEY = "AIzaSyCRh3BOWSeB2F3UfnT9d8Aqbtkih5VqdtI";
	String SEARCH_ENGINE_ID = "012879953607576427254:2cidu_it4hw";
	
	// Return a vector of image urls using the search term
	Vector<String> GetImagesFromGoogle(String searchTerm) {
		Vector<String> imageVec = new Vector<String>();
		String query = "https://www.googleapis.com/customsearch/v1?searchType=image&imgType=photo&key=" + API_KEY
				+ "&cx=" + SEARCH_ENGINE_ID + "&q=" + searchTerm;
		
		// Parse JSON
		String json = jsonGetRequest(query);
		JsonElement root = new JsonParser().parse(json);
		JsonObject rootObj = root.getAsJsonObject();
		int count = rootObj.getAsJsonObject("queries").getAsJsonArray("request").get(0)
					.getAsJsonObject().getAsJsonPrimitive("count").getAsInt();
		JsonArray items = rootObj.getAsJsonArray("items");
		// Maximum value of count is 10 (API default)
		for (int i = 0; i < count; ++i) {
			imageVec.add(items.get(i).getAsJsonObject().getAsJsonPrimitive("link").getAsString());
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