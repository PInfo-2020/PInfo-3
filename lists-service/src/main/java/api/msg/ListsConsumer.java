package api.msg;


import java.util.HashMap;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.aerogear.kafka.cdi.annotation.Consumer;
import org.aerogear.kafka.cdi.annotation.KafkaConfig;

import domain.model.Fridge;
import domain.service.ListsService;
import lombok.extern.java.Log;

@ApplicationScoped
@KafkaConfig(bootstrapServers = "#{thorntail.kafka-configuration.host}:#{thorntail.kafka-configuration.port}")
@Log
public class ListsConsumer {
	
	@Inject
	private ListsProducer listsProducer;
	
	@Inject
	private ListsService listsService;

	@Consumer(topics = "userReq", groupId = "ch.unige")
	public void consumeUserID(String userId) {
		log.info("Consumer got following user id : " + userId);
		
		HashMap<Integer, Double> ingredients = listsService.getAllFridgeRecipe(userId);
		
		Fridge fridge = new Fridge(ingredients);
		listsProducer.sendItem(fridge);
		
	}
	
}
