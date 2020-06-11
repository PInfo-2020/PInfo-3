package domain.model;

// import java.util.ArrayList;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.persistence.SequenceGenerator;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import lombok.Data;

@Data
@Entity
@Table(name = "Ingredients")
public class Ingredient implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name = "seq_id", sequenceName = "seq_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_id")	
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
	private int vegetarian;
	
	@Column(name = "vegan")
	@NotNull
	private int vegan;
	
	public Ingredient() {}
	
	
	public Ingredient(String name, String unit, int vegetarian, int vegan) {
		this.name = name;
		this.unit = unit;
		this.vegetarian = vegetarian;
		this.vegan = vegan;
	}
	
}
