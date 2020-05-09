package api.msg;


import java.util.HashMap;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.aerogear.kafka.cdi.annotation.Consumer;
import org.aerogear.kafka.cdi.annotation.KafkaConfig;

import domain.model.Item;
import domain.service.ListsService;
import lombok.extern.java.Log;

@ApplicationScoped
@KafkaConfig(bootstrapServers = "#{thorntail.kafka-configuration.host}:#{thorntail.kafka-configuration.port}")
@Log
public class ListsConsumer {
	
	private Object o = new Object();
	
	@Inject
	private ListsProducer listsProducer;
	
	@Inject
	private ListsService listsService;

	@Consumer(topics = "userReq", groupId = "ch.unige")
	public void consumeUserID(Long userId) {
		log.info("Consumer got following user id : " + userId);
		
		int userID = (int) ((long) userId);
		HashMap<Integer, Double> ingredients = listsService.getAllFridgeRecipe(userID);
		
		synchronized(o) {
			for (HashMap.Entry<Integer, Double> entry : ingredients.entrySet()) {
				Item item = new Item((long) entry.getKey(), entry.getValue());
				listsProducer.sendItem(item);
			}
			
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
			@SuppressWarnings("deprecation")
			Boolean bool = new Boolean(true);
		
			listsProducer.sendBoolean(bool);
			
		}
		
	}
	
}
