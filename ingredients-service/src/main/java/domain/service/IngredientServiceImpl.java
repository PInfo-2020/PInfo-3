package domain.service;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Default;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.transaction.Transactional;

import domain.model.Ingredient;


@ApplicationScoped
@Transactional
@Default
public class IngredientServiceImpl implements IngredientService {
	
	@PersistenceContext(unitName = "UserPU")
	private EntityManager em;	

	@Override
	public long create(Ingredient ingredient) {
		if (em.contains(user)) {
			throw new IllegalArgumentException("user already exists");
		}
		em.persist(user);
		em.flush();
		
		return user.getId();
	}
	
	@Override
	public void delete(Ingredient ingredient) {
		em.remove(em.contains(user) ? user : em.merge(user));
	}

	@Override
	public void update(Ingredient ingredient) {
		if (user == null) {
			throw new IllegalArgumentException("User does not exist");
		}
		em.merge(user);
	}

	@Override
	public User get(Long userId) {
		return em.find(User.class, userId);
	}

	@Override
	public List<Ingredient> getAll() {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<User> criteria = builder.createQuery(User.class);
		criteria.from(User.class);
		return em.createQuery(criteria).getResultList();
	}

}