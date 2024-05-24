package ru.themlyakov.store_project_hibernate.repository;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.stereotype.Repository;
import ru.themlyakov.store_project_hibernate.dto.response.ProductResponseDTO;
import ru.themlyakov.store_project_hibernate.entity.Product;

@Repository
public class ProductRepository extends RepositorySupport<Product> {

    public ProductRepository(EntityManagerFactory entityManager) {
        super(entityManager, Product.class, new ProductResponseDTO());
    }
}
