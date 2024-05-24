package ru.themlyakov.store_project_hibernate.repository;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.stereotype.Repository;
import ru.themlyakov.store_project_hibernate.dto.factory.ConsumerResponseFactory;
import ru.themlyakov.store_project_hibernate.dto.response.ConsumerResponseDTO;
import ru.themlyakov.store_project_hibernate.entity.Consumer;

@Repository
public class ConsumerRepository extends RepositorySupport<Consumer> {
    protected ConsumerRepository(EntityManagerFactory entityManager) {
        super(entityManager, Consumer.class, new ConsumerResponseFactory());
    }
}
