package domain.service;

import java.util.*;
import domain.model.*;


public interface ProfileService {
	
	public List<Profile> getDataUsers();

	public ArrayList<Profile> getDataOneUser(String usernameID);
	
	public ArrayList<PlannedRecipe> getAllPlannedRecipesFromOneUser(String usernameID);
}