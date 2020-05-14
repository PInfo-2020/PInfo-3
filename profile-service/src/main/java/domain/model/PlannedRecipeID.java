package domain.model;
import java.io.Serializable;
import java.util.Objects;

public class PlannedRecipeID implements Serializable {
	
	public PlannedRecipeID() {}
	
	public PlannedRecipeID(int rowID, String usernameID, int recipeID) {
		this.rowID = rowID;
		this.usernameID = usernameID;
		this.recipeID = recipeID;
	}

	private static final long serialVersionUID = 1L;
	
	private int rowID;
	
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

	public void setUsernameID(String usernameID) {
		this.usernameID = usernameID;
	}

	public int getRecipeID() {
		return recipeID;
	}

	public void setRecipeID(int recipeID) {
		this.recipeID = recipeID;
	}
}