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
 		when().get("/").then().body(containsString("Apple"));
 	}

 	@Test
 	public void testGetById() {
 		when().get("/id/3").then().body(containsString("Apple"));
 	}

 	@Test
 	public void testGetByName() {
 		when().get("/name/Apple").then().body(containsString("Apple"));
 	}

 	@Test
 	public void testGetUnitByName() {
 		when().get("/unit/Apple").then().body(containsString("kg"));
 	}
	
	@Test
	public void testDelete() {
		with().contentType(ContentType.JSON).when().request("DELETE", "/delete/4").then().statusCode(200);
	}
	
}
