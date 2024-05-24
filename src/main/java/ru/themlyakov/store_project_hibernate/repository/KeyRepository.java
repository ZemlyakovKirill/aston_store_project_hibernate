package ru.themlyakov.store_project_hibernate.repository;

import jakarta.persistence.EntityManagerFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import ru.themlyakov.store_project_hibernate.entity.Key;

import java.util.Optional;

@Repository
public class KeyRepository {

    private final SessionFactory sessionFactory;

    public KeyRepository(EntityManagerFactory entityManagerFactory) {
        this.sessionFactory = entityManagerFactory.unwrap(SessionFactory.class);
    }

    public Key insertOrGetKey(String name) {
        try (Session s = sessionFactory.openSession()) {
            Query<Key> keyQuery = s.createQuery("SELECT k FROM Key k WHERE k.name=:name", Key.class);
            keyQuery.setParameter("name", name);
            Optional<Key> key = keyQuery.uniqueResultOptional();
            if (key.isEmpty()) {
                Transaction transaction = s.beginTransaction();
                Key response = Key.builder().name(name).build();
                s.persist(response);
                transaction.commit();
                return response;
            }
            return key.get();
        }
    }
}
