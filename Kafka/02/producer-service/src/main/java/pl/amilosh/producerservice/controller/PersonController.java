package pl.amilosh.producerservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.amilosh.producerservice.event.PersonEvent;
import pl.amilosh.producerservice.kafka.KafkaProducer;

@RestController
@RequestMapping("/person")
@RequiredArgsConstructor
public class PersonController {

    private final ObjectMapper objectMapper;
    private final KafkaProducer kafkaProducer;

    @GetMapping("/send-message/{username}/{message}")
    public PersonEvent sendMessage(@PathVariable(name = "username") String username,
                                   @PathVariable(name = "message") String message) throws JsonProcessingException {
        var personEvent = new PersonEvent(username, message);
        var personEventAsString = objectMapper.writeValueAsString(personEvent);
        kafkaProducer.sendMessage("person", personEventAsString);
        return personEvent;
    }
}
