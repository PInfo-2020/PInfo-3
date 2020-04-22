package api.rest;

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

import domain.model.Comment;
import domain.model.Grade;
import domain.model.Recipe;
import domain.service.RecipeService;

@ApplicationScoped
@Path("/recipe")
public class RecipeRestService {
	
	@Inject
	private RecipeService recipeService;
	
	
	@GET // Return all of the recipes
	@Produces(MediaType.APPLICATION_JSON)
	public List<Recipe> getAll() {
		return recipeService.getAll();
	}
	
	@POST // Create a new recipe
	@Consumes(MediaType.APPLICATION_JSON)
	public void create(Recipe recipe) {
		recipeService.create(recipe, recipeService.count()+1);
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
	@Path("/countComment")
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
	@Path("/user/{id}/grade")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Grade> getAllGrades(@PathParam("id") Long userId) {
		return recipeService.getAllGrades(userId);
	}
	
	@GET // Return the grade of a recipe
	@Path("/{id}/grade")
	@Produces(MediaType.APPLICATION_JSON)
    public double getGrade(@PathParam("id") Long recipeId) {
		return recipeService.getGrade(recipeId);
	}
	
}