package api.msg;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.aerogear.kafka.cdi.annotation.Consumer;
import org.aerogear.kafka.cdi.annotation.KafkaConfig;

import domain.model.Score;
import domain.service.ProfileService;
import lombok.extern.java.Log;


@ApplicationScoped
@KafkaConfig(bootstrapServers = "#{thorntail.kafka-configuration.host}:#{thorntail.kafka-configuration.port}")
@Log
public class ProfileConsumer {
	//canal name scoreReq
	
	@Inject
	private ProfileService ps;

	@Consumer(topics = "scoreReq", groupId = "ch.unige")
	public void consumeScore(Score s) {
		log.info("Consumer got score from recipe service");
	
		ps.updateScore(s);
		
		log.info("TEST");
		
	}
	
}
