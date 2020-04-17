package domain.service;

import java.util.List;
import domain.model.Recipe;

public interface RecipeService {
	
	public void create(Recipe recipe, Long size);
	public List<Recipe> getAll();
	public Recipe get(Long recipeId);
	public List<Recipe> getByName(String recipeName);
	public Long count();

}