package domain.service;

import javax.enterprise.context.ApplicationScoped;
import lombok.extern.java.Log;
import javax.enterprise.inject.Default;
import javax.transaction.Transactional;

import org.wildfly.swarm.config.Logging;

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
    // making the following var public was a reason for thorntail crash : amazing

	@Override
	public List<Profile> getDataUsers() {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Profile> criteria = builder.createQuery( Profile.class );
		criteria.from(Profile.class);
		return em.createQuery( criteria ).getResultList();
	}
	
	@Override
	public Profile getDataOneUser(String usernameID){
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Profile> cq = cb.createQuery(Profile.class);
		Root<Profile> root = cq.from(Profile.class);
		cq.select(root);
		Predicate p1 = cb.equal(root.get("usernameID"), usernameID);
		cq.where(p1);
		return  em.createQuery(cq).getSingleResult();
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
	public void addNewPlannedRecipe(PlannedRecipe pr) {
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
		} else {
			//otherwise add it
			em.persist(pr);
		}
	}

	@Override
	public int addNewUser(Profile p) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Profile> cq = cb.createQuery(Profile.class);
		Root<Profile> root = cq.from(Profile.class);
		cq.select(root);
		Predicate p1 = cb.equal(root.get("usernameID"), p.getUsernameID());
		cq.where(p1);
		if (em.createQuery(cq).getResultList().size() == 1) {
			//if already in db do nothing
			return 0;
		} else {
			//otherwise add it
			em.persist(p);
			return 1;
		}
	}

	@Override
	public List<PlannedRecipe> getAllPlannedRecipes() {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<PlannedRecipe> criteria = builder.createQuery(PlannedRecipe.class);
		criteria.from(PlannedRecipe.class);
		return em.createQuery(criteria).getResultList();
	}

	@Override
	public void removeOnePlannedRecipe(String usernameID, int recipeID) {
		PlannedRecipeID pk = new PlannedRecipeID(usernameID, recipeID);
		PlannedRecipe pr = em.find(PlannedRecipe.class, pk);
		em.remove(pr);
	}

	@Override
	public void updateScore(Score s) {
		//s.getUsernameID();
		//profile(usernameID,username,score);
		//changer getDataOneUser() to return profile
		Profile p = this.getDataOneUser(s.getUsernameID());
	
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Profile> cq = cb.createQuery(Profile.class);
		Root<Profile> root = cq.from(Profile.class);
		cq.select(root);
		Predicate p1 = cb.equal(root.get("usernameID"), s.getUsernameID());
		cq.where(p1);
		if (em.createQuery(cq).getResultList().size() == 1) {
			p.setScore(s.getScore());
			em.merge(p);
		}
	}

	@Override
	public double getGrade(String usernameID) {
		TypedQuery<Profile> query = em.createQuery("SELECT g FROM Profile g WHERE g.usernameID = :usernameID ", Profile.class);
		query.setParameter("usernameID", usernameID);
		List<Profile> results = query.getResultList();

		return results.get(0).getScore();
	}
	@Override
	public List<Profile> getBestCooker() {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Profile> criteria = builder.createQuery(Profile.class);
		criteria.from(Profile.class);
		List<Profile> profiles = em.createQuery(criteria).getResultList();
		HashMap<Profile, Double> bestProfiles = new HashMap<Profile, Double>();
		for(Profile profile : profiles) {
			if (bestProfiles.size() < 6) {	// return the 6 best Profiles
				bestProfiles.put(profile, this.getGrade(profile.getUsernameID()));
			}
			else {
				if (this.getGrade(profile.getUsernameID()) > Collections.min(bestProfiles.values())) {
					for(Profile r : bestProfiles.keySet()) {
						if (this.getGrade(r.getUsernameID()) == Collections.min(bestProfiles.values())) {
							bestProfiles.remove(r, this.getGrade(r.getUsernameID()));
							bestProfiles.put(profile, this.getGrade(profile.getUsernameID()));
							break;
						}
					}
				}
			}
		}
		List<Profile> results = new ArrayList<Profile>();
		for(Profile r : bestProfiles.keySet()) {
			results.add(r);
		}
		
		Comparator<Profile> compareById = new Comparator<Profile>() {
		    @Override
		    public int compare(Profile p1, Profile p2) {
		    	return Double.compare(p1.getScore(), p2.getScore());
		    }
		};
		
		Collections.sort(results, compareById.reversed());
		
		return results;
	}


}
