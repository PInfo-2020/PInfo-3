export class ItemCart {
    userID: string;
    ingredientID: number;
    quantity: number;

    constructor(userID, ingredientID, quantity) {
        this.userID = userID;
	      this.ingredientID = ingredientID;
        this.quantity = quantity;
    }
}
