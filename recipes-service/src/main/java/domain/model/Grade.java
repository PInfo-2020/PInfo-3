package domain.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Entity
@Table(name="Grade")
@Data
public class Grade implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id")
	private Long id;
	
	@Column(name="recipeId")
	@NotNull
	private Long recipeId;
	
	@Column(name="userId")
	@NotNull
	private String userId;
	
	@Column(name="gradeRecipe")
	@NotNull
	private int gradeRecipe;
	
	public Grade() {}

	public Grade(@NotNull Long recipeId, @NotNull String userId, @NotNull int gradeRecipe) {
		super();
		this.recipeId = recipeId;
		this.userId = userId;
		this.gradeRecipe = gradeRecipe;
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
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getGradeRecipe() {
		return gradeRecipe;
	}

	public void setGradeRecipe(int gradeRecipe) {
		this.gradeRecipe = gradeRecipe;
	}
	
}
