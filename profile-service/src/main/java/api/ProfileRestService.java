package api;

import java.util.List;
import java.util.ArrayList;

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

import domain.model.*;
import domain.service.*;
import io.swagger.annotations.ApiOperation;

@ApplicationScoped
@Path("/profiles")
public class ProfileRestService {

	@Inject
	private ProfileService ps;

	@GET
	@Path("/all")
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Get all profiles")
	public List<Profile>  getDataUsers(){
		return ps.getDataUsers();
	}
	
	@GET
	@Path("/{usernameID}")
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Get data by given usernameID")
	public ArrayList<Profile> getDataOneUser(@PathParam("usernameID") String usernameID){
		return ps.getDataOneUser(usernameID);
	}
	
	@GET
	@Path("/plannedrecipes")
	@Produces(MediaType.APPLICATION_JSON)
	public List<PlannedRecipe> getAllPlannedRecipes() {
		return ps.getAllPlannedRecipes();
	}
	
	@GET
	@Path("plannedrecipes/{usernameID}")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<PlannedRecipe> getAllPlannedRecipesFromOneUser(@PathParam("usernameID") String usernameID) {
		return ps.getAllPlannedRecipesFromOneUser(usernameID);
	}
		
	@POST
	@Path("{usernameID}/{recipeID}/{rowID}/addNewPlannedRecipe")
	@Consumes(MediaType.APPLICATION_JSON)
	public void addNewPlannedRecipe(@PathParam("rowID") int rowID, @PathParam("usernameID") String usernameID, @PathParam("recipeID") int recipeID) {
		PlannedRecipe pr = new PlannedRecipe(rowID,usernameID,recipeID);
		ps.addNewPlannedRecipe(pr);
	}
	
	@POST
	@Path("{usernameID}/{username}/addNewUser")
	@Consumes(MediaType.APPLICATION_JSON)
	public int addNewUser(@PathParam("usernameID") String usernameID, @PathParam("username") String username) {
		Profile p = new Profile(usernameID,username,0);
		return ps.addNewUser(p);		
	}
	
//	@DELETE // Remove an item to cart of a user
//	@Path("/removeOneUser")
//	@Consumes(MediaType.APPLICATION_JSON)
//	public void removeOneUser(Profile p) {
//		ps.removeOneUser(p);
//	}
//	
//	@DELETE // Remove an item to fridge of a user
//	@Path("/removefromplannedrecipe")
//	@Consumes(MediaType.APPLICATION_JSON)
//	public void removeOnePlannedRecipe(PlannedRecipe pr) {
//		ps.removeOnePlannedRecipe(pr);
//	}

}