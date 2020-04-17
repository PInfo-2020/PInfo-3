package domain.service;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import domain.model.Ingredient;
import lombok.extern.java.Log;

@ApplicationScoped
@Log
public class IngredientServiceImpl implements IngredientService {
	
	@PersistenceContext(unitName = "IngredientsPU")
	private EntityManager em;
	
	@Override
	public List<Ingredient> getAll() {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Ingredient> criteria = builder.createQuery(Ingredient.class);
		criteria.from(Ingredient.class);
		return (List<Ingredient>) em.createQuery(criteria).getResultList();
	}
	/*
	@Override
	public long create(Ingredient ingredient) {
		if (em.contains(ingredient)) {
			throw new IllegalArgumentException("Ingredient already exists");
		}
		em.persist(ingredient);
		em.flush();
		
		return ingredient.getId();
	}
	
	@Override
	public void delete(Ingredient ingredient) {
		em.remove(em.contains(ingredient) ? ingredient : em.merge(ingredient));
	}

	@Override
	public void update(Ingredient ingredient) {
		if (ingredient == null) {
			throw new IllegalArgumentException("Ingredient does not exist");
		}
		em.merge(ingredient);
	}

	@Override
	public Ingredient get(Long id) {
		return em.find(Ingredient.class, id);
	}
	*/
}