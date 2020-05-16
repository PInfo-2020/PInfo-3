package domain.service;

import java.util.*;
import domain.model.*;


public interface ProfileService {
	
	public List<Profile> getDataUsers();

	public ArrayList<Profile> getDataOneUser(String usernameID);
	
	public ArrayList<PlannedRecipe> getAllPlannedRecipesFromOneUser(String usernameID);
	
	public int addNewPlannedRecipe(PlannedRecipe pr);
	
	public int addNewUser(Profile p);

	public List<PlannedRecipe> getAllPlannedRecipes();

	public List<Profile> getTenBest();

	public void removeOneUser(Profile p);
	
	public void removeOnePlannedRecipe(PlannedRecipe pr);

}