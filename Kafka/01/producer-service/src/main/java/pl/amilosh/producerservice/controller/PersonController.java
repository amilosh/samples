package pl.amilosh.producerservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.amilosh.producerservice.event.PersonEvent;

@RestController
@RequestMapping("/person")
@RequiredArgsConstructor
public class PersonController {

    private final KafkaTemplate<String, PersonEvent> kafkaTemplate;

    @GetMapping("/send-message/{username}/{message}")
    public PersonEvent sendMessage(@PathVariable(name = "username") String username,
                                   @PathVariable(name = "message") String message) {
        var personEvent = new PersonEvent(username, message);
        kafkaTemplate.send("personTopic", personEvent);
        return personEvent;
    }
}
