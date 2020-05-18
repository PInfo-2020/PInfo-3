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
		PlannedRecipe pr = new PlannedRecipe(369,"1",255);
		psi.addNewPlannedRecipe(pr);
		assertEquals(size +1, psi.getAllPlannedRecipes().size());		
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
		PlannedRecipe pr = new PlannedRecipe(25,"RVP",363);
		psi.addNewPlannedRecipe(pr);
		assertEquals(size +1, psi.getAllPlannedRecipes().size());
	}

}
