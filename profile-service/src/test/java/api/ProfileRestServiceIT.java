/*package api;


import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.containsString;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import domain.model.PlannedRecipe;
import domain.model.Profile;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class ProfileRestServiceIT {
	//Testes d'integration avec notre ami docker
	//need mvn clean install
	
	@BeforeAll
	public static void setup() {
		RestAssured.baseURI = "http://192.168.99.100:28080/";
		RestAssured.port = 8080;
	}

	@Test
	public void testGetDataUsers() {
		when().get("profiles").then().body(containsString("1"));

	}
	
	@Test
	public void testGetDataOneUser() {
		//when().get("2").then().body();
		

	}
	
	@Test
	public void testGetAllPlannedRecipesFromOneUser() {
		when().get("plannedrecipes/2").then().body(containsString(""));
	}
	
	@Test
	public void testAddNewPlannedRecipe() {
		PlannedRecipe pr = new PlannedRecipe(9,"77",32);
		with().contentType(ContentType.JSON).body(pr).when().request("POST","plannedrecipes/2").then().statusCode(200);
		
	}
	
	@Test
	public void testAddNewUser() {
		Profile p = new Profile("77","Ilyas",6);
		with().contentType(ContentType.JSON).body(p).when().request("POST","addNewUser/77/Ilyas").then().statusCode(200);
		
	}


}*/