package api.msg;


import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.aerogear.kafka.cdi.annotation.Consumer;
import org.aerogear.kafka.cdi.annotation.KafkaConfig;

import api.rest.RecipeRestService;
import domain.model.Fridge;
import domain.model.Recipe;
import domain.service.RecipeServiceImpl;
import lombok.extern.java.Log;

@ApplicationScoped
@KafkaConfig(bootstrapServers = "#{thorntail.kafka-configuration.host}:#{thorntail.kafka-configuration.port}")
@Log
public class RecipeConsumer {
	
	@Inject
	RecipeRestService rest;
	
	@Inject
	RecipeServiceImpl impl;
	
	@Consumer(topics = "itemReq", groupId = "ch.unige")
	public void consumeIngredients(Fridge fridge) {
		log.info("Consumer got the fridge : "+ fridge.getIngredients().size());
		
		List<Recipe> recipes = impl.getRecipesByFridge(fridge);
		
		RecipeRestService.setRecipes(recipes);
		RecipeRestService.setTest(1);
			
	}
	

}
