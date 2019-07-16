package com.project.algamoneyapi.resource;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.algamoneyapi.event.ResourceCreatedEvent;
import com.project.algamoneyapi.model.Person;
import com.project.algamoneyapi.repository.PersonRepository;

@RestController
@RequestMapping("/persons")
public class PersonResource {
	
	@Autowired
	private PersonRepository repository;
	
	@Autowired
	private ApplicationEventPublisher publisher;
		
	@GetMapping
	public List<Person> list(){
		return repository.findAll();
	}
	
	@PostMapping
	public ResponseEntity<Person> create(@Valid @RequestBody Person person, HttpServletResponse response) {
		Person personSaved = repository.save(person);
		this.publisher.publishEvent(new ResourceCreatedEvent(this, response, personSaved.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(personSaved);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Optional<Person>> findById(@PathVariable Long id){
		Optional<Person> person = repository.findById(id);
		return person.isPresent() ? ResponseEntity.ok().body(person) : ResponseEntity.notFound().build();
	}
}