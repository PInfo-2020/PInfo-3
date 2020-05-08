package api.msg;


import javax.enterprise.context.ApplicationScoped;

import org.aerogear.kafka.SimpleKafkaProducer;
import org.aerogear.kafka.cdi.annotation.KafkaConfig;
import org.aerogear.kafka.cdi.annotation.Producer;

import lombok.extern.java.Log;

@ApplicationScoped
@KafkaConfig(bootstrapServers = "#{thorntail.kafka-configuration.host}:#{thorntail.kafka-configuration.port}")
@Log
public class RecipeProducer {
	
	@Producer
	private SimpleKafkaProducer<String, Long> producer;

	public void send(Long userId) {
		log.info("Send user id " + userId + "to ListsService");
		producer.send("userReq", userId);
	}

}
