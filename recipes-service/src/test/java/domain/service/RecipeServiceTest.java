//package domain.service;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//
//import java.util.List;
//import java.util.UUID;
//import java.util.concurrent.ThreadLocalRandom;
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
//import domain.model.Comment;
//import domain.model.Grade;
//import domain.model.Ingredient;
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
//	@Test
//	void testGetAll() {
//		List<Recipe> recipes = recipeServiceImpl.getAll();
//		int size = recipes.size();
//		long sizeL = (long) size;
//		
//		recipeServiceImpl.create(getRandomRecipe(), sizeL + 1);
//		recipeServiceImpl.create(getRandomRecipe(), sizeL + 2);
//		recipeServiceImpl.create(getRandomRecipe(), sizeL + 3);
//		recipeServiceImpl.create(getRandomRecipe(), sizeL + 4);
//		
//		assertEquals(size + 4, recipeServiceImpl.getAll().size());
//	}
//	
//	@Test
//	void testCreate() {
//		List<Recipe> recipes = recipeServiceImpl.getAll();
//		int size = recipes.size();
//		long sizeL = (long) size;
//		Recipe recipe = getRandomRecipe();
//		recipeServiceImpl.create(recipe, sizeL + 1);
//		assertNotNull(recipe.getId());
//	}
//	
//	@Test
//	void testGet() {
//		List<Recipe> recipes = recipeServiceImpl.getAll();
//		int size = recipes.size();
//		long sizeL = (long) size;
//		
//		recipeServiceImpl.create(getRandomRecipe(), sizeL + 1);
//		recipeServiceImpl.create(getRandomRecipe(), sizeL + 2);
//		
//		assertNotNull(recipeServiceImpl.get((long) 2));
//	}
//	
//	@Test
//	void testGetByName() {
//		List<Recipe> recipes = recipeServiceImpl.getAll();
//		int size = recipes.size();
//		long sizeL = (long) size;
//		Recipe recipe = getRandomRecipe();
//		
//		recipeServiceImpl.create(recipe, sizeL + 1);
//		recipeServiceImpl.create(getRandomRecipe(), sizeL + 2);
//		
//		assertNotNull(recipeServiceImpl.getByName(recipe.getName()));
//	}
//	
//	@Test
//	void testGetAllComments() {
//		recipeServiceImpl.create(getRandomRecipe(),(long) 0);
//		List<Comment> comments = recipeServiceImpl.getAllComments((long) 1);
//		int size = comments.size();
//		long sizeL = (long) recipeServiceImpl.countComment();
//		long nbRecipes = (long) recipeServiceImpl.count();
//		Comment comment = new Comment((long) 1, "Delicieux");
//		Comment comment2 = new Comment((long) 1, "Delicieux");
//		
//		recipeServiceImpl.addComment(comment, sizeL + 1, nbRecipes);
//		recipeServiceImpl.addComment(comment2, sizeL + 2, nbRecipes);
//		
//		assertEquals(size + 2, recipeServiceImpl.getAllComments((long) 1).size());
//	}
//	
//	@Test
//	void testAddComment() {
//		recipeServiceImpl.create(getRandomRecipe(),(long) 0);
//		long sizeL = (long) recipeServiceImpl.countComment();
//		long nbRecipes = (long) recipeServiceImpl.count();
//		Comment comment = new Comment((long) 1, "Delicieux");
//		
//		recipeServiceImpl.addComment(comment, sizeL + 1, nbRecipes);
//		
//		assertNotNull(comment.getId());
//	}
//	
//	@Test
//	void testGetAllGrades() {
//		recipeServiceImpl.create(getRandomRecipe(),(long) 0);
//		List<Grade> grades = recipeServiceImpl.getAllGrades("0");
//		int size = grades.size();
//		long sizeL = (long) recipeServiceImpl.countGrade();
//		long nbRecipes = (long) recipeServiceImpl.count();
//		Grade grade = new Grade((long) 1, "0", 5);
//		Grade grade2 = new Grade((long) 1, "0", 5);
//		
//		recipeServiceImpl.addGrade(grade, sizeL + 1, nbRecipes, grades);
//		recipeServiceImpl.addGrade(grade2, sizeL + 2, nbRecipes, grades);
//		
//		assertEquals(size + 2, recipeServiceImpl.getAllGrades("0").size());
//	}
//	
//	@Test
//	void testAddGrade() {
//		recipeServiceImpl.create(getRandomRecipe(),(long) 0);
//		List<Grade> grades = recipeServiceImpl.getAllGrades("0");
//		long sizeL = (long) recipeServiceImpl.countGrade();
//		long nbRecipes = (long) recipeServiceImpl.count();
//		Grade grade  = new Grade((long) 1, "0", 5);
//		
//		recipeServiceImpl.addGrade(grade, sizeL + 1, nbRecipes, grades);
//		
//		assertNotNull(grade.getId());
//	}
//	
//	@Test
//	void testGetGrade() {
//		recipeServiceImpl.create(getRandomRecipe(),(long) 0);
//		List<Grade> grades = recipeServiceImpl.getAllGrades("0");
//		long sizeL = (long) recipeServiceImpl.countGrade();
//		long nbRecipes = (long) recipeServiceImpl.count();
//		Grade grade = new Grade((long) 1, "0", 5);
//		Grade grade2 = new Grade((long) 1, "0", 4);
//		
//		recipeServiceImpl.addGrade(grade, sizeL + 1, nbRecipes, grades);
//		recipeServiceImpl.addGrade(grade2, sizeL + 2, nbRecipes, grades);
//		
//		assertEquals(4.5, recipeServiceImpl.getGrade((long) 1));
//	}
//	
//	@Test
//	void testGetAllIngredients() {
//		recipeServiceImpl.create(getRandomRecipe(),(long) 0);
//		List<Ingredient> ingredients = recipeServiceImpl.getAllIngredients((long) 1);
//		int size = ingredients.size();
//		long sizeL = (long) recipeServiceImpl.countIngredient();
//		Ingredient ingredient = new Ingredient((long) 1, 8.5,0,0);
//		Ingredient ingredient2 = new Ingredient((long) 2, 3.2,0,0);
//		
//		recipeServiceImpl.createIngredient(ingredient, (long) 1, sizeL + 1);
//		recipeServiceImpl.createIngredient(ingredient2, (long) 1, sizeL + 2);
//		
//		assertEquals(size + 2, recipeServiceImpl.getAllIngredients((long) 1).size());
//	}
//	
//	@Test
//	void testCreateIngredient() {
//		recipeServiceImpl.create(getRandomRecipe(),(long) 0);
//		long sizeL = (long) recipeServiceImpl.countIngredient();
//		Ingredient ingredient = new Ingredient((long) 1, 8.5,0,0);
//		
//		recipeServiceImpl.createIngredient(ingredient, (long) 1, sizeL + 1);
//		
//		assertNotNull(ingredient.getId());
//	}
//	
//	@Test
//	void testGetBestRecipes() {
//		List<Recipe> recipes = recipeServiceImpl.getAll();
//		long sizeL = (long) recipes.size();
//		Recipe recipe = getRandomRecipe();
//		Recipe recipe2 = getRandomRecipe();
//		Recipe recipe3 = getRandomRecipe();
//		Recipe recipe4 = getRandomRecipe();
//		
//		recipeServiceImpl.create(recipe, sizeL + 1);
//		recipeServiceImpl.create(recipe2, sizeL + 2);
//		recipeServiceImpl.create(recipe3, sizeL + 3);
//		recipeServiceImpl.create(recipe4, sizeL + 4);
//		
//		List<Grade> grades = recipeServiceImpl.getAllGrades("0");
//		long sizeL2 = (long) recipeServiceImpl.countGrade();
//		long nbRecipes = (long) recipeServiceImpl.count();
//		Grade grade  = new Grade((long) 1, "0", 5);
//		Grade grade2  = new Grade((long) 2, "0", 2);
//		Grade grade3  = new Grade((long) 3, "0", 4);
//		Grade grade4  = new Grade((long) 4, "0", 6);
//		
//		recipeServiceImpl.addGrade(grade, sizeL2 + 1, nbRecipes, grades);
//		recipeServiceImpl.addGrade(grade2, sizeL2 + 2, nbRecipes, grades);
//		recipeServiceImpl.addGrade(grade3, sizeL2 + 3, nbRecipes, grades);
//		recipeServiceImpl.addGrade(grade4, sizeL2 + 4, nbRecipes, grades);
//		
//		assertEquals(3, recipeServiceImpl.getBestRecipes().size());
//		assertTrue(recipeServiceImpl.getBestRecipes().contains(recipe));
//		assertTrue(recipeServiceImpl.getBestRecipes().contains(recipe3));
//		assertTrue(recipeServiceImpl.getBestRecipes().contains(recipe4));
//	}
//	
//	@Test
//	void testGetUserGrade() {
//		List<Recipe> recipes = recipeServiceImpl.getAll();
//		long sizeL = (long) recipes.size();
//		Recipe recipe = new Recipe("n", "d", "i", 10, 1, "2");
//		Recipe recipe2 = new Recipe("n2", "d2", "i2", 10, 1, "2");
//		Recipe recipe3 = new Recipe("n3", "d3", "i3", 10, 1, "3");
//		
//		recipeServiceImpl.create(recipe, sizeL + 1);
//		recipeServiceImpl.create(recipe2, sizeL + 2);
//		recipeServiceImpl.create(recipe3, sizeL + 3);
//		
//		List<Grade> grades = recipeServiceImpl.getAllGrades("0");
//		long sizeL2 = (long) recipeServiceImpl.countGrade();
//		long nbRecipes = (long) recipeServiceImpl.count();
//		Grade grade = new Grade((long) 1, "0", 5);
//		Grade grade2 = new Grade((long) 2, "0", 2);
//		
//		recipeServiceImpl.addGrade(grade, sizeL2 + 1, nbRecipes, grades);
//		recipeServiceImpl.addGrade(grade2, sizeL2 + 2, nbRecipes, grades);
//		
//		assertEquals(3.5, recipeServiceImpl.getUserGrade("2"));
//	}
//	
//	private Recipe getRandomRecipe() {
//		Recipe recipe = new Recipe(UUID.randomUUID().toString(), UUID.randomUUID().toString(),
//				UUID.randomUUID().toString(), ThreadLocalRandom.current().nextInt(0, 500), ThreadLocalRandom.current().nextInt(0, 500),
//				UUID.randomUUID().toString());
//		return recipe;
//	}
//	
//
//}
