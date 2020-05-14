package api;

import java.util.List;
import java.util.ArrayList;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import domain.model.*;
import domain.service.*;
import io.swagger.annotations.ApiOperation;

@ApplicationScoped
public class ProfileRestService {
	
	@Inject
	private ProfileService ps;

	@GET
	@Path("/profiles")
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
	@Path("/plannedrecipes/{usernameID}")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<PlannedRecipe> getAllPlannedRecipesFromOneUser(@PathParam("usernameID") String usernameID) {
		return ps.getAllPlannedRecipesFromOneUser(usernameID);
	}
	
	
	
}