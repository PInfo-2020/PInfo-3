package api.msg;


import java.util.HashMap;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.aerogear.kafka.cdi.annotation.Consumer;
import org.aerogear.kafka.cdi.annotation.KafkaConfig;

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
	public void consumeUserID(Long userId) {
		log.info("Consumer got following user id : " + userId);
		
		int userID = (int) ((long) userId);
		HashMap<Integer, Double> ingredients = listsService.getAllFridgeRecipe(userID);
		listsProducer.sendAllFridge(ingredients);
	}
	
}
