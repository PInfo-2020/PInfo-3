package domain.service;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Default;
import javax.transaction.Transactional;
import java.util.*;
import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import domain.model.*;
import lombok.extern.java.Log;

@Default
@Transactional
@ApplicationScoped
@Log
public class ProfileServiceImpl implements ProfileService {

    @PersistenceContext(unitName = "ProfilesPU")
    private EntityManager em;

	@Override
	public List<Profile> getDataUsers() {
		// TODO Auto-generated method stub
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Profile> criteria = builder.createQuery( Profile.class );
		criteria.from(Profile.class);
		return (List<Profile>)em.createQuery( criteria ).getResultList();
	}
	
	@Override
	public ArrayList<Profile> getDataOneUser(String usernameID){
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Profile> cq = cb.createQuery(Profile.class);
		Root<Profile> root = cq.from(Profile.class);
		cq.select(root);
		Predicate p1 = cb.equal(root.get("usernameID"), usernameID);
		cq.where(p1);
		return (ArrayList<Profile>) em.createQuery(cq).getResultList();
	}
    
	@Override
	public ArrayList<PlannedRecipe> getAllPlannedRecipesFromOneUser(String usernameID) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<PlannedRecipe> cq = cb.createQuery(PlannedRecipe.class);
		Root<PlannedRecipe> root = cq.from(PlannedRecipe.class);
		cq.select(root);
		Predicate p1 = cb.equal(root.get("usernameID"), usernameID);
		cq.where(p1);
		return (ArrayList<PlannedRecipe>) em.createQuery(cq).getResultList();
	}

}
