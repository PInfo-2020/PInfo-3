package domain.service;

import java.util.ArrayList;
import java.util.HashMap;

import domain.model.ItemCart;
import domain.model.ItemFridge;

public interface ListsService {
	
	public void createItemCart(ItemCart itemCart);

	public void createItemFridge(ItemFridge itemFridge);

	public ArrayList<ItemCart> getAllCart(int userID);
	
	public ArrayList<ItemFridge> getAllFridge(int userID);

	public void modIngredientCart(ItemCart itemCart);
	
	public void modIngredientFridge(ItemFridge itemFridge);
	
	public void removeIngredientCart(ItemCart itemCart);
	
	public void removeIngredientFridge(ItemFridge itemFridge);

//	public ArrayList<ItemCart> getAllCartTEST();

	public HashMap<Integer, Double> getAllFridgeRecipe(int userID);
}