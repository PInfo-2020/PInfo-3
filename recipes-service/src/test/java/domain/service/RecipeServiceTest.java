package domain.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import domain.model.Recipe;
import eu.drus.jpa.unit.api.JpaUnit;

@ExtendWith(JpaUnit.class)
@ExtendWith(MockitoExtension.class)
public class RecipeServiceTest {
	@Spy
	@PersistenceContext(unitName = "RecipePUTest")
	EntityManager em;

	@InjectMocks
	private RecipeServiceImpl recipeServiceImpl;

	@Test
	public void testGetAll() {
		int size = initDataStore();
		assertEquals(size, recipeServiceImpl.getAll().size());
	}
	

	private int initDataStore() {
		em.clear();
		List<Recipe> recipes;
		Recipe recipe1 = new Recipe("curry");
		Recipe recipe2 = new Recipe("hamburger");
		recipeServiceImpl.create(recipe1);
		recipeServiceImpl.create(recipe2);
		recipes = recipeServiceImpl.getAll();
		int size = recipes.size();
		return size;
	}

}
