package com.project.algamoneyapi.resource;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.project.algamoneyapi.model.Category;
import com.project.algamoneyapi.repository.CategoryRepository;

@RestController
@RequestMapping("/categories")
public class CategoryResource {
	
	@Autowired
	private CategoryRepository repository;
	
	@GetMapping
	public List<Category> list(){
		return repository.findAll(); 
	}
	
	@PostMapping
	public ResponseEntity<Category> create(@RequestBody Category category, HttpServletResponse response) {
		Category categorySalved = repository.save(category);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
			.buildAndExpand(categorySalved.getId()).toUri();
		response.setHeader("Location", uri.toASCIIString());
		
		return ResponseEntity.created(uri).body(categorySalved);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Optional<Category>> findById(@PathVariable Long id) {
		Optional<Category> category = repository.findById(id);
		return category.isPresent() ? ResponseEntity.ok().body(category) : ResponseEntity.notFound().build();
	}
}
