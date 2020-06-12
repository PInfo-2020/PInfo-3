package domain.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import domain.model.*;

import eu.drus.jpa.unit.api.JpaUnit;

@ExtendWith(JpaUnit.class)
@ExtendWith(MockitoExtension.class)
class ProfileServiceImplTest {
	//Testes unitaires sans docker blabla
	// Run with Junit only

	@Spy
	@PersistenceContext(unitName = "ProfilesPUTest")
	EntityManager em;

	@InjectMocks
	private ProfileServiceImpl psi;
	
	@Test
	public void getDataUsers() {
		int size = psi.getDataUsers().size();
		Profile pr = new Profile("521","Heidi",3);
		psi.addNewUser(pr);
		assertEquals(size+1,psi.getDataUsers().size());
			
	}

	@Test
	public void getDataOneUser() {
		Profile pr = new Profile("777","Kays",6);
		psi.addNewUser(pr);
		assertNotNull(psi.getDataOneUser("777"));
		Profile p = psi.getDataOneUser("777");
		assertEquals("777",p.getUsernameID());
		
	}
	
	@Test
	void getAllPlannedRecipesFromOneUser() {
		Profile p = new Profile("212","Leila",5);
		psi.addNewUser(p);
		PlannedRecipe pr = new PlannedRecipe(18,"212",321);
		psi.addNewPlannedRecipe(pr);
		ArrayList<PlannedRecipe> prs = psi.getAllPlannedRecipesFromOneUser("212");
		assertEquals(321,prs.get(0).getRecipeID());
		
	}
	
	@Test
	void addNewPlannedRecipe() {
		int size = psi.getAllPlannedRecipes().size();
		PlannedRecipe pr = new PlannedRecipe(size+1,"1",255);
		PlannedRecipe pr2 = new PlannedRecipe(size+2,"8",255);
		psi.addNewPlannedRecipe(pr);
		psi.addNewPlannedRecipe(pr2);
		assertEquals(size +2, psi.getAllPlannedRecipes().size());		
	}
	
	@Test
	void addNewUser(){
		int size = psi.getDataUsers().size();
		Profile p = new Profile("007","Maya",6);
		psi.addNewUser(p);
		assertEquals(size +1,psi.getDataUsers().size());
	}

	@Test
	void getAllPlannedRecipes() {
		int size = psi.getAllPlannedRecipes().size();
		PlannedRecipe pr = new PlannedRecipe(size+1,"RVP",363);
		psi.addNewPlannedRecipe(pr);
		assertEquals(size +1, psi.getAllPlannedRecipes().size());
	}
	
	@Test
	void testGetBestCooker() {
		Profile p1 = new Profile("1","bb",5);
		Profile p2 = new Profile("2","bwb",4);
		Profile p3 = new Profile("3","beb",3);
		Profile p4 = new Profile("4","eb",2);
		Profile p5 = new Profile("5","bb",5);
		Profile p6 = new Profile("6","bwb",4);
		Profile p7 = new Profile("7","beb",3);
		Profile p8 = new Profile("8","eb",2);
		Profile p9 = new Profile("9","bb",5);
		Profile p10 = new Profile("10","bwb",4);
		Profile p11= new Profile("11","beb",3);
		Profile p12 = new Profile("12","eb",2);
		psi.addNewUser(p1);
		psi.addNewUser(p2);
		psi.addNewUser(p3);
		psi.addNewUser(p4);
		psi.addNewUser(p5);
		psi.addNewUser(p6);
		psi.addNewUser(p7);
		psi.addNewUser(p8);
		psi.addNewUser(p9);
		psi.addNewUser(p10);
		psi.addNewUser(p11);
		psi.addNewUser(p12);
		assertEquals(6, psi.getBestCooker().size());
		assertEquals(5, psi.getBestCooker().get(0).getScore());
		assertEquals(5, psi.getBestCooker().get(1).getScore());
		assertEquals(5, psi.getBestCooker().get(2).getScore());
		assertEquals(4, psi.getBestCooker().get(3).getScore());
		assertEquals(4, psi.getBestCooker().get(4).getScore());
		assertEquals(4, psi.getBestCooker().get(5).getScore());
	}
	
	@Test
	void testRemoveOnePlannedRecipe() {
		int size = psi.getAllPlannedRecipes().size();
		PlannedRecipe pr1 = new PlannedRecipe(size+1,"RVP3",364);
		PlannedRecipe pr2 = new PlannedRecipe(size+2,"RVP2",362);
		psi.addNewPlannedRecipe(pr1);
		psi.addNewPlannedRecipe(pr2);
		psi.removeOnePlannedRecipe(pr1.getUsernameID(), pr1.getRecipeID());
		assertEquals(size+1, psi.getAllPlannedRecipes().size());
	}
	
	@Test
	void updateScore() {
		Profile p = new Profile("212","Leila",5);
		psi.addNewUser(p);
		Score s = new Score("212", 6.0);
		psi.updateScore(s);
		assertEquals(6.0, psi.getDataOneUser("212").getScore());
	}

}
