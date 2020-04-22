package domain.service;

import java.util.List;

import domain.model.Comment;
import domain.model.Grade;
import domain.model.Recipe;

public interface RecipeService {
	
	public void create(Recipe recipe, Long size);
	public List<Recipe> getAll();
	public Recipe get(Long recipeId);
	public List<Recipe> getByName(String recipeName);
	public Long count();
	public Long countComment();
	public Long countGrade();
	public void addComment(Comment comment, Long size, Long nbRecipes);
	public List<Comment> getAllComments(Long recipeId);
	public void addGrade(Grade grade, Long size, Long nbRecipes, List<Grade> grades);
	public List<Grade> getAllGrades(Long userId);
	public double getGrade(Long recipeId);

}