package domain.service;

import javax.enterprise.context.ApplicationScoped;
import lombok.extern.java.Log;
import javax.enterprise.inject.Default;
import javax.transaction.Transactional;
import java.util.*;
import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import domain.model.*;


@Default
@Transactional
@ApplicationScoped
@Log
public class ProfileServiceImpl implements ProfileService {

    @PersistenceContext(unitName = "ProfilesPU")
    private EntityManager em;

    public ProfileService ps;
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
	
	@Override
	public int addNewPlannedRecipe(PlannedRecipe pr) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<PlannedRecipe> cq = cb.createQuery(PlannedRecipe.class);
		Root<PlannedRecipe> root = cq.from(PlannedRecipe.class);
		cq.select(root);
		Predicate p1 = cb.equal(root.get("usernameID"), pr.getUsernameID());
		Predicate p2 = cb.equal(root.get("recipeID"), pr.getRecipeID());
		Predicate pFinal = cb.and(p1,p2);
		cq.where(pFinal);
		if (em.createQuery(cq).getResultList().size() == 1) {
			//if already in db do nothing
			return 0;
		} else {
			//otherwise add it
			em.persist(pr);
			return 1;
		}
	}

	@Override
	public int addNewUser(Profile p) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Profile> cq = cb.createQuery(Profile.class);
		Root<Profile> root = cq.from(Profile.class);
		cq.select(root);
		Predicate p1 = cb.equal(root.get("usernameID"), p.getUsernameID());
		Predicate p2 = cb.equal(root.get("username"), p.getUsername());
		Predicate pFinal = cb.and(p1,p2);
		cq.where(pFinal);
		if (em.createQuery(cq).getResultList().size() == 1) {
			//if already in db do nothing
			return 0;
		} else {
			//otherwise add it
			p.setScore(0);
			em.persist(p);
			return 1;
		}
	}

	@Override
	public List<PlannedRecipe> getAllPlannedRecipes() {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<PlannedRecipe> criteria = builder.createQuery( PlannedRecipe.class );
		criteria.from(PlannedRecipe.class);
		return (List<PlannedRecipe>)em.createQuery( criteria ).getResultList();
	}

	@Override
	public List<Profile> getTenBest() {
		// TODO Auto-generated method stub
		return null;
	}
	

//	@Override
//	public List<Profile> getTenBest() {
//			// Add some log if you want to be able to debug this mess -_-
//			CriteriaBuilder builder = em.getCriteriaBuilder();
//			CriteriaQuery<Profile> criteria = builder.createQuery(Profile.class);
//			criteria.from(Profile.class);
//			List<Profile> profiles = em.createQuery(criteria).getResultList();
//			Map<Profile, Double> tenBest = new HashMap<Profile, Double>();
//			
//			List<Profile> pr = new ArrayList<Profile>();
//			List<Profile> lp = ps.getDataUsers();
//			for(Profile pp : lp) {
//				usrID.add(pp.getUsernameID());
//				scr.add(pp.get)
//				
//			}
			

		


	

}
