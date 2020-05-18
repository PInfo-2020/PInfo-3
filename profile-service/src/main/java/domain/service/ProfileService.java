package domain.service;

import java.util.*;
import domain.model.*;


public interface ProfileService {
	
	public List<Profile> getDataUsers();

	public Profile getDataOneUser(String usernameID);
	
	public ArrayList<PlannedRecipe> getAllPlannedRecipesFromOneUser(String usernameID);
	
	public void addNewPlannedRecipe(PlannedRecipe pr);
	
	public int addNewUser(Profile p);

	public List<PlannedRecipe> getAllPlannedRecipes();

	public void removeOneUser(Profile p);
	
	public void removeOnePlannedRecipe(PlannedRecipe pr);
	
	public void updateScore(Score s);

}