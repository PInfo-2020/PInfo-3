package domain.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
		listsServiceImpl.createItemCart(new ItemCart(1,1,1));
		listsServiceImpl.createItemCart(new ItemCart(2,2,2));
		assertEquals(1, listsServiceImpl.getAllCart(1).size());
	}

	@Test
	void testgetAllFridge() {
		listsServiceImpl.createItemFridge(new ItemFridge(1,1,1));
		listsServiceImpl.createItemFridge(new ItemFridge(1,2,2));
		listsServiceImpl.createItemFridge(new ItemFridge(2,1,1));
		listsServiceImpl.createItemFridge(new ItemFridge(2,2,2));
		assertEquals(2, listsServiceImpl.getAllFridge(1).size());
	}
	
	@Test
	void testmodIngredientCart() {
		listsServiceImpl.createItemCart(new ItemCart(1,1,1));
		listsServiceImpl.modIngredientCart(1, 1, 4);

		assertEquals(4, listsServiceImpl.getAllCart(1).get(0).getQuantity());
	}
	
	@Test
	void testmodIngredientFridge() {
		listsServiceImpl.createItemFridge(new ItemFridge(2,2,2));
		listsServiceImpl.modIngredientFridge(1, 1, 10);

		assertEquals(10, listsServiceImpl.getAllFridge(1).get(0).getQuantity());
	}
	
	@Test
	void testremoveIngredientCart() {
		listsServiceImpl.createItemCart(new ItemCart(1,1,1));
		listsServiceImpl.createItemCart(new ItemCart(1,2,2));
		listsServiceImpl.removeIngredientCart(1, 1);

		assertEquals(1, listsServiceImpl.getAllCart(1).size());
	}
	
	@Test
	void testremoveIngredientFridge() {
		listsServiceImpl.createItemFridge(new ItemFridge(2,2,2));
		listsServiceImpl.createItemFridge(new ItemFridge(2,1,1));
		listsServiceImpl.removeIngredientFridge(2, 1);

		assertEquals(1, listsServiceImpl.getAllFridge(2).size());
	}
	
	
//	@Test
//	void testGetAllCartTEST() {
//		listsServiceImpl.createItemCart(new ItemCart(1,1,1));
//		listsServiceImpl.createItemCart(new ItemCart(1,2,2));
//		listsServiceImpl.createItemCart(new ItemCart(2,1,1));
//		listsServiceImpl.createItemCart(new ItemCart(2,2,2));
//		listsServiceImpl.modIngredientCart(3, 1, 4);
//		listsServiceImpl.removeIngredientCart(1, 1);
//		ArrayList<ItemCart> listItem = listsServiceImpl.getAllCartTEST();
//		for (ItemCart i : listItem) {
//			System.out.println("userID: " + i.getUserID() + " ingredientID: " + i.getIngredientID() + " quantity: " + i.getQuantity());
//		}
//		
//		assertEquals(4, listItem.size());
//	}
}
