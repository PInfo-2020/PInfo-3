export class IngredientFridge {
	ingredientId: number;
	quantite: number;
	vegetarien: number;
	vegan: number;

  constructor(ingredientId, quantite, vegetarien, vegan) {
      this.ingredientId = ingredientId;
      this.quantite = quantite;
      this.vegetarien = vegetarien;
      this.vegan = vegan;
  }
}
