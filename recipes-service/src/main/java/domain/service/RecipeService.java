package domain.service;

import java.util.List;

import domain.model.Recipe;

public interface RecipeService {
	
	public Long create(Recipe recipe);
	public List<Recipe> getAll();

}