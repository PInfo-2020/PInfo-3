//package domain.service;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//
//import java.util.List;
//import java.util.UUID;
//
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Spy;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import domain.model.Recipe;
//import eu.drus.jpa.unit.api.JpaUnit;
//
//@ExtendWith(JpaUnit.class)
//@ExtendWith(MockitoExtension.class)
//public class RecipeServiceTest {
//	@Spy
//	@PersistenceContext(unitName = "RecipePUTest")
//	EntityManager em;
//
//	@InjectMocks
//	private RecipeServiceImpl recipeServiceImpl;
//
////	@Test
////	void testGetAll() {
////		List<Recipe> recipes = recipeServiceImpl.getAll();
////		int size = recipes.size();
////		
////		recipeServiceImpl.create(getRandomRecipe());
////		recipeServiceImpl.create(getRandomRecipe());
////		recipeServiceImpl.create(getRandomRecipe());
////		recipeServiceImpl.create(getRandomRecipe());
////		
////		assertEquals(size + 4, recipeServiceImpl.getAll().size());
////	}
////	
////	@Test
////	void testCreate() {
////		Recipe recipe = getRandomRecipe();
////		recipeServiceImpl.create(recipe);
////		assertNotNull(recipe.getId());
////	}
////	
////	private Recipe getRandomRecipe() {
////		Recipe recipe = new Recipe(UUID.randomUUID().toString());
////		return recipe;
////	}
//
//}
