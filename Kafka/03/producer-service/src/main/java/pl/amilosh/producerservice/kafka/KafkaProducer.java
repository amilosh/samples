package pl.amilosh.producerservice.kafka;

import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaProducer {

//    private final KafkaTemplate<String, String> kafkaTemplate;
//
//    public void sendMessage(String key, String message) {
//        kafkaTemplate.send(new ProducerRecord<String, String>("personTopic", key, message));
//    }
}
