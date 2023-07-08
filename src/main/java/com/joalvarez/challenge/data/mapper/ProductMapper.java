package com.joalvarez.challenge.data.mapper;

import com.joalvarez.challenge.data.domain.Product;
import com.joalvarez.challenge.data.dto.ProductDTO;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

	public ProductDTO toDTO(Product entity) {
		ProductDTO dto = new ProductDTO();

		dto.setId(entity.getId());
		dto.setName(entity.getName());
		dto.setPrice(entity.getPrice());
		dto.setStock(entity.getStock());
		dto.setActive(entity.isActive());

		return dto;
	}

	public Product toEntity(ProductDTO dto) {
		Product entity = new Product();

		entity.setId(dto.getId());
		entity.setName(dto.getName());
		entity.setPrice(dto.getPrice());
		entity.setStock(dto.getStock());
		entity.setActive(dto.isActive());

		return entity;
	}

}
