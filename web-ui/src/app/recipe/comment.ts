export class Comment {
  id: number;
	recipeId: number;
	commentRecipe: string;

  constructor(recipeId, commentRecipe) {
    this.recipeId = recipeId;
  	this.commentRecipe = commentRecipe;
  }
}
