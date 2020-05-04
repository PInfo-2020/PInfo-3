package api;

import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import domain.model.ItemCart;
import domain.service.ListsService;

@ApplicationScoped
@Path("/lists")
public class ListsServiceRestService {

	@Inject
	private ListsService listsService;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<ItemCart> getAllCart(int userID) {
		return listsService.getAllCart(userID);
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public void createItemCart(ItemCart itemCart) {
		listsService.createItemCart(itemCart);
	}
}