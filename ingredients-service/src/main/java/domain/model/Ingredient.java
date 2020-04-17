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
	/*
	@Column(name = "type")
	private ArrayList<Boolean> type;
	
	public Ingredient() {}
	
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

	public ArrayList<Boolean> getType() {
		return type;
	}
	public void setType(ArrayList<Boolean> type) {
		this.type = type;
	}
	*/
	
}
