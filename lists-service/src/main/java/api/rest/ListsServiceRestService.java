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
	public List<ItemCart> getAllCart(@PathParam("id") String userID) {
		return listsService.getAllCart(userID);
	}
	
	@GET // Get the fridge from a user
	@Path("/fridge/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<ItemFridge> getAllFridge(@PathParam("id") String userID) {
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
	@Path("/removefromcart/{userID}/{ingID}")
	@Consumes(MediaType.APPLICATION_JSON)
	public void removeIngredientCart(@PathParam("userID") String userID, @PathParam("ingID") int ingredientID) {
		ItemCart itemCart = new ItemCart(userID, ingredientID, 1.0);
		listsService.removeIngredientCart(itemCart);
	}
	
	@DELETE // Remove an item to fridge of a user
	@Path("/removefromfridge/{userID}/{ingID}")
	@Consumes(MediaType.APPLICATION_JSON)
	public void removeIngredientFridge(@PathParam("userID") String userID, @PathParam("ingID") int ingredientID) {
		ItemFridge itemFridge = new ItemFridge(userID, ingredientID, 1.0);
		listsService.removeIngredientFridge(itemFridge);
	}
	
	@POST // Add ingredients of Recipe that a user wants to do in the Cart
	@Path("/addcartfromrecipe")
	@Consumes(MediaType.APPLICATION_JSON)
	public void modIngredientCart(List<ItemCart> itemCarts) {
		listsService.modCartForRecipeToMake(itemCarts);
	}
	
}