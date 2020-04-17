package domain.service;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Default;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
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
	public void create(Recipe recipe, Long size) {
		recipe.setId(size);
		em.persist(recipe);
	}
	
	@Override
	public List<Recipe> getAll() {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Recipe> criteria = builder.createQuery(Recipe.class);
		criteria.from(Recipe.class);
		return em.createQuery(criteria).getResultList();
	}
	
	@Override
	public Recipe get(Long recipeId) {
		return em.find(Recipe.class, recipeId);
	}
	
	@Override
	public List<Recipe> getByName(String recipeName) {
		TypedQuery<Recipe> query = em.createQuery("SELECT r FROM Recipe r WHERE r.name LIKE CONCAT('%',:recipeName,'%')", Recipe.class);
		query.setParameter("recipeName", recipeName);
		List<Recipe> results = query.getResultList();
		return results;
	}
	
	@Override
	public Long count() {
		CriteriaBuilder qb = em.getCriteriaBuilder();
		CriteriaQuery<Long> cq = qb.createQuery(Long.class);
		cq.select(qb.count(cq.from(Recipe.class)));
		return em.createQuery(cq).getSingleResult();
	}

}
