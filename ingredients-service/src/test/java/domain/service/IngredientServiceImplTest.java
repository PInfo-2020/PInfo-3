package domain.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

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
        List<Ingredient> ingredients = ingredientServiceImpl.getAll();
        int size = ingredients.size();

        Ingredient i = getRandomIngredient();
        Ingredient i2 = getRandomIngredient();
        ingredientServiceImpl.create(i);
        ingredientServiceImpl.create(i2);

        assertEquals(size + 2, ingredientServiceImpl.getAll().size());
    }
	
	
	@Test
	public void testGetById() {
		initDataStore();
		List<Ingredient> ingredients = ingredientServiceImpl.getAll();
		if(ingredients.isEmpty()) {
			ingredients.add(getRandomIngredient());
		}
		int id = ingredients.get(0).getId();
		Ingredient ingredient = ingredientServiceImpl.getById(id);
		assertEquals(ingredients.get(0).getId(), ingredient.getId());
		assertEquals(ingredients.get(0).getName(), ingredient.getName());
	}
	
	@Test
	public void testGetByName() {
		List<Ingredient> ingredients = ingredientServiceImpl.getAll();
		if(ingredients.isEmpty()) {
			ingredients.add(getRandomIngredient());
		}
		ingredientServiceImpl.create(ingredients.get(0));
		String name = ingredients.get(0).getName();
		assertNotNull(ingredientServiceImpl.getByName(ingredients.get(0).getName()));
		assertEquals(name, ingredientServiceImpl.getByName(name).getName());
	}
	
	@Test
	public void testGetUnitByName() {
		List<Ingredient> ingredients = ingredientServiceImpl.getAll();
		if(ingredients.isEmpty()) {
			ingredients.add(getRandomIngredient());
		}
		ingredientServiceImpl.create(ingredients.get(0));
		String name = ingredients.get(0).getName();
		String unit = ingredients.get(0).getUnit();
		assertEquals(unit, ingredientServiceImpl.getUnitByName(name));
	}
	
	@Test
	public void testCreate() {
		Ingredient ingredient = getRandomIngredient();
		ingredientServiceImpl.create(ingredient);
		Ingredient i = em.find(Ingredient.class, ingredient.getId());
		assertTrue(ingredient.equals(i));
		IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class,
				() -> ingredientServiceImpl.create(ingredient),"Ingredient already exists");
		assertTrue(thrown.getMessage().contains("Ingredient already exists"));
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
	public void testExistByName() {
		List<Ingredient> ingredients = ingredientServiceImpl.getAll();
		if(ingredients.isEmpty()) {
			ingredients.add(getRandomIngredient());
		}
		String name = ingredients.get(0).getName();
		ingredientServiceImpl.create(ingredients.get(0));
		assertTrue(ingredientServiceImpl.existByName(name));
		ingredientServiceImpl.delete(ingredients.get(0));
		assertFalse(ingredientServiceImpl.existByName(name));
	}

	private List<Ingredient> getIngredients() {
		List<Ingredient> ingredients = new ArrayList<>();
		long numberOfIngredients = Math.round((Math.random() * 10)) + 1;
		for(int i = 0; i < numberOfIngredients; i++) {
			ingredients.add(getRandomIngredient());
		}
		return ingredients;
	}
	

	private Ingredient getRandomIngredient() {
		Ingredient ingredient = new Ingredient(UUID.randomUUID().toString(), UUID.randomUUID().toString(), 
				ThreadLocalRandom.current().nextInt(0, 2), ThreadLocalRandom.current().nextInt(0, 2));
		return ingredient;
	}
	
	private int initDataStore() {
		int size = ingredientServiceImpl.getAll().size();
		List<Ingredient> ingredients = getIngredients();
		for (Ingredient ingredient : ingredients) {
			ingredientServiceImpl.create(ingredient);
		}
		return size + ingredients.size();
	}
	
	
}
