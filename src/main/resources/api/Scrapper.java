package api;
import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import data.Recipe;

public class Scrapper {

	
	public static ArrayList<Recipe> search(String searchTerms, int n){
		
		ArrayList<Recipe> recipes = new ArrayList<Recipe>();
		
		String url =  "https://allrecipes.com/search/results/?wt=" + searchTerms + "&sort=re";
		try {
			Document doc = Jsoup.connect(url).get();
			Element elem = doc.getElementById("fixedGridSection");
			Elements recipeBoxes = elem.getElementsByClass("fixed-recipe-card__info");
						
			for(Element e : recipeBoxes) {
				Elements links = e.getElementsByTag("a");
				String link = links.get(0).attr("href");
				Recipe recipe = Scrapper.get(link);
				recipes.add(recipe);
			}
						
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return recipes;
	}
	
	public static Recipe get(String url) {
		
		Document doc = null;
		try {
			doc = Jsoup.connect(url).get();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Element recipeName = doc.getElementById("recipe-main-content");
		String name = recipeName.text();
		
		Elements recipePhoto = doc.getElementsByClass("rec-photo");
		String pictureUrl = recipePhoto.get(0).attr("src");
		
		Elements ratingSummary = doc.getElementsByClass("recipe-summary__stars");
		Elements rating = ratingSummary.get(0).getElementsByClass("rating-stars");
		String sRecipeRating = rating.attr("data-ratingstars");
		double recipeRating = Double.parseDouble(sRecipeRating);

		Element directions = doc.getElementsByClass("directions--section__steps").get(0);
		
		Elements times = directions.getElementsByTag("time");
		String sPrepTime = "";
		String sCookTime = "";
		for(Element time: times) {
			String timeType = time.attr("itemprop");
			if(timeType.equals("prepTime")) {
				sPrepTime = time.attr("datetime");
			}
			if(timeType.equals("cookTime")) {
				sCookTime = time.attr("datetime");
			}
		}
		double cookTime = parseTime(sCookTime);
		double prepTime = parseTime(sPrepTime);
		
		ArrayList<String> instructions = new ArrayList<String>();
		
		for(Element instruction : directions.getElementsByClass("recipe-directions__list--item")) {
			instructions.add(instruction.text());
		}
		
		
		return new Recipe(name, pictureUrl, prepTime, cookTime, new ArrayList<String>(), new ArrayList<String>(), recipeRating);
	}
	
	public static int parseTime(String datetime) {
		
		if(datetime.equals("")) {
			return -1;
		}
		
		int retval = 0;
		
		int hIndex = datetime.indexOf('H');
		int mIndex = datetime.indexOf('M');
		
		int hours = 0;
		int minutes = 0;
		
		if(hIndex > 2) {
			hours = Integer.parseInt(datetime.substring(2, hIndex));
			if(mIndex > 2) {
				minutes = Integer.parseInt(datetime.substring(hIndex+1, mIndex));
			}
		} else if(mIndex > 2) {
			minutes = Integer.parseInt(datetime.substring(2, mIndex));
		} else {
			return -1;
		}
		
		retval = minutes + 60 * hours;
		
		return retval;
	}
	
}
