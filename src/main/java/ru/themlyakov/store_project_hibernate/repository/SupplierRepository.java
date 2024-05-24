package ru.themlyakov.store_project_hibernate.repository;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.stereotype.Repository;
import ru.themlyakov.store_project_hibernate.dto.response.SupplierResponseDTO;
import ru.themlyakov.store_project_hibernate.entity.Supplier;

@Repository
public class SupplierRepository extends RepositorySupport<Supplier> {
    protected SupplierRepository(EntityManagerFactory entityManager) {
        super(entityManager, Supplier.class, new SupplierResponseDTO());
    }
}
