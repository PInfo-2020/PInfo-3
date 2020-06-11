package domain.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import domain.model.ItemCart;
import domain.model.ItemFridge;
import eu.drus.jpa.unit.api.JpaUnit;

@ExtendWith(JpaUnit.class)
@ExtendWith(MockitoExtension.class)
class ListsServiceImplTest {

	@Spy
	@PersistenceContext(unitName = "ListsPUTest")
	EntityManager em;

	@InjectMocks
	private ListsServiceImpl listsServiceImpl;

	@Test
	void testgetAllCart() {
		listsServiceImpl.createItemCart(new ItemCart("1",1,1));
		listsServiceImpl.createItemCart(new ItemCart("2",2,2));
		assertEquals(1, listsServiceImpl.getAllCart("1").size());
	}

	@Test
	void testgetAllFridge() {
		listsServiceImpl.createItemFridge(new ItemFridge("1",1,1));
		listsServiceImpl.createItemFridge(new ItemFridge("1",2,2));
		listsServiceImpl.createItemFridge(new ItemFridge("2",1,1));
		listsServiceImpl.createItemFridge(new ItemFridge("2",2,2));
		assertEquals(2, listsServiceImpl.getAllFridge("1").size());
	}
	
	@Test
	void testmodIngredientCart() {
		listsServiceImpl.createItemCart(new ItemCart("1",1,1));
		listsServiceImpl.modIngredientCart(new ItemCart("1",1,4));

		assertEquals(4, listsServiceImpl.getAllCart("1").get(0).getQuantity());
	}
	
	@Test
	void testmodIngredientFridge() {
		listsServiceImpl.createItemFridge(new ItemFridge("2",2,2));
		listsServiceImpl.modIngredientFridge(new ItemFridge("1", 1, 10));

		assertEquals(10, listsServiceImpl.getAllFridge("1").get(0).getQuantity());
	}
	
	@Test
	void testremoveIngredientCart() {
		ItemCart i1 = new ItemCart("1",1,1);
		ItemCart i2 = new ItemCart("1",2,2);
		listsServiceImpl.createItemCart(i1);
		listsServiceImpl.createItemCart(i2);
		listsServiceImpl.removeIngredientCart(i1);

		assertEquals(1, listsServiceImpl.getAllCart("1").size());
	}
	
	@Test
	void testremoveIngredientFridge() {
		ItemFridge i1 = new ItemFridge("2",2,2);
		ItemFridge i2 = new ItemFridge("2",1,1);
		listsServiceImpl.createItemFridge(i1);
		listsServiceImpl.createItemFridge(i2);
		listsServiceImpl.removeIngredientFridge(i2);

		assertEquals(1, listsServiceImpl.getAllFridge("2").size());
	}
	
	@Test
	void testGetAllFridgeRecipe() {
		ItemFridge i1 = new ItemFridge("2",2,2);
		ItemFridge i2 = new ItemFridge("2",1,1);
		listsServiceImpl.createItemFridge(i1);
		listsServiceImpl.createItemFridge(i2);
		
		HashMap<Integer, Double> hash = listsServiceImpl.getAllFridgeRecipe("2");
		assertEquals(2, hash.get(2));
	}
	
	@Test
	public void testModCartForRecipeToMake() {
		ItemFridge i1 = new ItemFridge("2",2,2);
		ItemFridge i3 = new ItemFridge("2",8,6);
		listsServiceImpl.createItemFridge(i1);
		listsServiceImpl.createItemFridge(i3);
		ItemCart i2 = new ItemCart("2",2,2);
		listsServiceImpl.createItemCart(i2);
		
		ItemCart m1 = new ItemCart("2",2,4);
		ItemCart m2 = new ItemCart("2",3,4);
		ItemCart m3 = new ItemCart("2",8,8);
		List<ItemCart> carts = new ArrayList<ItemCart>();
		carts.add(m1);
		carts.add(m2);
		carts.add(m3);
		listsServiceImpl.modCartForRecipeToMake(carts);
		
		double quantity1 = 0;
		double quantity2 = 0;
		double quantity3 = 0;
		for (ItemCart i : listsServiceImpl.getAllCart("2")) {
			if (2 == i.getIngredientID()) {
				quantity1 = i.getQuantity();
			}
			if (3 == i.getIngredientID()) {
				quantity2 = i.getQuantity();
			}
			if (8 == i.getIngredientID()) {
				quantity3 = i.getQuantity();
			}
		}
		
		assertEquals(4, quantity2);
		assertEquals(6, quantity1);
		assertEquals(2, quantity3);
		
	}
	
}
