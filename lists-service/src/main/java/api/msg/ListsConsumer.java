package api.msg;


import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.aerogear.kafka.cdi.annotation.Consumer;
import org.aerogear.kafka.cdi.annotation.KafkaConfig;

import lombok.extern.java.Log;

@ApplicationScoped
@KafkaConfig(bootstrapServers = "#{thorntail.kafka-configuration.host}:#{thorntail.kafka-configuration.port}")
@Log
public class ListsConsumer {
	@Inject
	private ListsProducer producer;

	@Consumer(topics = "userReq", groupId = "pinfo-micro-services")
	public void consumeUserID(long userID) {
		log.info("Consumer got following message : " );
		producer.sendAllFridge((int) userID);
	}
}
