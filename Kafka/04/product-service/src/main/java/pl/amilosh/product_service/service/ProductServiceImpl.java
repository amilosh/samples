package pl.amilosh.product_service.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import pl.amilosh.product_service.service.dto.CreateProductDto;
import pl.amilosh.product_service.service.event.ProductCreatedEvent;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Service
public class ProductServiceImpl implements ProductService{
    private final KafkaTemplate<String, ProductCreatedEvent> kafkaTemplate;
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    public ProductServiceImpl(KafkaTemplate<String, ProductCreatedEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    /* Async */
    @Override
    public String createProduct(CreateProductDto createProductDto) {
        String productId = UUID.randomUUID().toString();
        ProductCreatedEvent event = new ProductCreatedEvent(productId, createProductDto.getTitle(),
                createProductDto.getPrice());
        CompletableFuture<SendResult<String, ProductCreatedEvent>> future = kafkaTemplate
                .send("product-create-events-topic", productId, event);
        future.whenComplete((result, exception) -> {
            if (exception != null) {
                LOGGER.error("Failed to send mesage {}", exception.getMessage());
            } else {
                LOGGER.info("Message was sent successfully {}", result.getRecordMetadata());
            }
        });

        /* Sync - bad practice */
        // future.join();
        LOGGER.info("Return: {}", productId);
        return productId;
    }

    /* Sync */
//    @Override
//    public String createProduct(CreateProductDto createProductDto) throws ExecutionException, InterruptedException {
//        String productId = UUID.randomUUID().toString();
//        ProductCreatedEvent event = new ProductCreatedEvent(productId, createProductDto.getTitle(), createProductDto.getPrice());
//
//        SendResult<String, ProductCreatedEvent> result = kafkaTemplate
//                .send("product-create-events-topic", event)
//                .get();
//        LOGGER.info("Message was sent successfully {}", result.getRecordMetadata());
//
//        LOGGER.info("Return: {}", productId);
//        return productId;
//    }
}
