package api.rest;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import api.msg.RecipeProducer;
import domain.model.Comment;
import domain.model.Grade;
import domain.model.Ingredient;
import domain.model.Recipe;
import domain.service.RecipeService;

@ApplicationScoped
@Path("/recipe")
public class RecipeRestService {
	
	@Inject
	private RecipeService recipeService;
	
	@Inject
	private RecipeProducer recipeProducer;
	
	
	static private boolean test = false;
	static private List<Recipe> recipes = new ArrayList<Recipe>();
	
	
	@GET // Return all of the recipes
	@Produces(MediaType.APPLICATION_JSON)
	public List<Recipe> getAll() {
		return recipeService.getAll();
	}
	
	@POST // Create a new recipe and return his id
	@Consumes(MediaType.APPLICATION_JSON)
	public Long create(Recipe recipe) {
		return recipeService.create(recipe, recipeService.count()+1);
	}
	
	@GET // Find a recipe by id
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Recipe get(@PathParam("id") Long recipeId) {
		return recipeService.get(recipeId);
	}
	
	@GET // Find recipes by string contains in name
	@Path("/name/{name}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Recipe> getByName(@PathParam("name") String recipeName) {
		return recipeService.getByName(recipeName);
	}
	
	@GET // Return the number of recipes
	@Path("/count")
	@Produces(MediaType.APPLICATION_JSON)
    public Long count() {
		return recipeService.count();
	}
	
	@GET // Return the number of ingredients
	@Path("/countIngredient")
	@Produces(MediaType.APPLICATION_JSON)
    public Long countIngredient() {
		return recipeService.countIngredient();
	}
	
	@GET // Return the number of comments
	@Path("/countComment")
	@Produces(MediaType.APPLICATION_JSON)
    public Long countComment() {
		return recipeService.countComment();
	}
	
	@POST // Add a new comment to a recipe
	@Path("/comment")
	@Consumes(MediaType.APPLICATION_JSON)
	public void addComment(Comment comment) {
		recipeService.addComment(comment, recipeService.countComment()+1, recipeService.count());
	}
	
	@GET // Return all of the comments of a recipe
	@Path("/{id}/comment")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Comment> getAllComments(@PathParam("id") Long recipeId) {
		return recipeService.getAllComments(recipeId);
	}
	
	@GET // Return the number of grades
	@Path("/countGrade")
	@Produces(MediaType.APPLICATION_JSON)
    public Long countGrade() {
		return recipeService.countGrade();
	}
	
	@POST // Add a new grade to a recipe
	@Path("/grade")
	@Consumes(MediaType.APPLICATION_JSON)
	public void addGrade(Grade grade) {
		List<Grade> grades = recipeService.getAllGrades(grade.getUserId());
		recipeService.addGrade(grade, recipeService.countGrade()+1, recipeService.count(), grades);
	}
	
	@GET // Return all of the grades from a user
	@Path("/user/{id}/grades")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Grade> getAllGrades(@PathParam("id") String userId) {
		return recipeService.getAllGrades(userId);
	}
	
	@GET // Return the grade of a recipe
	@Path("/{id}/grade")
	@Produces(MediaType.APPLICATION_JSON)
    public double getGrade(@PathParam("id") Long recipeId) {
		return recipeService.getGrade(recipeId);
	}
	
	@POST // Create a new ingredient
	@Path("/{id}/addingredients")
	@Consumes(MediaType.APPLICATION_JSON)
	public void createIngredient(List<Ingredient> ingredients, @PathParam("id") Long recipeId) {
		for (Ingredient ingredient : ingredients) {
			recipeService.createIngredient(ingredient, recipeId, recipeService.countIngredient()+1);
		}
	}
	
	@GET // Return all of the ingredients of a recipe
	@Path("/{id}/ingredient")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Ingredient> getAllIngredients(@PathParam("id") Long recipeId) {
		return recipeService.getAllIngredients(recipeId);
	}
	
	@GET // Return the 3 best recipes
	@Path("/top")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Recipe> getBestRecipes() {
		return recipeService.getBestRecipes();
	}
	
	@GET // Return the grade of a user (the mean of all grades from recipes created by the user)
	@Path("/user/{id}/grade")
	@Produces(MediaType.APPLICATION_JSON)
    public double getUserGrade(@PathParam("id") String userId) {
		return recipeService.getUserGrade(userId);
	}
	
	@GET // Return all the recipes that contains ingredients from user of id userID
	@Path("/user/{id}/fridge/recipe")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Recipe> getRecipesByFridge(@PathParam("id") String userId) {
		recipeProducer.send(userId);
		
		while(!test) {
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		test = false;
		List<Recipe> vide = new ArrayList<Recipe>();
		List<Recipe> r = recipes;
		recipes =  vide;
		
		return r;
	}
	
	@GET // Find all recipes that are vegetarian
	@Path("/vegetarien")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Recipe> getByVegetarien() {
		return recipeService.getByVegetarien();
	}
	
	@GET // Find all recipes that are vegan
	@Path("/vegetarien")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Recipe> getByVegan() {
		return recipeService.getByVegan();
	}
	
	@GET // Return all of the recipes created by the user of id userId
	@Path("/user/{id}/recipes")
	@Produces(MediaType.APPLICATION_JSON)
    public List<Recipe> getCreatedRecipes(@PathParam("id") String userId) {
		return recipeService.getCreatedRecipes(userId);
	}
	
	
	public void setRecipes(List<Recipe> recipes) {
		RecipeRestService.recipes = recipes;
	}
	
	public void setTest(boolean test) {
		RecipeRestService.test = test;
	}
	
}