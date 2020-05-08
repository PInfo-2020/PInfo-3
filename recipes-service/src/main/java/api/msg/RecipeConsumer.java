package api.msg;

import java.util.HashMap;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.aerogear.kafka.cdi.annotation.Consumer;
import org.aerogear.kafka.cdi.annotation.KafkaConfig;

import api.rest.RecipeRestService;
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
	
	@Consumer(topics = "fridgeReq", groupId = "pinfo-micro-services")
	public void consumeIngredients(HashMap<Integer, Double> ingredients) {
		log.info("Consumer got following ingredients : " + ingredients);
		
		rest.setRecipes(impl.getAll());
		rest.setTest(true);
		
	}

}
