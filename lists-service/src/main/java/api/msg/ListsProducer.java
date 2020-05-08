package api.msg;

import java.util.HashMap;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.aerogear.kafka.SimpleKafkaProducer;
import org.aerogear.kafka.cdi.annotation.KafkaConfig;
import org.aerogear.kafka.cdi.annotation.Producer;

import domain.service.ListsService;
import lombok.extern.java.Log;


@ApplicationScoped
@KafkaConfig(bootstrapServers = "#{thorntail.kafka-configuration.host}:#{thorntail.kafka-configuration.port}")
@Log
public class ListsProducer {
	
	@Producer
	private SimpleKafkaProducer<String, HashMap<Integer, Double>> producer;

	@Inject
	private ListsService listsService;
	
	public void sendAllFridge(int userID) {
		log.info("Send the current state of the fridge's user to the topic");
		producer.send("fridgeReq", listsService.getAllFridgeRecipe(userID));	
	}
}
