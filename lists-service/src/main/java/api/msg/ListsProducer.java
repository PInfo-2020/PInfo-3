package api.msg;

import java.util.HashMap;

import javax.enterprise.context.ApplicationScoped;

import org.aerogear.kafka.SimpleKafkaProducer;
import org.aerogear.kafka.cdi.annotation.KafkaConfig;
import org.aerogear.kafka.cdi.annotation.Producer;

import lombok.extern.java.Log;


@ApplicationScoped
@KafkaConfig(bootstrapServers = "#{thorntail.kafka-configuration.host}:#{thorntail.kafka-configuration.port}")
@Log
public class ListsProducer {
	
	@Producer
	private SimpleKafkaProducer<String, HashMap<Integer, Double>> producer;
	
	public void sendAllFridge(HashMap<Integer, Double> ingredients) {
		log.info("Send the current state of the fridge's user to the topic");
		producer.send("fridgeReq", ingredients);	
	}
}
