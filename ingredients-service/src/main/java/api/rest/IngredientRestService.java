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

import api.msg.IngredientProducer;
import domain.model.Ingredient;
import domain.service.IngredientService;

@ApplicationScoped
@Path("/ingredients")
public class IngredientRestService {
	
	@Inject
	private IngredientService ingredientService;
	@Inject
	private IngredientProducer ingredientProducer;
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response create(Ingredient ingredient) {
		try {
			ingredientService.create(ingredient);
		} catch(IllegalArgumentException i) {
			return Response.status(Status.BAD_REQUEST).build();
		} catch(Exception e) {
			return Response.status(Status.BAD_GATEWAY).build();
		}
		
		ingredientProducer.send(ingredient, "IngredientCreate");
		return Response.ok().build();
	}
	
	@DELETE
	@Path("{id}")
	public Response delete(@PathParam("id") Long id) {
		try {
			ingredientService.delete(ingredientService.get(id));
			ingredientProducer.send(id, "IngredientDeleted");
		} catch(Exception e) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
		
		return Response.ok().build();
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response update(Ingredient ingredient) {
		try {
			ingredientService.update(ingredient);
			ingredientProducer.send(ingredient, "IngredientUpdate");
		} catch(Exception e) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
		
		return Response.ok().build();
	}
	
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Ingredient get(@PathParam("id") Long id) {
		return ingredientService.get(id);
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Ingredient> getAll(){
		return ingredientService.getAll();
	}

}