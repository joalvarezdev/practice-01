package com.joalvarez.challenge.service;

import com.joalvarez.challenge.data.domain.Product;
import com.joalvarez.challenge.data.dto.ProductDTO;
import com.joalvarez.challenge.data.enums.InternalCode;
import com.joalvarez.challenge.data.mapper.ProductMapper;
import com.joalvarez.challenge.data.repository.ProductRepository;
import com.joalvarez.challenge.exception.GenericException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ProductService {

	private ProductRepository repository;
	private ProductMapper mapper;

	public List<ProductDTO> getAll() {
		List<Product> products = this.repository.findAll();

		if (products.isEmpty()) {
			throw new GenericException(
				InternalCode.PRODUCTS_EMPTY,
				"Warn: Error Ocurred in @ProductService.getAll()",
				HttpStatus.IM_USED
			);
		}

		return products.stream()
			.map(this.mapper::toDTO)
			.collect(Collectors.toList());
	}

	public ProductDTO getById(UUID id) {
		return this.repository.findById(id)
			.map(this.mapper::toDTO)
			.orElseThrow(() -> this.idDontExist(id));
	}


	public ProductDTO save(ProductDTO dto) {
		if (this.repository.findByNameIgnoreCase(dto.getName().toLowerCase()).isPresent()) {
			throw new GenericException(
				InternalCode.PRODUCT_ALREADY_EXISTS,
				"Warn: Error Occurred in @ProducService.save(ProductDTO)",
				HttpStatus.BAD_REQUEST,
				List.of(String.format("Product with name {} already exists", dto.getName()))
			);
		}

		return this.mapper.toDTO(this.saveProduct(dto));
	}

	public ProductDTO update(ProductDTO dto) {

		if (Objects.isNull(dto.getId())) {
			throw new GenericException(
				InternalCode.PRODUCT_DONT_EXISTS,
				"Error: Error Occurred in @ProductService.update(ProductDTO)",
				HttpStatus.BAD_REQUEST
			);
		}

		Optional<Product> product = this.repository.findByNameIgnoreCase(dto.getName());

		if (product.isPresent() && !product.get().getId().equals(dto.getId())) {
			throw new GenericException(
				InternalCode.PRODUCT_ALREADY_EXISTS,
				"Warn: Error Occurred in @ProducService.save(ProductDTO)",
				HttpStatus.BAD_REQUEST,
				List.of(String.format("Product with name {} already exists", dto.getName()))
			);
		}

		return this.mapper.toDTO(this.saveProduct(dto));
	}

	public ProductDTO detele(UUID id) {
		ProductDTO dto = this.getById(id);

		dto.setActive(false);

		this.repository.save(this.mapper.toEntity(dto));

		return dto;
	}

	private Product saveProduct(ProductDTO dto) {
		if (Objects.isNull(dto.getId()))  {
			dto.setId(UUID.randomUUID());
		}
		return this.repository.save(this.mapper.toEntity(dto));
	}

	private GenericException idDontExist(UUID id) {
		return new GenericException(
			InternalCode.PRODUCT_DONT_EXISTS,
			"Warn: Error Occurred in @ProducService.getById(UUID)",
			HttpStatus.BAD_REQUEST,
			List.of(String.format("Product with id {} does not exist", id))
		);
	}

	@Autowired
	public void setRepository(ProductRepository repository) {
		this.repository = repository;
	}

	@Autowired
	public void setMapper(ProductMapper mapper) {
		this.mapper = mapper;
	}
}
