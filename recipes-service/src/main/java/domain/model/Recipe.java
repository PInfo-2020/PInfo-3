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
	
	@Column(name="description")
	@NotNull
	private String description;
	
	@Column(name="instructions")
	@NotNull
	private String instructions;
	
	@Column(name="minutes")
	@NotNull
	private int minutes;
	
	@Column(name="personnes")
	@NotNull
	private int personnes;
	
	@Column(name="userId")
	@NotNull
	private Long userId;
	
	public Recipe() {}

	public Recipe(@NotNull String name, @NotNull String description, @NotNull String instructions, @NotNull int minutes, @NotNull int personnes, @NotNull Long userId) {
		super();
		this.name = name;
		this.description = description;
		this.instructions = instructions;
		this.minutes = minutes;
		this.personnes = personnes;
		this.userId = userId;
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
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getInstructions() {
		return instructions;
	}

	public void setInstructions(String instructions) {
		this.instructions = instructions;
	}
	
	public int getMinutes() {
		return minutes;
	}

	public void setMinutes(int minutes) {
		this.minutes = minutes;
	}
	
	public int getPersonnes() {
		return personnes;
	}

	public void setPersonnes(int personnes) {
		this.personnes = personnes;
	}
	
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
}
