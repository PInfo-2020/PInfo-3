package domain.model;

import java.io.Serializable;
import java.util.Objects;

public class ItemCartID implements Serializable {
	
	public ItemCartID() {}
	
	public ItemCartID(String userID, int ingredientID) {
		this.userID = userID;
		this.ingredientID = ingredientID;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String userID;
	
	private int ingredientID;
	
	@Override
	public boolean equals(Object object) {
		if (object instanceof ItemCartID) {
			ItemCartID itemCartID = (ItemCartID) object;
			if ((this.userID.equals(itemCartID.getUserID())) && (this.ingredientID == itemCartID.getIngredientID())) {
				return true;
			}
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(userID, ingredientID);
	}

	public String getUserID() {
		return userID;
	}

	public int getIngredientID() {
		return ingredientID;
	}

}
