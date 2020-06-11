package domain.model;

import java.io.Serializable;
import java.util.Objects;

public class ItemFridgeID implements Serializable {
	
	public ItemFridgeID() {}
	
	public ItemFridgeID(String userID, int ingredientID) {
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
		if (object instanceof ItemFridgeID) {
			ItemFridgeID itemFridgeID = (ItemFridgeID) object;
			if ((this.userID.equals(itemFridgeID.getUserID())) && (this.ingredientID == itemFridgeID.getIngredientID())) {
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
