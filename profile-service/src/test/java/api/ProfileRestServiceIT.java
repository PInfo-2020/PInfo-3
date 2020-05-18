package api;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.containsString;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.containsString;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import domain.model.*;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;


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
	    PlannedRecipe pr = new PlannedRecipe(57,"1", 32);
	   with().contentType(ContentType.JSON).body(pr).when().request("POST", "57/1/32/addNewPlannedRecipe").then().statusCode(204);
	   when().get("/plannedrecipes").then().body(containsString("32"));
	}
	
	@Test
	public void testAddNewUser() {
	   Profile p = new Profile("77","Martine",5);
	   with().contentType(ContentType.JSON).body(p).when().request("POST", "/77/Martine/addNewUser").then().statusCode(200);
	   when().get("/all").then().body(containsString("77"));
	}
	
//	@Test
//	public void testRemoveOneUser() {
//		with().contentType(ContentType.JSON).when().request("DELETE", "/removeOneUser").then().statusCode(200);
//	}
//	
//	@Test
//	public void testRemoveFromPlannedRecipe() {
//		with().contentType(ContentType.JSON).when().request("DELETE", "/removefromplannedrecipe").then().statusCode(200);
//	}
}