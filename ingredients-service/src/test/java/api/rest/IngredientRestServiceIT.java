package api.rest;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.containsString;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import domain.model.Ingredient;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;


public class IngredientRestServiceIT {

	@BeforeAll
	public static void setup() {
		RestAssured.baseURI = "http://localhost:28080/ingredients";
		RestAssured.port = 8080;
	}

	@Test
	public void testGetAll() {
		when().get("/").then().body(containsString("sugar"));
	}

	@Test
	public void testGetById() {
		when().get("/id/3").then().body(containsString("chicken"));
	}
	
	@Test
	public void testGetByName() {
		when().get("/name/avocado").then().body(containsString("unit"));
	}
	
	@Test
	public void testGetUnitByName() {
		when().get("/unit/tomato").then().body(containsString("kg"));
	}
	
	@Test
	public void testCreate() {
	   Ingredient ingredient = new Ingredient(1000, "egg", "unit", 1, 0);
	   with().contentType(ContentType.JSON).body(ingredient).when().request("POST", "/create").then().statusCode(200);
	   when().get("/").then().body(containsString("egg"));
	}
	
	@Test
	public void testUpdate() {
	   Ingredient ingredient = new Ingredient(1, "lemon", "unit", 1, 0);
	   with().contentType(ContentType.JSON).body(ingredient).when().request("PUT", "/update").then().statusCode(200);
	}
	
	@Test
	public void testDelete() {
		with().contentType(ContentType.JSON).when().request("DELETE", "/delete/4").then().statusCode(200);
	}
	
}
