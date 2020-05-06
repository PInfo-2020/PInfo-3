package api.rest;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.containsString;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import io.restassured.RestAssured;

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
	   String payload = "{\n" +
	            "  \"id\": \"1000\",\n" +
	            "  \"name\": \"egg\",\n" +
	            "  \"unit\": \"unit\"\n" +
	            "  \"vegetarian\": \"1\"\n" +
	            "  \"vegan\": \"0\"\n" +
	            "}";
		with().body(payload).when().post("/create").then().statusCode(200);
		when().get("/").then().body(containsString("egg"));
	}
	
	@Test
	public void testUpdate() {
	   String payload = "{\n" +
	            "  \"id\": \"1000\",\n" +
	            "  \"name\": \"lemon\",\n" +
	            "  \"unit\": \"unit\"\n" +
	            "  \"vegetarian\": \"1\"\n" +
	            "  \"vegan\": \"1\"\n" +
	            "}";
		with().body(payload).when().put("/update").then().statusCode(200);
		when().get("/").then().body(containsString("lemon"));
	}
	
	@Test
	public void testDelete() {
		when().delete("/delete/1000").then().statusCode(200);
	}
	
}
