package api.rest;

import java.net.URI;
import java.util.List;
import java.util.Locale.Category;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import domain.model.Recipe;
import domain.service.RecipeService;

@ApplicationScoped
@Path("/recipe")
public class RecipeRestService {
	
	@Inject
	private RecipeService recipeService;
	
	@GET
	@Produces("application/json")
	public List<Recipe> getAll() {
		return recipeService.getAll();
	}
	
	@POST
	@Consumes("application/json")
	public Response create(Recipe recipe) {
		Long newId = null;
		try {
			newId = recipeService.create(recipe);
		} catch(IllegalArgumentException i) {
			return Response.status(Status.BAD_REQUEST).build();
		} catch(Exception e) {
			return Response.status(Status.BAD_GATEWAY).build();
		}
		
		return Response.status(Status.CREATED).location(URI.create("/recipe/" + newId.toString())).build();
	}

}