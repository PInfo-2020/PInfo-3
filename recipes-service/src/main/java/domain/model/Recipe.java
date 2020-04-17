package domain.model;



import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Entity
@Table(name="Recipe")
@Data
public class Recipe implements Serializable {

	private static final long serialVersionUID = -8677180318520117547L;

	@Id
	@Column(name="id")
	private Long id;
	
	@Column(name="name")
	@NotNull
	private String name;
	
	public Recipe() {}

	public Recipe(@NotNull String name) {
		super();
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
}
