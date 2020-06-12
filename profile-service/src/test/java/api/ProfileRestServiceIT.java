package api;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.containsString;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import domain.model.*;

public class ProfileRestServiceIT {

	@BeforeAll
	public static void setup() {
		RestAssured.baseURI = "http://localhost:28080/profiles";
		RestAssured.port = 8080;
	}

	@Test
	public void testGetDataUsers() {
		when().get("/all").then().body(containsString("Roxy"));
	}

	@Test
	public void testGetDataOneUser() {
		when().get("/2").then().body(containsString("Eli"));
	}
	
	@Test
	public void testGetAllPlannedRecipes() {
		when().get("/plannedrecipes").then().body(containsString("6"));
	}
	
	@Test
	public void testGetAllPlannedRecipesFromOneUser() {
		when().get("plannedrecipes/1").then().body(containsString("1"));
	}
	
	@Test
	public void testAddNewPlannedRecipe() {
	   PlannedRecipe pr = new PlannedRecipe(57,"1",32);
	   with().contentType(ContentType.JSON).body(pr).when().request("POST", "/1/32/addNewPlannedRecipe").then().statusCode(204);
	}
	
	@Test
	public void testAddNewUser() {
	   Profile p = new Profile("77","Martine",5);
	   with().contentType(ContentType.JSON).body(p).when().request("POST", "/77/Martine/addNewUser").then().statusCode(200);
	   when().get("/all").then().body(containsString("77"));
	}
	
	@Test
	public void testRemoveFromPlannedRecipe() {
		when().request("DELETE", "/removefromplannedrecipe/1/001").then().statusCode(204);
	}
	
	@Test
	public void testGetBestCooker() {
		when().get("/getBestCooker").then().body(containsString("4"));
	}
	
}