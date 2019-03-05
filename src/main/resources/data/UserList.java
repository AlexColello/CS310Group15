package data;
import java.io.Serializable;
import java.util.ArrayList;

public class UserList implements Serializable {
	private static final long serialVersionUID = 1L;

	private ArrayList<Restaurant> restaurants;
	private ArrayList<Recipe> recipes;
	
	public UserList() {
		restaurants = new ArrayList<Restaurant>();
		recipes = new ArrayList<Recipe>();
	}
	
	public ArrayList<Restaurant> getRestaurants() {
		return restaurants;
	}

	public ArrayList<Recipe> getRecipes() {
		return recipes;
	}

	public boolean add(Recipe r){	
		return recipes.add(r);
	}
	
	public boolean add(Restaurant r) {
		return restaurants.add(r);
	}
	
	public boolean remove(Recipe r) {
		return recipes.remove(r);
	}
	
	public boolean remove(Restaurant r) {
		return restaurants.remove(r);
	}
	
	public boolean contains(Recipe r) {
		return recipes.contains(r);
	}
	
	public boolean contains(Restaurant r) {
		return restaurants.contains(r);
	}
}
