package domain.service;

import java.util.List;

import domain.model.Ingredient;

public interface IngredientService {
	
	public long create(Ingredient ingredient);
	
	public void delete(Ingredient ingredient);
	
	public void update(Ingredient ingredient);
	
	public Ingredient get(int id);
	
	public List<Ingredient> getAll();
	
}