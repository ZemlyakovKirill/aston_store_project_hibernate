package ru.themlyakov.store_project_hibernate.repository;

import jakarta.persistence.EntityManagerFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import ru.themlyakov.store_project_hibernate.entity.Value;

import java.util.Optional;

@Repository
public class ValueRepository {

    private final SessionFactory sessionFactory;

    public ValueRepository(EntityManagerFactory entityManagerFactory) {
        this.sessionFactory = entityManagerFactory.unwrap(SessionFactory.class);
    }

    public Value insertOrGetValue(String name) {
        try (Session s = sessionFactory.openSession()) {
            if (name == null) {
                Value value = new Value(null);
                value.setId(1L);
                return value;
            }
            Query<Value> query = s.createQuery("SELECT v FROM Value v WHERE v.name=:name", Value.class);
            query.setParameter("name", name);
            Optional<Value> value = query.uniqueResultOptional();
            if (value.isEmpty()) {
                Transaction transaction = s.beginTransaction();
                Value response = new Value(name);
                s.persist(response);
                transaction.commit();
                return response;
            }
            return value.get();
        }
    }
}
