package pl.amilosh.personservice.service;

import java.util.List;

import pl.amilosh.personservice.dto.PersonDto;
import org.springframework.stereotype.Service;

import pl.amilosh.personservice.model.Person;
import pl.amilosh.personservice.repository.PersonRepository;

@Service
public class PersonService {

	private final PersonRepository personRepository;

	public PersonService(PersonRepository personRepository) {
		this.personRepository = personRepository;
	}

	public List<Person> findAll(){
		return personRepository.findAll();
	}

	public String create(PersonDto personsDto) {
		var name = personsDto.getName();
		var person = new Person(name);
		var savedPerson = personRepository.save(person);
		return "Person was saved with id: " + savedPerson.getId();
	}
}
