package api.msg;


import java.util.ArrayList;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.aerogear.kafka.cdi.annotation.Consumer;
import org.aerogear.kafka.cdi.annotation.KafkaConfig;

import api.rest.RecipeRestService;
import domain.model.Item;
import domain.service.RecipeServiceImpl;
import lombok.extern.java.Log;

@ApplicationScoped
@KafkaConfig(bootstrapServers = "#{thorntail.kafka-configuration.host}:#{thorntail.kafka-configuration.port}")
@Log
public class RecipeConsumer {
	
	ArrayList<Item> items = new ArrayList<Item>();
	
	@Inject
	RecipeRestService rest;
	
	@Inject
	RecipeServiceImpl impl;
	
	@Consumer(topics = "itemReq", groupId = "ch.unige")
	public void consumeIngredients(Item item) {
		log.info("Consumer got following item : " + item.getQuantity());
		items.add(item);
	}
	
	
	@Consumer(topics = "boolReq", groupId = "ch.unige")
	public void consumeCommunication(Boolean bool) {
		log.info("End of the communication");
		
		// Récupérer les recettes
		// ArrayList<Item> vides = new ArrayList<Item>();
		// items = vides;
		
		rest.setRecipes(impl.getAll());
		rest.setTest(true);
	}
	

}
