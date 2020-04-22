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

import domain.model.Comment;
import domain.model.Grade;
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
	
	@Override
	public Long countComment() {
		CriteriaBuilder qb = em.getCriteriaBuilder();
		CriteriaQuery<Long> cq = qb.createQuery(Long.class);
		cq.select(qb.count(cq.from(Comment.class)));
		return em.createQuery(cq).getSingleResult();
	}
	
	@Override
	public void addComment(Comment comment, Long size, Long nbRecipes) {
		comment.setId(size);
		if (comment.getRecipeId() > nbRecipes) {
			throw new IllegalArgumentException("The recipe id of your comment doesn't exist : " + comment.getRecipeId());
		}
		em.persist(comment);
	}
	
	@Override
	public List<Comment> getAllComments(Long recipeId) {
		TypedQuery<Comment> query = em.createQuery("SELECT c FROM Comment c WHERE c.recipeId = :recipeId ", Comment.class);
		query.setParameter("recipeId", recipeId);
		List<Comment> results = query.getResultList();
		return results;
	}
	
	@Override
	public Long countGrade() {
		CriteriaBuilder qb = em.getCriteriaBuilder();
		CriteriaQuery<Long> cq = qb.createQuery(Long.class);
		cq.select(qb.count(cq.from(Grade.class)));
		return em.createQuery(cq).getSingleResult();
	}
	
	@Override
	public void addGrade(Grade grade, Long size, Long nbRecipes, List<Grade> grades) {
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
		TypedQuery<Grade> query = em.createQuery("SELECT g FROM Grade g WHERE g.userId = :userId ", Grade.class);
		query.setParameter("userId", userId);
		List<Grade> results = query.getResultList();
		return results;
	}
	
	@Override
	public double getGrade(Long recipeId) {
		TypedQuery<Grade> query = em.createQuery("SELECT g FROM Grade g WHERE g.recipeId = :recipeId ", Grade.class);
		query.setParameter("recipeId", recipeId);
		List<Grade> results = query.getResultList();
		double mean = 0;
		if (results.size() != 0) {
			for (Grade g : results) {
				mean += g.getGradeRecipe();
			}
			mean /= results.size();
		}
		mean = mean*100;
		mean = Math.round(mean);
		mean = mean /100;
		return mean;
	}

}
