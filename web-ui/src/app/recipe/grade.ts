export class Grade {
	recipeId: number;
	userId: string;
	gradeRecipe: number;

  constructor(recipeId, userId, gradeRecipe) {
    this.recipeId = recipeId;
  	this.userId = userId;
  	this.gradeRecipe = gradeRecipe;
  }
}
