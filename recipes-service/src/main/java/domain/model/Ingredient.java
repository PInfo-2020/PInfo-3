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
	
	@Column(name="vegetarien")
	@NotNull
	private int vegetarien;
	
	@Column(name="vegan")
	@NotNull
	private int vegan;
	
	public Ingredient() {}

	public Ingredient(@NotNull Long ingredientId, @NotNull double quantite, @NotNull int vegetarien, @NotNull int vegan) {
		super();
		this.ingredientId = ingredientId;
		this.quantite = quantite;
		this.vegetarien = vegetarien;
		this.vegan = vegan;
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

	public void setQuantite(float quantite) {
		this.quantite = quantite;
	}
	
	public int getVegetarien() {
		return vegetarien;
	}

	public void setVegetarien(int vegetarien) {
		this.vegetarien = vegetarien;
	}
	
	public int getVegan() {
		return vegan;
	}

	public void setVegan(int vegan) {
		this.vegan = vegan;
	}

	
}
