package pl.amilosh.consumerservice.kafka;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class KafkaConsumer {

    @KafkaListener(topics = "personTopic", groupId = "person-group")
    public void listen(ConsumerRecord<String, String> record) {
        var key = record.key();
        var value = record.value();
        log.info("Message from producer-service: {} - {}", key, value);
    }
}
