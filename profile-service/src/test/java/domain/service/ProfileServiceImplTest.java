package domain.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Default;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;


import domain.model.PlannedRecipe;
import domain.model.PlannedRecipeID;
import domain.model.Profile;
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
		ArrayList<Profile> p = psi.getDataOneUser("777");
		assertEquals("777",p.get(0).getUsernameID());
		
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

/*	void removeOneUser(Profile p) {
		
	}
	
	void removeOnePlannedRecipe(PlannedRecipe pr) {
		
	}*/
	
/*	@Test
	void testGetAll() {
		int size = initDataStore();
		assertEquals(size, counterpartyServiceImpl.getAll().size());
	}

	@Test
	void testGet() {
		initDataStore();
		List<Counterparty> counterparties = counterpartyServiceImpl.getAll();
		String lei = counterparties.get(0).getLei();
		Counterparty cpty= counterpartyServiceImpl.get(lei);
		assertEquals(counterparties.get(0).getLei(), cpty.getLei());
		assertEquals(counterparties.get(0).getLegalAddress(), cpty.getLegalAddress());
	}
	
	@Test
	void testCount() {
		long size = initDataStore();
		long count = counterpartyServiceImpl.count();
		assertEquals(size, count);
	}

	private List<Counterparty> getCounterparties() {

		List<Counterparty> counterparties = new ArrayList<>();
		long numberOfCpty = Math.round((Math.random() * 10)) + 5;
		for (int i = 0; i < numberOfCpty; i++) {
			counterparties.add(getRandomCounterparty());
		}
		return counterparties;

	}

	private int initDataStore() {
		int size = counterpartyServiceImpl.getAll().size();
		List<Counterparty> counterparties = getCounterparties();	
		for (Counterparty c : counterparties) {
			em.persist(c);
		}
		return size + counterparties.size();
	}


	private Counterparty getRandomCounterparty() {
		Counterparty c = new Counterparty();
		c.setLei(UUID.randomUUID().toString());
		c.setName(UUID.randomUUID().toString());
		c.setStatus(STATUS.ACTIVE);

		Address a = new Address();
		a.setFirstAddressLine(UUID.randomUUID().toString());
		a.setCity(UUID.randomUUID().toString());
		a.setRegion(UUID.randomUUID().toString());
		a.setCountry(UUID.randomUUID().toString());
		a.setPostalCode(UUID.randomUUID().toString());

		Registration r = new Registration();
		r.setRegistrationAuthorityEntityID(UUID.randomUUID().toString());
		r.setRegistrationAuthorityID(UUID.randomUUID().toString());
		r.setJurisdiction(UUID.randomUUID().toString());

		c.setLegalAddress(a);
		c.setRegistration(r);

		return c;
	}*/
}
