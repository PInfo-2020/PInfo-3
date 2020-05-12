package domain.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Entity
@Table(name="Comment")
@Data
public class Comment implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id")
	private Long id;
	
	@Column(name="recipeId")
	@NotNull
	private Long recipeId;
	
	@Column(name="commentRecipe")
	@NotNull
	private String commentRecipe;
	
	public Comment() {}

	public Comment(@NotNull Long recipeId, @NotNull String commentRecipe) {
		super();
		this.recipeId = recipeId;
		this.commentRecipe = commentRecipe;
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

	public String getCommentRecipe() {
		return commentRecipe;
	}

	public void setCommentRecipe(String commentRecipe) {
		this.commentRecipe = commentRecipe;
	}
	
}

