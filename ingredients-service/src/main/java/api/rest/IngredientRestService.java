package api.rest;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import domain.model.Ingredient;
import domain.service.IngredientService;


@ApplicationScoped
@Path("/ingredients")
public class IngredientRestService {
	
	@Inject
	private IngredientService ingredientService;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Ingredient> getAll() {
		return ingredientService.getAll();
	}
	
	@GET
	@Path("/id/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Ingredient getById(@PathParam("id") int id) {
		return ingredientService.getById(id);
	}
	
	@GET
	@Path("/name/{name}")
	@Produces(MediaType.APPLICATION_JSON)
	public Ingredient getByName(@PathParam("name") String name) {
		return ingredientService.getByName(name);
	}
	
	@GET
	@Path("/unit/{name}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getUnitByName(@PathParam("name") String name) {
		return ingredientService.getUnitByName(name);
	}
	
	@POST
	@Path("/create")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response create(Ingredient ingredient) {
		try {
			ingredientService.create(ingredient);
		} catch(IllegalArgumentException i) {
			return Response.status(Status.BAD_REQUEST).build();
		} catch(Exception e) {
			return Response.status(Status.BAD_GATEWAY).build();
		}
		return Response.ok().build();
	}
	
	@DELETE
	@Path("/delete/{id}")
	public Response delete(@PathParam("id") int id) {
		try {
			ingredientService.delete(ingredientService.getById(id));
		} catch(Exception e) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
		return Response.ok().build();
	}
	

}