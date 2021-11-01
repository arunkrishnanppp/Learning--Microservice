package com.example.kafkaconsumer;




import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class KafkaConsumer {

    @KafkaListener(topics = "KAFKA_STRING", groupId = "group_id")
    public void consume(String message) {
        System.out.println("Consumed message: " + message);
    }
    @KafkaListener(topics = "KAFKA_JSON", groupId = "group_json")
    public void consumeJson(String message) throws JsonMappingException, JsonProcessingException {
        System.out.println("Consumed message: " + message);
        ObjectMapper mapper=new ObjectMapper();
        User user=mapper.readValue(message, User.class);
        		System.out.println("User is:"+user);
    }


//  @KafkaListener(topics = "KAFKA_JSON", groupId = "group_json",
//          containerFactory = "userKafkaListenerFactory")
//  public void consumeJson(User user) {
//     System.out.println("Consumed JSON Message: " + user);
//}
}