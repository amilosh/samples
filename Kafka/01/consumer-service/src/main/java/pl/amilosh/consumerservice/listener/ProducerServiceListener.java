package pl.amilosh.consumerservice.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import pl.amilosh.consumerservice.event.PersonEvent;

@Slf4j
@Component
public class ProducerServiceListener {

    @KafkaListener(topics = "personTopic")
    public void handleNotification(PersonEvent personEvent) {
        log.info("Message from personTopic, username: {}, message: {}", personEvent.getUsername(), personEvent.getMessage());
    }
}
