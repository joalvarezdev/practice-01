package com.joalvarez.challenge.controller;

import com.joalvarez.challenge.data.dto.ProductDTO;
import com.joalvarez.challenge.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("product")
public class ProductController {

	private ProductService service;

	@GetMapping
	public ResponseEntity<List<ProductDTO>> getAll() {
		return ResponseEntity.ok(this.service.getAll());
	}

	@GetMapping("{id}")
	public ResponseEntity<ProductDTO> getById(@PathVariable UUID id) {
		return ResponseEntity.ok(this.service.getById(id));
	}

	@PostMapping
	public ResponseEntity<ProductDTO> create(@Valid @RequestBody ProductDTO dto) {
		return ResponseEntity.ok(this.service.save(dto));
	}

	@PutMapping
	public ResponseEntity<ProductDTO> update(@Valid @RequestBody ProductDTO dto) {
		return ResponseEntity.ok(this.service.update(dto));
	}

	@DeleteMapping("{id}")
	public ResponseEntity<ProductDTO> delete(@PathVariable UUID id) {
		return ResponseEntity.ok(this.service.detele(id));
	}

	@Autowired
	public void setService(ProductService service) {
		this.service = service;
	}
}
