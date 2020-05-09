package domain.service;

import java.util.ArrayList;
import java.util.Collections;
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
import domain.model.Grade;
import domain.model.Ingredient;
import domain.model.Item;
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
		if (comment.getRecipeId() > nbRecipes) {
			throw new IllegalArgumentException("The recipe id of your comment doesn't exist : " + comment.getRecipeId());
		}
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
	public void addGrade(Grade grade, Long size, Long nbRecipes, List<Grade> grades) {
		log.info("create a grade for a recipe");
		grade.setId(size);
		if ((grade.getRecipeId() > nbRecipes) || (grade.getRecipeId() <= 0))  {
			throw new IllegalArgumentException("The recipe id of your grade doesn't exist : " + grade.getRecipeId());
		}
		for (Grade g : grades) {
			if (g.getRecipeId() == grade.getRecipeId()) {
				throw new IllegalArgumentException("You already give a grade to that recipe : " + grade.getRecipeId());
			}
		}
		em.persist(grade);
	}
	
	@Override
	public List<Grade> getAllGrades(Long userId) {
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
		return results;
	}
	
	@Override
	public double getUserGrade(Long userId) {
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
	public List<Recipe> getRecipesByFridge(ArrayList<Item> items) {
		log.info("retrieve all recipes by Fridge");
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Recipe> criteria = builder.createQuery(Recipe.class);
		criteria.from(Recipe.class);
		return em.createQuery(criteria).getResultList();
	}
	

}
