import java.util.ArrayList;

public class Recipe implements Comparable<Recipe>{

	private String name = "";
	private String pictureUrl = "";
	private double prepTime = -1;
	private double cookTime = -1;
	private ArrayList<String> ingredients;
	private ArrayList<String> instructions;
	private double rating = -1;
	
	public Recipe(String name, String pictureUrl, double prepTime, double cookTime, ArrayList<String> ingredients,
			ArrayList<String> instructions, double rating) {
		this.name = name;
		this.pictureUrl = pictureUrl;
		this.prepTime = prepTime;
		this.cookTime = cookTime;
		this.ingredients = ingredients;
		this.instructions = instructions;
		this.rating = rating;
	}

	public String getName() {
		return name;
	}

	public String getPictureUrl() {
		return pictureUrl;
	}

	public double getPrepTime() {
		return prepTime;
	}

	public double getCookTime() {
		return cookTime;
	}

	public ArrayList<String> getIngredients() {
		return ingredients;
	}

	public ArrayList<String> getInstructions() {
		return instructions;
	}

	public double getRating() {
		return rating;
	}

	public int compareTo(Recipe arg0) {
		return (int) (this.getPrepTime() - arg0.getPrepTime());
	}
	
}
