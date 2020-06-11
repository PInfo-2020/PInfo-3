package domain.service;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Default;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import domain.model.Ingredient;
import lombok.extern.java.Log;

@Default
@Transactional
@ApplicationScoped
@Log
public class IngredientServiceImpl implements IngredientService {
	
	@PersistenceContext(unitName = "IngredientsPU")
	private EntityManager em;
	
	@Override
	public List<Ingredient> getAll() {
		log.info("Get all ingredients");
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Ingredient> criteria = builder.createQuery(Ingredient.class);
		criteria.from(Ingredient.class);
		return em.createQuery(criteria).getResultList();
	}
	
	@Override
	public Ingredient getById(int id) {
		log.info("Get an ingredient by id");
		return em.find(Ingredient.class, id);
	}
	
	@Override
	public Ingredient getByName(String name) {
		log.info("Get unit by ingredient name");
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Ingredient> criteria = builder.createQuery(Ingredient.class);
		Root<Ingredient> i = criteria.from(Ingredient.class);
		criteria.select(i).where(builder.equal(i.get("name"),name));
		return em.createQuery(criteria).getSingleResult();
	}
	
	@Override
	public String getUnitByName(String name) {
		log.info("Get unit by ingredient name");
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Ingredient> criteria = builder.createQuery(Ingredient.class);
		Root<Ingredient> i = criteria.from(Ingredient.class);
		criteria.select(i).where(builder.equal(i.get("name"),name));
		Ingredient ingredient = em.createQuery(criteria).getSingleResult();
		return ingredient.getUnit();
	}
	
	@Override
	public int create(Ingredient ingredient) {
		log.info("Create an ingredient");
		if(em.contains(ingredient)) {
			throw new IllegalArgumentException("Ingredient already exists");
		}
		em.persist(ingredient);
		em.flush();
		return ingredient.getId();
	}
	
	@Override
	public void delete(Ingredient ingredient) {
		log.info("Delete an ingredient");
		em.remove(em.contains(ingredient) ? ingredient : em.merge(ingredient));
	}
	
	@Override
	public boolean existByName(String name) {
		log.info("Check if an ingredient exists");
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Ingredient> criteria = builder.createQuery(Ingredient.class);
		Root<Ingredient> i = criteria.from(Ingredient.class);
		criteria.select(i).where(builder.equal(i.get("name"),name));
		if(em.createQuery(criteria).getResultList().isEmpty()) {
			return false;
		}
		return true;
	}
	
}