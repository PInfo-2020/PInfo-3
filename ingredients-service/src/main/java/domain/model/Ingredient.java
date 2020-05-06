package domain.model;

// import java.util.ArrayList;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
@Entity
@Table(name = "Ingredients")
public class Ingredient implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "id")
	@NotNull
	private int id;
	
	@Column(name = "name")
	@NotNull
	private String name;
	
	@Column(name = "unit")
	@NotNull
	private String unit;
	
	@Column(name = "vegetarian")
	@NotNull
	private boolean vegetarian;
	
	@Column(name = "vegan")
	@NotNull
	private boolean vegan;
	
	public Ingredient() {}
	
	/*
	public Ingredient(int id, String name, String unit, boolean vegetarian, boolean vegan) {
		this.id = id;
		this.name = name;
		this.unit = unit;
		this.vegetarian = vegetarian;
		this.vegan = vegan;
	}
	*/
	
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getUnit() {
		return unit;
	}
	
	public void setUnit(String unit) {
		this.unit = unit;
	}

	public boolean getVegetarian() {
		return vegetarian;
	}
	
	public void setVegetarian(boolean vegetarian) {
		this.vegetarian = vegetarian;
	}
	
	public boolean getVegan() {
		return vegan;
	}
	
	public void setVegan(boolean vegan) {
		this.vegan = vegan;
	}
	
}
