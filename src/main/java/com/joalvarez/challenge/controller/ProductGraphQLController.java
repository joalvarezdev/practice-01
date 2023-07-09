package com.joalvarez.challenge.controller;

import com.joalvarez.challenge.data.dto.ProductDTO;
import com.joalvarez.challenge.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class ProductGraphQLController {

	private ProductService service;

	@QueryMapping(name = "findProductById")
	public ProductDTO findProductById(@Argument(name = "id") String id) {
		return this.service.findByIdGrapgh(id);
	}

	@QueryMapping(name = "findAllProducts")
	public List<ProductDTO> findProducts() {
		return this.service.getAll();
	}

	@MutationMapping(name = "createProduct")
	public ProductDTO create(@Argument(name = "product") ProductDTO product) {
		return this.service.save(product);
	}

	@Autowired
	public void setService(ProductService service) {
		this.service = service;
	}

}
