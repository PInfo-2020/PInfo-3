package api;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import domain.model.ItemCart;
import domain.model.ItemFridge;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class ListsRestServiceIT {

	@BeforeAll
	public static void setup() {
		RestAssured.baseURI = "http://localhost:28080/lists";
		RestAssured.port = 8080;
	}

	@Test
	public void testGetAllCart() {
		when().get("/cart/1").then().body(containsString("1"));
	}
		
	@Test
	public void testGetAllFridge() {
		when().get("/fridge/3").then().body(containsString("3"));
	}

	@Test
	public void testModIngredientCart() {
		ItemCart itemCart = new ItemCart(3,3,4);
		with().contentType(ContentType.JSON).body(itemCart).when().request("POST", "/addtocart").then().statusCode(204);
	}
	
	@Test
	public void testModIngredientFridge() {
		ItemFridge itemFridge = new ItemFridge(3,3,4);
		with().contentType(ContentType.JSON).body(itemFridge).when().request("POST", "/addtofridge").then().statusCode(204);
	}
	
	@Test
	public void testRemoveIngredientCart() {
		ItemCart itemCart = new ItemCart(3,3,4);
		with().contentType(ContentType.JSON).body(itemCart).when().request("DELETE", "/removefromcart").then().statusCode(204);
	}
	
	@Test
	public void testRemoveIngredientFridge() {
		ItemFridge itemFridge = new ItemFridge(3,3,4);
		with().contentType(ContentType.JSON).body(itemFridge).when().request("DELETE", "/removefromfridge").then().statusCode(204);
	}
	
}
	
	