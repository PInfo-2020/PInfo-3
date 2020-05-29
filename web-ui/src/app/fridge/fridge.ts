export class Fridge {
    userID: string;
    ingredientID: number;
    quantity: number;

    constructor(userID, ingredientID, quantity) {
        this.userID = userID;
	    this.ingredientID = ingredientID;
        this.quantity = quantity;
    }
}