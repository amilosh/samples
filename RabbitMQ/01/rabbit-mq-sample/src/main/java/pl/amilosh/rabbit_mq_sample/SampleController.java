package pl.amilosh.rabbit_mq_sample;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleController {

    private final AmqpTemplate amqpTemplate;
    private final RabbitTemplate rabbitTemplate;

    public SampleController(AmqpTemplate amqpTemplate, RabbitTemplate rabbitTemplate) {
        this.amqpTemplate = amqpTemplate;
        this.rabbitTemplate = rabbitTemplate;
    }

    @PostMapping("/send")
    public String send(@RequestBody String message) {
        amqpTemplate.convertAndSend("myQueue", message); // uses default exchange
        return "ok";
    }

    @PostMapping("/send2")
    public String send2(@RequestBody String message) {
        rabbitTemplate.setExchange("common-exchange");
        rabbitTemplate.convertAndSend(message);
        return "ok";
    }

    @PostMapping("/send-direct")
    public String sendDirect(@RequestBody MessageDto dto) {
        rabbitTemplate.setExchange("direct-exchange");
        rabbitTemplate.convertAndSend(dto.getKey(), dto.getMessage());
        return "ok";
    }
}
