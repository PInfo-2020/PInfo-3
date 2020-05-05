package domain.service;

import java.util.ArrayList;
import java.util.HashMap;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Default;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import domain.model.ItemCart;
import domain.model.ItemCartID;
import domain.model.ItemFridge;
import domain.model.ItemFridgeID;
import lombok.extern.java.Log;

@Default
@Transactional
@ApplicationScoped
@Log
public class ListsServiceImpl implements ListsService {

    @PersistenceContext(unitName = "ListsPU")
    private EntityManager em;
    
    @Override
	public void createItemCart(ItemCart itemCart) {
    	log.info("Créer un item cart");
		em.persist(itemCart);
	}
    
    @Override
	public void createItemFridge(ItemFridge itemFridge) {
    	log.info("Créer un item fridge");
		em.persist(itemFridge);
	}
    
    @Override
    public HashMap<Integer, Double> getAllFridgeRecipe(int userID){
    	ArrayList<ItemFridge> itemFridgeList = getAllFridge(userID);
    	HashMap<Integer, Double> myHash = new HashMap<Integer, Double>();
    	for (ItemFridge i: itemFridgeList) {
    		myHash.put(i.getIngredientID(), i.getQuantity());
    	}
    	return myHash;
    }


	@Override
	public ArrayList<ItemCart> getAllCart(int userID) {
		log.info("Renvoie la liste des items cart pour un certain user");
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<ItemCart> cq = cb.createQuery(ItemCart.class);
		Root<ItemCart> root = cq.from(ItemCart.class);
		cq.select(root);
		Predicate p1 = cb.equal(root.get("userID"), userID);
		cq.where(p1);
		return (ArrayList<ItemCart>) em.createQuery(cq).getResultList();
	}
	
	@Override
	public ArrayList<ItemFridge> getAllFridge(int userID) {
		log.info("Renvoie la liste des items fridge pour un certain user");
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<ItemFridge> cq = cb.createQuery(ItemFridge.class);
		Root<ItemFridge> root = cq.from(ItemFridge.class);
		cq.select(root);
		Predicate p1 = cb.equal(root.get("userID"), userID);
		cq.where(p1);
		return (ArrayList<ItemFridge>) em.createQuery(cq).getResultList();
	}

	@Override
	public void modIngredientCart(ItemCart itemCart) {
		log.info("Modifie la quantité d'un ingrédient dans le cart de l'utilisateur");
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<ItemCart> cq = cb.createQuery(ItemCart.class);
		Root<ItemCart> root = cq.from(ItemCart.class);
		cq.select(root);
		Predicate p1 = cb.equal(root.get("userID"), itemCart.getUserID());
		Predicate p2 = cb.equal(root.get("ingredientID"), itemCart.getIngredientID());
		Predicate pFinal = cb.and(p1,p2);
		cq.where(pFinal);
		if (em.createQuery(cq).getResultList().size() == 1) {
			em.merge(itemCart);
		} else {
			em.persist(itemCart);
		}
		
	}

	@Override
	public void modIngredientFridge(ItemFridge itemFridge) {
		log.info("Modifie la quantité d'un ingrédient dans le fridge de l'utilisateur");
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<ItemFridge> cq = cb.createQuery(ItemFridge.class);
		Root<ItemFridge> root = cq.from(ItemFridge.class);
		cq.select(root);
		Predicate p1 = cb.equal(root.get("userID"), itemFridge.getUserID());
		Predicate p2 = cb.equal(root.get("ingredientID"), itemFridge.getIngredientID());
		Predicate pFinal = cb.and(p1,p2);
		cq.where(pFinal);
		if (em.createQuery(cq).getResultList().size() == 1) {
			em.merge(itemFridge);
		} else {
			em.persist(itemFridge);
		}
	}

	@Override
	public void removeIngredientCart(ItemCart itemCart) {
		log.info("Supprime un ingrédient dans le cart de l'utilisateur");
		ItemCartID pk = new ItemCartID(itemCart.getUserID(), itemCart.getIngredientID());
		itemCart = em.find(ItemCart.class, pk);
		em.remove(itemCart);
	}

	@Override
	public void removeIngredientFridge(ItemFridge itemFridge) {
		log.info("Supprime un ingrédient dans le fridge de l'utilisateur");
		ItemFridgeID pk = new ItemFridgeID(itemFridge.getUserID(), itemFridge.getIngredientID());
		itemFridge = em.find(ItemFridge.class, pk);
		em.remove(itemFridge);
	}
	
//	@Override
//	public ArrayList<ItemCart> getAllCartTEST() {
//		CriteriaBuilder cb = em.getCriteriaBuilder();
//		CriteriaQuery<ItemCart> cq = cb.createQuery(ItemCart.class);
//		Root<ItemCart> root = cq.from(ItemCart.class);
//		cq.select(root);
//		return (ArrayList<ItemCart>) em.createQuery(cq).getResultList();
//	}
}
