package domain.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Default;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.transaction.Transactional;

import domain.model.Comment;
import domain.model.Fridge;
import domain.model.Grade;
import domain.model.Ingredient;
import domain.model.Recipe;
import lombok.extern.java.Log;

@ApplicationScoped
@Transactional
@Default
@Log
public class RecipeServiceImpl implements RecipeService {
	
	@PersistenceContext(unitName = "RecipePU")
	private EntityManager em;
	
	
	@Override
	public Long create(Recipe recipe, Long size) {
		log.info("create a recipe");
		recipe.setId(size);
		em.persist(recipe);
		return recipe.getId();
	}

	
	@Override
	public void createIngredient(Ingredient ingredient, Long recipeId, Long size) {
		log.info("create an ingredient of a recipe");
		ingredient.setId(size);
		ingredient.setRecipeId(recipeId);
		em.persist(ingredient);
	}
	
	@Override
	public List<Recipe> getAll() {
		log.info("retrieve all recipes");
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Recipe> criteria = builder.createQuery(Recipe.class);
		criteria.from(Recipe.class);
		return em.createQuery(criteria).getResultList();
	}
	
	@Override
	public Recipe get(Long recipeId) {
		log.info("retrieve a recipe by its id");
		return em.find(Recipe.class, recipeId);
	}
	
	@Override
	public List<Recipe> getByName(String recipeName) {
		log.info("retrieve all recipes by name given");
		TypedQuery<Recipe> query = em.createQuery("SELECT r FROM Recipe r WHERE r.name LIKE CONCAT('%',:recipeName,'%')", Recipe.class);
		query.setParameter("recipeName", recipeName);
		List<Recipe> results = query.getResultList();
		return results;
	}
	
	@Override
	public Long count() {
		log.info("retrieve the number of recipes");
		CriteriaBuilder qb = em.getCriteriaBuilder();
		CriteriaQuery<Long> cq = qb.createQuery(Long.class);
		cq.select(qb.count(cq.from(Recipe.class)));
		return em.createQuery(cq).getSingleResult();
	}
	
	@Override
	public Long countIngredient() {
		log.info("retrieve the number of ingredients");
		CriteriaBuilder qb = em.getCriteriaBuilder();
		CriteriaQuery<Long> cq = qb.createQuery(Long.class);
		cq.select(qb.count(cq.from(Ingredient.class)));
		return em.createQuery(cq).getSingleResult();
	}
	
	@Override
	public Long countComment() {
		log.info("retrieve the number of comments");
		CriteriaBuilder qb = em.getCriteriaBuilder();
		CriteriaQuery<Long> cq = qb.createQuery(Long.class);
		cq.select(qb.count(cq.from(Comment.class)));
		return em.createQuery(cq).getSingleResult();
	}
	
	@Override
	public void addComment(Comment comment, Long size, Long nbRecipes) {
		log.info("create a comment for a recipe");
		comment.setId(size);
		em.persist(comment);
	}
	
	@Override
	public List<Comment> getAllComments(Long recipeId) {
		log.info("retrieve all comments of a recipe");
		TypedQuery<Comment> query = em.createQuery("SELECT c FROM Comment c WHERE c.recipeId = :recipeId ", Comment.class);
		query.setParameter("recipeId", recipeId);
		List<Comment> results = query.getResultList();
		return results;
	}
	
	@Override
	public Long countGrade() {
		log.info("retrieve the number of grades");
		CriteriaBuilder qb = em.getCriteriaBuilder();
		CriteriaQuery<Long> cq = qb.createQuery(Long.class);
		cq.select(qb.count(cq.from(Grade.class)));
		return em.createQuery(cq).getSingleResult();
	}
	
	@Override
	public void addGrade(Grade grade, Long size, List<Grade> grades) {
		log.info("create a grade for a recipe");
		grade.setId(size);
		int tmp = 1;
		for (Grade g : grades) {
			if ( (int) ((long) g.getRecipeId()) == (int) ((long) grade.getRecipeId())) {
				g.setGradeRecipe(grade.getGradeRecipe());
				em.merge(g);
				tmp = 0;
				break;
			}
		}
		if (tmp == 1) {
			em.persist(grade);
		}
	}
	
	@Override
	public List<Grade> getAllGrades(String userId) {
		log.info("retrieve all grades given by a user");
		TypedQuery<Grade> query = em.createQuery("SELECT g FROM Grade g WHERE g.userId = :userId ", Grade.class);
		query.setParameter("userId", userId);
		List<Grade> results = query.getResultList();
		return results;
	}
	
	@Override
	public double getGrade(Long recipeId) {
		log.info("retrieve the final grade of a recipe");
		TypedQuery<Grade> query = em.createQuery("SELECT g FROM Grade g WHERE g.recipeId = :recipeId ", Grade.class);
		query.setParameter("recipeId", recipeId);
		List<Grade> results = query.getResultList();
		double mean = 0.0;
		if (results.size() != 0) {
			for (Grade g : results) {
				mean += g.getGradeRecipe();
			}
			mean /= results.size();
			
			mean = mean*100;
			mean = Math.round(mean);
			mean = mean /100;
		}
		return mean;
	}
	
	@Override
	public List<Ingredient> getAllIngredients(Long recipeId) {
		log.info("retrieve ingredients of a recipe");
		TypedQuery<Ingredient> query = em.createQuery("SELECT i FROM Ingredient i WHERE i.recipeId = :recipeId ", Ingredient.class);
		query.setParameter("recipeId", recipeId);
		List<Ingredient> results = query.getResultList();
		return results;
	}
	
	@Override
	public List<Recipe> getBestRecipes() {
		log.info("retrieve the best recipes");
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Recipe> criteria = builder.createQuery(Recipe.class);
		criteria.from(Recipe.class);
		List<Recipe> recipes = em.createQuery(criteria).getResultList();
		HashMap<Recipe, Double> bestRecipes = new HashMap<Recipe, Double>();
		for(Recipe recipe : recipes) {
			if (bestRecipes.size() < 3) {	// return the 3 best Recipes
				bestRecipes.put(recipe, this.getGrade(recipe.getId()));
			}
			else {
				if (this.getGrade(recipe.getId()) > Collections.min(bestRecipes.values())) {
					for(Recipe r : bestRecipes.keySet()) {
						if (this.getGrade(r.getId()) == Collections.min(bestRecipes.values())) {
							bestRecipes.remove(r, this.getGrade(r.getId()));
							bestRecipes.put(recipe, this.getGrade(recipe.getId()));
							break;
						}
					}
				}
			}
		}
		List<Recipe> results = new ArrayList<Recipe>();
		for(Recipe r : bestRecipes.keySet()) {
			results.add(r);
		}
		
		Comparator<Recipe> compareById = new Comparator<Recipe>() {
		    @Override
		    public int compare(Recipe r1, Recipe r2) {
		    	return Double.compare(getGrade(r1.getId()), getGrade(r2.getId()));
		    }
		};
		
		Collections.sort(results, compareById.reversed());
		
		return results;
	}
	
	@Override
	public double getUserGrade(String userId) {
		log.info("retrieve the final grade of a user");
		TypedQuery<Recipe> query = em.createQuery("SELECT r FROM Recipe r WHERE r.userId = :userId ", Recipe.class);
		query.setParameter("userId", userId);
		List<Recipe> results = query.getResultList();
		double mean = 0;
		if (results.size() != 0) {
			for (Recipe recipe : results) {
				mean += this.getGrade(recipe.getId());
			}
			mean /= results.size();
			mean = mean*100;
			mean = Math.round(mean);
			mean = mean /100;
		}
		
		return mean;
	}
	
	@Override
	public List<Recipe> getRecipesByFridge(Fridge fridge) {
		log.info("retrieve all recipes that contain ingredients in Fridge");
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Recipe> criteria = builder.createQuery(Recipe.class);
		criteria.from(Recipe.class);
		List<Recipe> allRecipes = em.createQuery(criteria).getResultList();
		List<Recipe> newRecipes = new ArrayList<Recipe>();
		for (Recipe r : allRecipes) {
			TypedQuery<Ingredient> query = em.createQuery("SELECT i FROM Ingredient i WHERE i.recipeId = :recipeId ", Ingredient.class);
			query.setParameter("recipeId", r.getId());
			List<Ingredient> results = query.getResultList();
			ArrayList<Integer> booleans = new ArrayList<Integer>();
			int compteur = 0;
			for(@SuppressWarnings("unused") HashMap.Entry<Integer, Double> entry0 : fridge.getIngredients().entrySet()) {
				booleans.add(0);
			}
			for(HashMap.Entry<Integer, Double> entry : fridge.getIngredients().entrySet()) {
				for (Ingredient j : results) {
					if ((j.getIngredientId() == (long) entry.getKey()) && (j.getQuantite() <= entry.getValue())) {
						booleans.set(compteur, 1);
						break;
					}
				}
				compteur += 1;
			}
			int ok = 1;
			for(Integer b : booleans) {
				if (b == 0) {
					ok = 0;
					break;
				}
			}
			if (ok == 1) {
				newRecipes.add(r);
			}
		}
		
		return newRecipes;
		
	}
	
	@Override
	public List<Recipe> getByVegetarien() {
		log.info("retrieve all recipes that are vegetarien");
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Recipe> criteria = builder.createQuery(Recipe.class);
		criteria.from(Recipe.class);
		List<Recipe> allRecipes = em.createQuery(criteria).getResultList();
		List<Recipe> newRecipes = new ArrayList<Recipe>();
		for (Recipe r : allRecipes) {
			TypedQuery<Ingredient> query = em.createQuery("SELECT i FROM Ingredient i WHERE i.recipeId = :recipeId ", Ingredient.class);
			query.setParameter("recipeId", r.getId());
			List<Ingredient> results = query.getResultList();
			int veg = 1;
			for (Ingredient i : results) {
				if (i.getVegetarien() == 0) {
					veg = 0;
				}
			}
			
			if (veg == 1) {
				newRecipes.add(r);
			}
		
		}
		
		return newRecipes;
		
	}
	
	@Override
	public List<Recipe> getByVegan() {
		log.info("retrieve all recipes that are vegan");
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Recipe> criteria = builder.createQuery(Recipe.class);
		criteria.from(Recipe.class);
		List<Recipe> allRecipes = em.createQuery(criteria).getResultList();
		List<Recipe> newRecipes = new ArrayList<Recipe>();
		for (Recipe r : allRecipes) {
			TypedQuery<Ingredient> query = em.createQuery("SELECT i FROM Ingredient i WHERE i.recipeId = :recipeId ", Ingredient.class);
			query.setParameter("recipeId", r.getId());
			List<Ingredient> results = query.getResultList();
			int veg = 1;
			for (Ingredient i : results) {
				if (i.getVegan() == 0) {
					veg = 0;
				}
			}
			
			if (veg == 1) {
				newRecipes.add(r);
			}
		
		}
		
		return newRecipes;
		
	}
	
	@Override
	public List<Recipe> getCreatedRecipes(String userId) {
		log.info("retrieve all recipes created by a user");
		TypedQuery<Recipe> query = em.createQuery("SELECT r FROM Recipe r WHERE r.userId = :userId ", Recipe.class);
		query.setParameter("userId", userId);
		List<Recipe> results = query.getResultList();
		return results;
	}
	

}
