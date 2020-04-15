package domain.service;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Default;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.transaction.Transactional;

import domain.model.Recipe;

@ApplicationScoped
@Transactional
@Default
public class RecipeServiceImpl implements RecipeService {
	
	@PersistenceContext(unitName = "RecipePU")
	private EntityManager em;
	
	@Override
	public Long create(Recipe recipe) {
		if (em.contains(recipe)) {
			throw new IllegalArgumentException("Recipe already exists");
		} 
		em.persist(recipe);
		// Sync the transaction to get the newly generated id
		em.flush();
		
		return recipe.getId();
	}
	
	@Override
	public List<Recipe> getAll() {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Recipe> criteria = builder.createQuery(Recipe.class);
		criteria.from(Recipe.class);
		return em.createQuery(criteria).getResultList();
	}

}
