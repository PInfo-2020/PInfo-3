package api.rest;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import domain.model.Comment;
import domain.model.Grade;
import domain.model.Ingredient;
import domain.model.Recipe;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class RecipeRestServiceIT {

	@BeforeAll
	public static void setup() {
		RestAssured.baseURI = "http://localhost:28080/recipe";
		RestAssured.port = 8080;
	}

	@Test
	public void testGetAll() {
		when().get("/").then().body(containsString("nom1"));
	}
	
	@Test
	public void testCreate() {
		Recipe recipe = new Recipe("nom4", "d4", "i4", 10, 1, "3");
		with().contentType(ContentType.JSON).body(recipe).when().request("POST", "/").then().statusCode(200);
	}
	
	@Test
	public void testGet() {
		when().get("/1").then().body(containsString("nom1"));
	}
	
	@Test
	public void testGetByName() {
		when().get("/name/nom3").then().body(containsString("nom3"));
	}
	
	@Test
	public void testCount() {
		when().get("/count").then().body(containsString("3"));
	}
	
	@Test
	public void testCountIngredient() {
		when().get("/countIngredient").then().body(containsString("3"));
	}
	
	@Test
	public void testCountComment() {
		when().get("/countComment").then().body(containsString("3"));
	}
	
	@Test
	public void testGetAllComments() {
		when().get("/3/comment").then().body(containsString("bon"));
	}
	
	@Test
	public void testCountGrade() {
		when().get("/countGrade").then().body(containsString("3"));
	}
	
	
	@Test
	public void testGetAllGrades() {
		when().get("/user/10/grades").then().body(containsString("5"));
	}
	
	@Test
	public void testGetGrade() {
		when().get("/3/grade").then().body(containsString("4.5"));
	}
	
	
	@Test
	public void testGetAllIngredients() {
		when().get("/3/ingredient").then().body(containsString("7"));
	}
	
	@Test
	public void testGetBestRecipes() {
		when().get("/top").then().body(containsString("nom1"));
	}
	
	@Test
	public void testAddComment() {
		Comment comment = new Comment((long) 1, "Delicieux");
		with().contentType(ContentType.JSON).body(comment).when().request("POST", "/comment").then().statusCode(204);
	}
	
	@Test
	public void testAddGrade() {
		Grade grade = new Grade((long) 1, "0", 5);
		with().contentType(ContentType.JSON).body(grade).when().request("POST", "/grade").then().statusCode(204);
	}
	
	@Test
	public void testCreateIngredient() {
		Ingredient ingredient = new Ingredient((long) 1, 10.0,0,0);
		List<Ingredient> ingredients = new ArrayList<Ingredient>();
		ingredients.add(ingredient);
		with().contentType(ContentType.JSON).body(ingredients).when().request("POST", "/1/addingredients").then().statusCode(204);
	}
	
	@Test
	public void testGetUserGrade() {
		when().get("/user/1/grade").then().body(containsString("0"));
	}
	

}