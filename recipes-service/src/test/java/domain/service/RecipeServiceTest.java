package domain.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import domain.model.Comment;
import domain.model.Fridge;
import domain.model.Grade;
import domain.model.Ingredient;
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
	void testGetAll() {
		List<Recipe> recipes = recipeServiceImpl.getAll();
		int size = recipes.size();
		long sizeL = (long) size;
		
		recipeServiceImpl.create(getRandomRecipe(), sizeL + 1);
		recipeServiceImpl.create(getRandomRecipe(), sizeL + 2);
		recipeServiceImpl.create(getRandomRecipe(), sizeL + 3);
		recipeServiceImpl.create(getRandomRecipe(), sizeL + 4);
		
		assertEquals(size + 4, recipeServiceImpl.getAll().size());
	}
	
	@Test
	void testCreate() {
		List<Recipe> recipes = recipeServiceImpl.getAll();
		int size = recipes.size();
		long sizeL = (long) size;
		Recipe recipe = getRandomRecipe();
		recipeServiceImpl.create(recipe, sizeL + 1);
		assertNotNull(recipe.getId());
	}
	
	@Test
	void testGet() {
		List<Recipe> recipes = recipeServiceImpl.getAll();
		int size = recipes.size();
		long sizeL = (long) size;
		
		recipeServiceImpl.create(getRandomRecipe(), sizeL + 1);
		recipeServiceImpl.create(getRandomRecipe(), sizeL + 2);
		
		assertNotNull(recipeServiceImpl.get((long) 2));
	}
	
	@Test
	void testGetByName() {
		List<Recipe> recipes = recipeServiceImpl.getAll();
		int size = recipes.size();
		long sizeL = (long) size;
		Recipe recipe = getRandomRecipe();
		
		recipeServiceImpl.create(recipe, sizeL + 1);
		recipeServiceImpl.create(getRandomRecipe(), sizeL + 2);
		
		assertNotNull(recipeServiceImpl.getByName(recipe.getName()));
	}
	
	@Test
	void testGetAllComments() {
		recipeServiceImpl.create(getRandomRecipe(),(long) 0);
		List<Comment> comments = recipeServiceImpl.getAllComments((long) 1);
		int size = comments.size();
		long sizeL = (long) recipeServiceImpl.countComment();
		long nbRecipes = (long) recipeServiceImpl.count();
		Comment comment = new Comment((long) 1, "Delicieux");
		Comment comment2 = new Comment((long) 1, "Delicieux");
		
		recipeServiceImpl.addComment(comment, sizeL + 1, nbRecipes);
		recipeServiceImpl.addComment(comment2, sizeL + 2, nbRecipes);
		
		assertEquals(size + 2, recipeServiceImpl.getAllComments((long) 1).size());
	}
	
	@Test
	void testAddComment() {
		recipeServiceImpl.create(getRandomRecipe(),(long) 0);
		long sizeL = (long) recipeServiceImpl.countComment();
		long nbRecipes = (long) recipeServiceImpl.count();
		Comment comment = new Comment((long) 1, "Delicieux");
		
		recipeServiceImpl.addComment(comment, sizeL + 1, nbRecipes);
		
		assertNotNull(comment.getId());
	}
	
	@Test
	void testGetAllGrades() {
		recipeServiceImpl.create(getRandomRecipe(),(long) 0);
		List<Grade> grades = recipeServiceImpl.getAllGrades("0");
		int size = grades.size();
		long sizeL = (long) recipeServiceImpl.countGrade();
		Grade grade = new Grade((long) 1, "0", 5);
		Grade grade2 = new Grade((long) 1, "0", 5);
		
		recipeServiceImpl.addGrade(grade, sizeL + 1, grades);
		recipeServiceImpl.addGrade(grade2, sizeL + 2, grades);
		
		assertEquals(size + 2, recipeServiceImpl.getAllGrades("0").size());
	}
	
	@Test
	void testAddGrade() {
		recipeServiceImpl.create(getRandomRecipe(),(long) 0);
		List<Grade> grades = recipeServiceImpl.getAllGrades("0");
		long sizeL = (long) recipeServiceImpl.countGrade();
		Grade grade  = new Grade((long) 1, "0", 5);
		
		recipeServiceImpl.addGrade(grade, sizeL + 1, grades);
		
		assertEquals(5, recipeServiceImpl.getAllGrades("0").get(0).getGradeRecipe());
		
		List<Grade> grades2 = recipeServiceImpl.getAllGrades("0");
		Grade grade2  = new Grade((long) 1, "0", 6);
		
		recipeServiceImpl.addGrade(grade2, sizeL + 2, grades2);
		
		assertEquals(6, recipeServiceImpl.getAllGrades("0").get(0).getGradeRecipe());
		assertEquals(1, recipeServiceImpl.getAllGrades("0").size());
	}
	
	@Test
	void testGetGrade() {
		recipeServiceImpl.create(getRandomRecipe(),(long) 0);
		List<Grade> grades = recipeServiceImpl.getAllGrades("0");
		long sizeL = (long) recipeServiceImpl.countGrade();
		Grade grade = new Grade((long) 1, "0", 5);
		Grade grade2 = new Grade((long) 1, "0", 4);
		
		recipeServiceImpl.addGrade(grade, sizeL + 1, grades);
		recipeServiceImpl.addGrade(grade2, sizeL + 2, grades);
		
		assertEquals(4.5, recipeServiceImpl.getGrade((long) 1));
	}
	
	@Test
	void testGetAllIngredients() {
		recipeServiceImpl.create(getRandomRecipe(),(long) 0);
		List<Ingredient> ingredients = recipeServiceImpl.getAllIngredients((long) 1);
		int size = ingredients.size();
		long sizeL = (long) recipeServiceImpl.countIngredient();
		Ingredient ingredient = new Ingredient((long) 1, 8.5,0,0);
		Ingredient ingredient2 = new Ingredient((long) 2, 3.2,0,0);
		
		recipeServiceImpl.createIngredient(ingredient, (long) 1, sizeL + 1);
		recipeServiceImpl.createIngredient(ingredient2, (long) 1, sizeL + 2);
		
		assertEquals(size + 2, recipeServiceImpl.getAllIngredients((long) 1).size());
	}
	
	@Test
	void testCreateIngredient() {
		recipeServiceImpl.create(getRandomRecipe(),(long) 0);
		long sizeL = (long) recipeServiceImpl.countIngredient();
		Ingredient ingredient = new Ingredient((long) 1, 8.5,0,0);
		
		recipeServiceImpl.createIngredient(ingredient, (long) 1, sizeL + 1);
		
		assertNotNull(ingredient.getId());
	}
	
	@Test
	void testGetUserGrade() {
		List<Recipe> recipes = recipeServiceImpl.getAll();
		long sizeL = (long) recipes.size();
		Recipe recipe = new Recipe("n", "d", "i", 10, 1, "2");
		Recipe recipe2 = new Recipe("n2", "d2", "i2", 10, 1, "2");
		Recipe recipe3 = new Recipe("n3", "d3", "i3", 10, 1, "3");
		
		recipeServiceImpl.create(recipe, sizeL + 1);
		recipeServiceImpl.create(recipe2, sizeL + 2);
		recipeServiceImpl.create(recipe3, sizeL + 3);
		
		List<Grade> grades = recipeServiceImpl.getAllGrades("0");
		long sizeL2 = (long) recipeServiceImpl.countGrade();
		Grade grade = new Grade((long) 1, "0", 5);
		Grade grade2 = new Grade((long) 2, "0", 2);
		
		recipeServiceImpl.addGrade(grade, sizeL2 + 1, grades);
		recipeServiceImpl.addGrade(grade2, sizeL2 + 2, grades);
		
		assertEquals(3.5, recipeServiceImpl.getUserGrade("2"));
	}
	
	@Test
	void testGetBestRecipes() {
		Recipe recipe = new Recipe("n", "d", "i", 10, 1, "1");
		Recipe recipe2 = new Recipe("n2", "d2", "i2", 10, 1, "2");
		Recipe recipe3 = new Recipe("n3", "d3", "i3", 10, 1, "3");
		Recipe recipe4 = new Recipe("n4", "d4", "i4", 10, 1, "4");
		Recipe recipe5 = new Recipe("n5", "d5", "i5", 10, 1, "5");
		Recipe recipe6 = new Recipe("n6", "d6", "i6", 10, 1, "6");
		Recipe recipe7 = new Recipe("n7", "d7", "i7", 10, 1, "7");
		Recipe recipe8 = new Recipe("n8", "d8", "i8", 10, 1, "8");
		Recipe recipe9 = new Recipe("n9", "d9", "i9", 10, 1, "9");
		
		recipeServiceImpl.create(recipe,(long) 0);
		recipeServiceImpl.create(recipe2,(long) 1);
		recipeServiceImpl.create(recipe3,(long) 2);
		recipeServiceImpl.create(recipe4,(long) 3);
		recipeServiceImpl.create(recipe5,(long) 4);
		recipeServiceImpl.create(recipe6,(long) 5);
		recipeServiceImpl.create(recipe7,(long) 6);
		recipeServiceImpl.create(recipe8,(long) 7);
		recipeServiceImpl.create(recipe9,(long) 8);
		
		List<Grade> grades = recipeServiceImpl.getAllGrades("0");
		long sizeL = (long) recipeServiceImpl.countGrade();
		Grade grade = new Grade((long) 1, "0", 2);
		Grade grade2 = new Grade((long) 2, "0", 4);
		Grade grade3 = new Grade((long) 3, "0", 6);
		Grade grade4 = new Grade((long) 4, "0", 1);
		Grade grade5 = new Grade((long) 5, "0", 5);
		Grade grade6 = new Grade((long) 6, "0", 6);
		Grade grade7 = new Grade((long) 7, "0", 3);
		Grade grade8 = new Grade((long) 8, "0", 4);
		Grade grade9 = new Grade((long) 9, "0", 5);
		
		recipeServiceImpl.addGrade(grade, sizeL + 1, grades);
		recipeServiceImpl.addGrade(grade2, sizeL + 2, grades);
		recipeServiceImpl.addGrade(grade3, sizeL + 3, grades);
		recipeServiceImpl.addGrade(grade4, sizeL + 4, grades);
		recipeServiceImpl.addGrade(grade5, sizeL + 5, grades);
		recipeServiceImpl.addGrade(grade6, sizeL + 6, grades);
		recipeServiceImpl.addGrade(grade7, sizeL + 7, grades);
		recipeServiceImpl.addGrade(grade8, sizeL + 8, grades);
		recipeServiceImpl.addGrade(grade9, sizeL + 9, grades);
		
		assertEquals(6, recipeServiceImpl.getGrade(recipeServiceImpl.getBestRecipes().get(0).getId()));
		assertEquals(6, recipeServiceImpl.getGrade(recipeServiceImpl.getBestRecipes().get(1).getId()));
		assertEquals(5, recipeServiceImpl.getGrade(recipeServiceImpl.getBestRecipes().get(2).getId()));
	
	}
	
	@Test
	void testGetRecipesByFridge() {
	    HashMap<Integer,Double> ing = new HashMap<Integer,Double>();
	    ing.put(1, 8.5);
	    ing.put(2, 6.6);
	    Fridge fridge = new Fridge(ing);
	    
	    Recipe recipe = new Recipe("n", "d", "i", 10, 1, "1");
		Recipe recipe2 = new Recipe("n2", "d2", "i2", 10, 1, "2");
		recipeServiceImpl.create(recipe,(long) 0);
		recipeServiceImpl.create(recipe2,(long) 1);
		
		Ingredient ingredient = new Ingredient((long) 1, 7.5,1,0);
		Ingredient ingredient2 = new Ingredient((long) 2, 4.5,1,0);
		Ingredient ingredient3 = new Ingredient((long) 3, 8.5,0,0);
		
		recipeServiceImpl.createIngredient(ingredient,(long) 0,(long) 0);
		recipeServiceImpl.createIngredient(ingredient2,(long) 0,(long) 1);
		recipeServiceImpl.createIngredient(ingredient3,(long) 1,(long) 2);
		
		assertEquals(1, recipeServiceImpl.getRecipesByFridge(fridge).size());
		assertEquals("n", recipeServiceImpl.getRecipesByFridge(fridge).get(0).getName());
	}
	
	@Test
	void testGetByVegetarien() {
		Recipe recipe = new Recipe("n", "d", "i", 10, 1, "1");
		Recipe recipe2 = new Recipe("n2", "d2", "i2", 10, 1, "2");
		recipeServiceImpl.create(recipe,(long) 0);
		recipeServiceImpl.create(recipe2,(long) 1);
		
		Ingredient ingredient = new Ingredient((long) 1, 8.5,1,0);
		Ingredient ingredient2 = new Ingredient((long) 2, 8.5,1,0);
		Ingredient ingredient3 = new Ingredient((long) 3, 8.5,0,0);
		
		recipeServiceImpl.createIngredient(ingredient,(long) 0,(long) 0);
		recipeServiceImpl.createIngredient(ingredient2,(long) 0,(long) 1);
		recipeServiceImpl.createIngredient(ingredient3,(long) 1,(long) 2);
		
		assertEquals(1, recipeServiceImpl.getByVegetarien().size());
		assertEquals("n", recipeServiceImpl.getByVegetarien().get(0).getName());
		
	}
	
	@Test
	void testGetByVegan() {
		Recipe recipe = new Recipe("n", "d", "i", 10, 1, "1");
		Recipe recipe2 = new Recipe("n2", "d2", "i2", 10, 1, "2");
		recipeServiceImpl.create(recipe,(long) 0);
		recipeServiceImpl.create(recipe2,(long) 1);
		
		Ingredient ingredient = new Ingredient((long) 1, 8.5,0,1);
		Ingredient ingredient2 = new Ingredient((long) 2, 8.5,0,1);
		Ingredient ingredient3 = new Ingredient((long) 3, 8.5,0,0);
		
		recipeServiceImpl.createIngredient(ingredient,(long) 0,(long) 0);
		recipeServiceImpl.createIngredient(ingredient2,(long) 0,(long) 1);
		recipeServiceImpl.createIngredient(ingredient3,(long) 1,(long) 2);
		
		assertEquals(1, recipeServiceImpl.getByVegan().size());
		assertEquals("n", recipeServiceImpl.getByVegan().get(0).getName());
		
	}
	
	@Test
	void testGetCreatedRecipes() {
		Recipe recipe = new Recipe("n", "d", "i", 10, 1, "1");
		Recipe recipe2 = new Recipe("n2", "d2", "i2", 10, 1, "1");
		Recipe recipe3 = new Recipe("n3", "d3", "i3", 10, 1, "2");
		recipeServiceImpl.create(recipe,(long) 0);
		recipeServiceImpl.create(recipe2,(long) 1);
		recipeServiceImpl.create(recipe3,(long) 2);
		
		assertEquals(2, recipeServiceImpl.getCreatedRecipes("1").size());
		assertEquals("n", recipeServiceImpl.getCreatedRecipes("1").get(0).getName());
		assertEquals("n2", recipeServiceImpl.getCreatedRecipes("1").get(1).getName());
		
	}
	
	private Recipe getRandomRecipe() {
		Recipe recipe = new Recipe(UUID.randomUUID().toString(), UUID.randomUUID().toString(),
				UUID.randomUUID().toString(), ThreadLocalRandom.current().nextInt(0, 500), ThreadLocalRandom.current().nextInt(0, 500),
				UUID.randomUUID().toString());
		return recipe;
	}
	

}
