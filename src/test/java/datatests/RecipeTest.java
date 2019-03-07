package datatests;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;

import org.junit.Test;

import data.Recipe;

public class RecipeTest {

	@Test
	public void testConstructor() {
		String name = "Good Food";
		String pictureUrl = "http://www.todayifoundout.com/wp-content/uploads/2017/11/rick-astley.png";
		double prepTime = 10;
		double cookTime = 25;
		ArrayList<String> ingredients = new ArrayList<String>();
		ingredients.add("1 teaspoon ground ginger");
		ingredients.add("1 rack of lamb");
		ArrayList<String> instructions = new ArrayList<String>();
		instructions.add("Throw in a pan.");
		instructions.add("Cook until done.");
		double rating = 4.5;
		
		Recipe recipe = new Recipe(name, pictureUrl, prepTime, cookTime, ingredients, instructions, rating);
		
		assertEquals(name, recipe.getName());
		assertEquals(pictureUrl, recipe.getPictureUrl());
		assertEquals(prepTime, recipe.getPrepTime(), 0);
		assertEquals(cookTime, recipe.getCookTime(), 0);
		assertEquals(ingredients, recipe.getIngredients());
		assertEquals(instructions, recipe.getInstructions());
		assertEquals(rating, recipe.getRating(), 0);
		
	}
	
	@Test
	public void testCompare() {
		
		String name = "Good Food";
		String pictureUrl = "http://www.todayifoundout.com/wp-content/uploads/2017/11/rick-astley.png";
		double prepTime = 10;
		double cookTime = 25;
		ArrayList<String> ingredients = new ArrayList<String>();
		ingredients.add("1 teaspoon ground ginger");
		ingredients.add("1 rack of lamb");
		ArrayList<String> instructions = new ArrayList<String>();
		instructions.add("Throw in a pan.");
		instructions.add("Cook until done.");
		double rating = 4.5;
		
		Recipe recipe1 = new Recipe(name, pictureUrl, prepTime, cookTime, ingredients, instructions, rating);
		Recipe recipe2 = new Recipe(name, pictureUrl, prepTime + 5, cookTime, ingredients, instructions, rating);
		Recipe recipe3 = new Recipe(name, pictureUrl, prepTime, cookTime, ingredients, instructions, rating);
		Recipe recipe4 = new Recipe(name, pictureUrl, prepTime - 5, cookTime, ingredients, instructions, rating);
		
		ArrayList<Recipe> recipes = new ArrayList<Recipe>();
		recipes.add(recipe1);
		recipes.add(recipe2);
		recipes.add(recipe3);
		recipes.add(recipe4);
		
		assertEquals(recipe1, recipes.get(0));
		assertEquals(recipe2, recipes.get(1));
		assertEquals(recipe3, recipes.get(2));
		assertEquals(recipe4, recipes.get(3));
		
		Collections.sort(recipes);
		
		assertEquals(recipe4, recipes.get(0));
		assertEquals(recipe1, recipes.get(1));
		assertEquals(recipe3, recipes.get(2));
		assertEquals(recipe2, recipes.get(3));
		
		
	}

}
