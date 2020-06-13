package domain.model;

import java.util.HashMap;

public class Fridge {
	private HashMap<Integer, Double> ingredients;
	public Fridge(HashMap<Integer, Double> ingredients) {
		this.ingredients = ingredients;
	}
	public Fridge() {}
	public HashMap<Integer, Double> getIngredients() {
		return ingredients;
	}
}