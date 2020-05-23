//package api.msg;
//
//
//import javax.enterprise.context.ApplicationScoped;
//
//import org.aerogear.kafka.SimpleKafkaProducer;
//import org.aerogear.kafka.cdi.annotation.KafkaConfig;
//import org.aerogear.kafka.cdi.annotation.Producer;
//
//import domain.model.Score;
//import lombok.extern.java.Log;
//
//@ApplicationScoped
//@KafkaConfig(bootstrapServers = "#{thorntail.kafka-configuration.host}:#{thorntail.kafka-configuration.port}")
//@Log
//public class RecipeProducer {
//	
//	@Producer
//	private SimpleKafkaProducer<String, String> producer;
//	
//	@Producer
//	private SimpleKafkaProducer<String, Score> producerScore;
//
//	public void send(String userId) {
//		log.info("Send user id " + userId + " to ListsService");
//		producer.send("userReq", userId);
//	}
//	
//	public void sendScore(Score s) {
//		log.info("Send score to ProfileService");
//		producerScore.send("scoreReq", s);
//	}
//
//}
