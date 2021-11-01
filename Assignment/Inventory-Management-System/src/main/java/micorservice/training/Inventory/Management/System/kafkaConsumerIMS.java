package micorservice.training.Inventory.Management.System;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class kafkaConsumerIMS {
	
	@Autowired
	private ProductRepository productRepository;
	
	
	@KafkaListener(topics="PRODUCT_TOPIC",groupId = "group_json",
			containerFactory= "productKafkaListenerFactory")
	public void consumeProduc(@Payload Product msg) {
		//System.out.println("Consumed JSON Message: " + msg.getProduct_name());
		
		Product prod=new Product(msg.getId(),msg.getProduct_name(),msg.getPrice());
		System.out.println(prod);
		productRepository.save(prod);
		
		
	}
	
	
	
	// @KafkaListener(topics = "KAFKA_JSON", groupId = "group_json",
//	          containerFactory = "userKafkaListenerFactory")
	//  public void consumeJson(User user) {
//	     System.out.println("Consumed JSON Message: " + user);
	//}

}
