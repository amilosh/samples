package pl.amilosh.personservice.controller;

import pl.amilosh.personservice.dto.PersonDto;
import pl.amilosh.personservice.model.Person;
import pl.amilosh.personservice.service.PersonService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    public List<Person> getAllPersons() {
        return personService.findAll();
    }

    @PostMapping
    public String createPerson(@RequestBody PersonDto personsDto) {
        return personService.create(personsDto);
    }
}
