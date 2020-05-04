package domain.service;

import java.util.ArrayList;

import domain.model.ItemCart;
import domain.model.ItemFridge;

public interface ListsService {
	
	public void createItemCart(ItemCart itemCart);

	public void createItemFridge(ItemFridge itemFridge);

	public ArrayList<ItemCart> getAllCart(int userID);
	
	public ArrayList<ItemFridge> getAllFridge(int userID);

	public void modIngredientCart(int userID, int ingredientID, double quantity);
	
	public void modIngredientFridge(int userID, int ingredientID, double quantity);
	
//	public void subIngredientCart(int userID, int ingredientID, double quantity);
//	
//	public void subIngredientFridge(int userID, int ingredientID, double quantity);
	
	public void removeIngredientCart(int userID, int ingredientID);
	
	public void removeIngredientFridge(int userID, int ingredientID);

	public ArrayList<ItemCart> getAllCartTEST();
}