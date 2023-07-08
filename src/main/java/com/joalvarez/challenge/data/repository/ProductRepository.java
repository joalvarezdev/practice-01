package com.joalvarez.challenge.data.repository;

import com.joalvarez.challenge.data.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {

	Optional<Product> findByNameIgnoreCase(String name);
}
