package api.rest;

import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import domain.model.ItemCart;
import domain.model.ItemFridge;
import domain.service.ListsService;

@ApplicationScoped
@Path("/lists")
public class ListsServiceRestService {

	@Inject
	private ListsService listsService;

	@GET // Get the cart from a user
	@Path("/cart/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<ItemCart> getAllCart(@PathParam("id") int userID) {
		return listsService.getAllCart(userID);
	}
	
	@GET // Get the fridge from a user
	@Path("/fridge/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<ItemFridge> getAllFridge(@PathParam("id") int userID) {
		return listsService.getAllFridge(userID);
	}
	
	@POST // Modify/Add an item to cart of a user
	@Path("/addtocart")
	@Consumes(MediaType.APPLICATION_JSON)
	public void modIngredientCart(ItemCart itemCart) {
		listsService.modIngredientCart(itemCart);
	}
	
	@POST // Modify/Add an item to fridge of a user
	@Path("/addtofridge")
	@Consumes(MediaType.APPLICATION_JSON)
	public void modIngredientFridge(ItemFridge itemFridge) {
		listsService.modIngredientFridge(itemFridge);
	}
	
	@DELETE // Remove an item to cart of a user
	@Path("/removefromcart")
	@Consumes(MediaType.APPLICATION_JSON)
	public void removeIngredientCart(ItemCart itemCart) {
		listsService.removeIngredientCart(itemCart);
	}
	
	@DELETE // Remove an item to fridge of a user
	@Path("/removefromfridge")
	@Consumes(MediaType.APPLICATION_JSON)
	public void removeIngredientFridge(ItemFridge itemFridge) {
		listsService.removeIngredientFridge(itemFridge);
	}
}