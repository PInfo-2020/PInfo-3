export class Cart {
    userID: string;
    ingredientID: number;
    quantity: number;

    constructor(userID, ingredientID, quantity) {
        this.userID = userID;
	    this.ingredientID = ingredientID;
        this.quantity = quantity;
    }
}