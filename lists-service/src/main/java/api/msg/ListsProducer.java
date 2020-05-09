package api.msg;


import javax.enterprise.context.ApplicationScoped;

import org.aerogear.kafka.SimpleKafkaProducer;
import org.aerogear.kafka.cdi.annotation.KafkaConfig;
import org.aerogear.kafka.cdi.annotation.Producer;

import domain.model.Item;
import lombok.extern.java.Log;


@ApplicationScoped
@KafkaConfig(bootstrapServers = "#{thorntail.kafka-configuration.host}:#{thorntail.kafka-configuration.port}")
@Log
public class ListsProducer {
	
	@Producer
	private SimpleKafkaProducer<String, Item> producer;
	
	@Producer
	private SimpleKafkaProducer<String, Boolean> producer2;
	
	public void sendItem(Item item) {
		log.info("Send an item");
		producer.send("itemReq", item);	
	}
	
	public void sendBoolean(Boolean bool) {
		log.info("Send the bool");
		producer2.send("boolReq", bool);	
	}
}
