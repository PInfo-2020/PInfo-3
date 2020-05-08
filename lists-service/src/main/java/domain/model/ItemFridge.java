package domain.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@IdClass(ItemFridgeID.class)
@Table(name="ItemFridge")
public class ItemFridge implements Serializable{
	
	public ItemFridge() {}
	
	public ItemFridge(int userID, int ingredientID, double quantity) {
		super();
		this.userID = userID;
		this.ingredientID = ingredientID;
		this.quantity = quantity;
	}
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="userID", nullable=false)
	private int userID;
	
	@Id
	@Column(name="ingredientID", nullable=false)
	private int ingredientID;
	
	@Column(name="quantity", nullable=false)
	private double quantity;

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public int getIngredientID() {
		return ingredientID;
	}

	public void setIngredientID(int ingredientID) {
		this.ingredientID = ingredientID;
	}

	public double getQuantity() {
		return quantity;
	}

	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}
	
	
}