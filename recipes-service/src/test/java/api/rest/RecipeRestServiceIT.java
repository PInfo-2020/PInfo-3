//package api.rest;
//
//
//import org.junit.jupiter.api.BeforeAll;
//import io.restassured.RestAssured;
//
//public class RecipeRestServiceIT {
//
//	@BeforeAll
//	public static void setup() {
//		RestAssured.baseURI = "http://localhost:28080/recipe";
//		RestAssured.port = 8080;
//	}
//
////	@Test
////	public void testGetAll() {
////		when().get("/").then().body(containsString("254900LAW6SKNVPBBN21"));
////	}
////
////	@Test
////	public void testGet() {
////		when().get("/1").then().body(containsString("254900LAW6SKNVPBBN21"));
////	}
////	
////	@Test
////	public void testCount() {
////		when().get("/count").then().body(containsString("10"));
////	}
//
//}