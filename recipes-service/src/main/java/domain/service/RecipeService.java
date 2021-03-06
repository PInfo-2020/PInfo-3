package domain.service;

import java.util.List;

import domain.model.Comment;
import domain.model.Fridge;
import domain.model.Grade;
import domain.model.Ingredient;
import domain.model.Recipe;

public interface RecipeService {
	
	public Long create(Recipe recipe, Long size);
	public void createIngredient(Ingredient ingredient, Long recipeId, Long size);
	public List<Recipe> getAll();
	public Recipe get(Long recipeId);
	public List<Recipe> getByName(String recipeName);
	public Long count();
	public Long countIngredient();
	public Long countComment();
	public Long countGrade();
	public void addComment(Comment comment, Long size, Long nbRecipes);
	public List<Comment> getAllComments(Long recipeId);
	public void addGrade(Grade grade, Long size, List<Grade> grades);
	public List<Grade> getAllGrades(String userId);
	public double getGrade(Long recipeId);
	public List<Ingredient> getAllIngredients(Long recipeId);
	public List<Recipe> getBestRecipes();
	public double getUserGrade(String userId);
	public List<Recipe> getRecipesByFridge(Fridge fridge);
	public List<Recipe> getByVegetarien();
	public List<Recipe> getByVegan();
	public List<Recipe> getCreatedRecipes(String userId);

}