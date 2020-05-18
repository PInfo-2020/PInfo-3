package domain.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;



import javax.persistence.IdClass;

import lombok.Data;

@Data
@Entity
@IdClass(PlannedRecipeID.class)
@Table(name="plannedrecipe")
public class PlannedRecipe implements Serializable {
	
	public PlannedRecipe() {}
	public PlannedRecipe(@NotNull int rowID,@NotNull String usernameID, @NotNull int recipeID) {
		super();
		this.rowID = rowID;
		this.usernameID = usernameID;
		this.recipeID = recipeID;
	}
	
	private static final long serialVersionUID = 1L;

	@Id
	//@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="rowID", unique=true)
	private int rowID;
	
	@Id
	@Column(name="usernameID")
	private String usernameID;
	
	@Id
	@Column(name="recipeID")
	private int recipeID;
	
	public int getRowID() {
		return rowID;
	}
	public void setRowID(int rowID) {
		this.rowID = rowID;
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
