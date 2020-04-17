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
	
}