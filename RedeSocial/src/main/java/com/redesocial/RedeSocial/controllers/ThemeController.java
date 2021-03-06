package com.redesocial.RedeSocial.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.redesocial.RedeSocial.models.ThemeModel;
import com.redesocial.RedeSocial.repositories.ThemeRepository;



@RestController
@RequestMapping("/theme")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ThemeController {
	
	@Autowired
	private ThemeRepository repository;

	@GetMapping
	public ResponseEntity<List<ThemeModel>> getAll() {
		return ResponseEntity.ok(repository.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<ThemeModel> getById(@PathVariable Long id) {
		return repository.findById(id).map(resp -> ResponseEntity.ok(resp)).orElse(ResponseEntity.notFound().build());
	}

	@GetMapping("/name/{name}")
	public ResponseEntity<List<ThemeModel>> getByName(@PathVariable String name) {
		return ResponseEntity.ok(repository.findAllByNameContainingIgnoreCase(name));
	}

	@PostMapping
	public ResponseEntity<ThemeModel> post(@Valid @RequestBody ThemeModel theme) {
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(theme));
	}

	@PutMapping
	public ResponseEntity<ThemeModel> put(@Valid @RequestBody ThemeModel theme) {
		return ResponseEntity.status(HttpStatus.OK).body(repository.save(theme));
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		repository.deleteById(id);

	}
	
	

}
