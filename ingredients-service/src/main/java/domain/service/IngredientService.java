package domain.service;

import java.util.List;

import domain.model.Ingredient;

public interface IngredientService {
	
	public List<Ingredient> getAll();
	
	public Ingredient getById(int id);
	
	public Ingredient getByName(String name);
	
	public String getUnitByName(String name);
	
	public int create(Ingredient ingredient);
	
	public void delete(Ingredient ingredient);
	
	public void update(Ingredient ingredient);
	
	public boolean existByName(String name);
	
}