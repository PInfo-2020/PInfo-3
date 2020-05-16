/*package domain.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
	@PersistenceContext(unitName = "ProfilesPU")
	EntityManager em;

	@InjectMocks
	private ProfileServiceImpl psi;

	@Test
	void getDataUsers() {
		List<Profile> profiles = psi.getDataUsers();
		int nbrOfRowBefore = profiles.size();
		int nbrOfRowAfter = 0;
		//Add new profiles in the db
		Profile p = new Profile("8","Sannji",5);
		psi.addNewUser(p);
		nbrOfRowAfter = profiles.size();
		assertEquals(nbrOfRowBefore +1, nbrOfRowAfter);
		//Verify that a row has efficiently been added
	}
	

	
//	@Test
/*	void  getDataOneUser() {
		//based on the usernameID
		List<Profile> profile =  psi.getDataUsers();
		int nbrOfRowBefore = profile.size();
		int nbrofRowAfter = 0;
		
	}
	
	@Test
	void getAllPlannedRecipesFromOneUser() {
		
	}
	
	@Test
	void addNewPlannedRecipe() {
		
	}
	
	
	
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
	}
}*/
