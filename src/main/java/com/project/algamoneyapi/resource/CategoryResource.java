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
import com.project.algamoneyapi.model.Category;
import com.project.algamoneyapi.repository.CategoryRepository;

@RestController
@RequestMapping("/categories")
public class CategoryResource {
	
	@Autowired
	private CategoryRepository repository;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@GetMapping
	public List<Category> list(){
		return repository.findAll(); 
	}
	
	@PostMapping
	public ResponseEntity<Category> create(@Valid @RequestBody Category category, HttpServletResponse response) {
		Category categorySalved = repository.save(category);
		this.publisher.publishEvent(new ResourceCreatedEvent(this, response, categorySalved.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(categorySalved);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Optional<Category>> findById(@PathVariable Long id) {
		Optional<Category> category = repository.findById(id);
		return category.isPresent() ? ResponseEntity.ok().body(category) : ResponseEntity.notFound().build();
	}
}
