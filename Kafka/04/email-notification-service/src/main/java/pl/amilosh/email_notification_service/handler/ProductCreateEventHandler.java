package pl.amilosh.email_notification_service.handler;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import pl.amilosh.email_notification_service.dto.ProductCreatedEvent;

@Component
public class ProductCreateEventHandler {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @KafkaListener(topics = "product-create-events-topic", groupId = "email-service-group", containerFactory = "kafkaListenerContainerFactory")
//    public void handle(ProductCreatedEvent productCreateEvent) {
    public void handle(ConsumerRecord<String, ProductCreatedEvent> record) {
        LOGGER.info("Received event: {}", record);
    }
}
