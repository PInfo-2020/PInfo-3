package domain.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import domain.model.Ingredient;
import eu.drus.jpa.unit.api.JpaUnit;

@ExtendWith(JpaUnit.class)
@ExtendWith(MockitoExtension.class)
public class IngredientServiceImplTest {
	
	@Spy
	@PersistenceContext(unitName = "IngredientsPUTest")
	EntityManager em;

	@InjectMocks
	private IngredientServiceImpl ingredientServiceImpl;

	@Test
	public void testGetAll() {
		int size = initDataStore();
		assertEquals(size, ingredientServiceImpl.getAll().size());
	}
	
	
	@Test
	public void testGet() {
		initDataStore();
		List<Ingredient> ingredients = ingredientServiceImpl.getAll();
		if (ingredients.isEmpty()) {
			initDataStore();
		}
		int id = ingredients.get(0).getId();
		Ingredient ingredient = ingredientServiceImpl.get(id);

		assertEquals(ingredients.get(0).getId(), ingredient.getId());
		assertEquals(ingredients.get(0).getName(), ingredient.getName());
	}
	
	@Test
	public void testCreate() {
		Ingredient ingredient = getRandomIngredient();
		ingredientServiceImpl.create(ingredient);

		Ingredient i = em.find(Ingredient.class, ingredient.getId());

		assertTrue(ingredient.equals(i));
		IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class,
				() -> ingredientServiceImpl.create(ingredient),"ingredient already exists");
		assertTrue(thrown.getMessage().contains("ingredient already exists"));
	}

	
	@Test
	public void testDelete() {

		Ingredient ingredient = getRandomIngredient();
		ingredientServiceImpl.create(ingredient);
		ingredientServiceImpl.delete(ingredient);
		
		Ingredient i = em.find(Ingredient.class, ingredient.getId());

		assertTrue(i == null);
	}

	@Test
	public void testUpdate() {

		Ingredient ing1 = getRandomIngredient();
		ingredientServiceImpl.create(ing1);
		Ingredient ing2 = getRandomIngredient();
		ing2.setId(ing1.getId());

		ingredientServiceImpl.update(ing2);

		Ingredient i = em.find(Ingredient.class, ing1.getId());
		assertTrue(ing1.equals(i));
		
		Ingredient ingNull = null;
		
		IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class,
				() -> ingredientServiceImpl.update(ingNull), "ingredient does not exist");
		assertTrue(thrown.getMessage().contains("ingredient does not exist"));
	}

	private List<Ingredient> getIngredients() {

		List<Ingredient> ingredients = new ArrayList<>();
		long numberOfIngredients = Math.round((Math.random() * 10)) + 1;
		for (int i = 0; i < numberOfIngredients; i++) {
			ingredients.add(getRandomIngredient());
		}
		return ingredients;
	}

	private int initDataStore() {
		int size = ingredientServiceImpl.getAll().size();
		List<Ingredient> ingredients = getIngredients();
		for (Ingredient ingredient : ingredients) {
			ingredientServiceImpl.create(ingredient);
		}
		return size + ingredients.size();
	}

	private Ingredient getRandomIngredient() {
		Ingredient ingredient = new Ingredient();
		
		Random r = new Random();
		ingredient.setId(r.nextInt(200));
		ingredient.setName(UUID.randomUUID().toString());
		ingredient.setUnit(UUID.randomUUID().toString());

		return ingredient;
	}
	
}