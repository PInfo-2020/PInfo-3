package api.msg;


import javax.enterprise.context.ApplicationScoped;

import org.aerogear.kafka.SimpleKafkaProducer;
import org.aerogear.kafka.cdi.annotation.KafkaConfig;
import org.aerogear.kafka.cdi.annotation.Producer;

import domain.model.Fridge;
import lombok.extern.java.Log;


@ApplicationScoped
@KafkaConfig(bootstrapServers = "#{thorntail.kafka-configuration.host}:#{thorntail.kafka-configuration.port}")
@Log
public class ListsProducer {
	
	
	@Producer
	private SimpleKafkaProducer<String, Fridge> producer;
	
	
	public void sendItem(Fridge fridge) {
		log.info("Send a fridge");
		producer.send("itemReq", fridge);	
	}
}
