package domain.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Entity
@Table(name="Ingredient")
@Data
public class Ingredient implements Serializable {

	private static final long serialVersionUID = 1L;;

	@Id
	@Column(name="id")
	private Long id;
	
	@Column(name="recipeId")
	@NotNull
	private Long recipeId;
	
	@Column(name="ingredientId")
	@NotNull
	private Long ingredientId;
	
	@Column(name="quantite")
	@NotNull
	private double quantite;
	
	public Ingredient() {}

	public Ingredient(@NotNull Long ingredientId, @NotNull double quantite) {
		super();
		this.ingredientId = ingredientId;
		this.quantite = quantite;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getRecipeId() {
		return recipeId;
	}

	public void setRecipeId(Long recipeId) {
		this.recipeId = recipeId;
	}
	
	public Long getIngredientId() {
		return ingredientId;
	}

	public void setIngredientId(Long ingredientId) {
		this.ingredientId = ingredientId;
	}

	public double getQuantite() {
		return quantite;
	}

	public void setQuantite(double quantite) {
		this.quantite = quantite;
	}

	
}
