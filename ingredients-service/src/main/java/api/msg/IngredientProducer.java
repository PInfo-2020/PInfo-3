package api.msg;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.aerogear.kafka.SimpleKafkaProducer;
import org.aerogear.kafka.cdi.annotation.KafkaConfig;
import org.aerogear.kafka.cdi.annotation.Producer;

import domain.model.Ingredient;
import domain.service.IngredientService;
import lombok.extern.java.Log;

@Log
@ApplicationScoped
@KafkaConfig(bootstrapServers="#{thorntail.kafka-configuration.host}:#{thorntail.kafka-configuration.port}")
public class IngredientProducer {
	
	@Producer
	private SimpleKafkaProducer<String,Ingredient> producer;
	
	@Inject
	private IngredientService ingredientService;
	
	public void sendAllUsers(String topic) {
		log.info("Send the current state of ALL users to the topic " + topic);
		for (Ingredient ingredient : ingredientService.getAll()) {
			producer.send(topic, ingredient);
		}
	}

	public void send(Ingredient ingredient, String topic) {
		log.info("Send the state of an user to the topic " + topic + " with id " + ingredient.getId() );
		producer.send(topic, ingredient);			
	}

	public void send(Long id, String topic) {
		log.info("Send the state of an ad to the topic " + topic + " with id " + id);
		Ingredient ingredient = ingredientService.get(id);
		if (ingredient != null) {
			this.send(ingredient, topic);
		}
	}
}