package domain.model;
import java.io.Serializable;
import java.util.Objects;

public class PlannedRecipeID implements Serializable {
	
	public PlannedRecipeID() {}
	
	public PlannedRecipeID(String usernameID, int recipeID) {
		this.usernameID = usernameID;
		this.recipeID = recipeID;
	}

	private static final long serialVersionUID = 1L;
		
	private String usernameID;

	private int recipeID;
	
	@Override
	public boolean equals(Object object) {
		if (object instanceof PlannedRecipeID) {
			PlannedRecipeID prID = (PlannedRecipeID) object;
			if ((this.usernameID == prID.getUsernameID()) && (this.recipeID == prID.getRecipeID())) {
				return true;
			}
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(usernameID, recipeID);
	}

	public String getUsernameID() {
		return usernameID;
	}
	public int getRecipeID() {
		return recipeID;
	}

}