package ru.themlyakov.store_project_hibernate.repository;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.themlyakov.store_project_hibernate.dto.factory.AttributeResponseFactory;
import ru.themlyakov.store_project_hibernate.dto.response.AttributeResponseDTO;
import ru.themlyakov.store_project_hibernate.dto.response.AttributeWithChildrenDTO;
import ru.themlyakov.store_project_hibernate.entity.Attribute;
import ru.themlyakov.store_project_hibernate.entity.Key;
import ru.themlyakov.store_project_hibernate.entity.Value;

import java.util.List;


@Repository
@Slf4j
public class AttributeRepository extends RepositorySupport<Attribute> {

    @Autowired
    private KeyRepository keyRepository;

    @Autowired
    private ValueRepository valueRepository;

    protected AttributeRepository(EntityManagerFactory entityManager) {
        super(entityManager, Attribute.class, new AttributeResponseFactory());
    }

    @Override
    public AttributeWithChildrenDTO delete(Number id) {
        return (AttributeWithChildrenDTO) super.delete(id);
    }

    @Override
    public AttributeWithChildrenDTO selectById(Number id) {
        return (AttributeWithChildrenDTO) super.selectById(id);
    }

    public List<AttributeWithChildrenDTO> selectAllWithChildren() {
        AttributeWithChildrenDTO attributeWithChildrenDTO = new AttributeWithChildrenDTO();
        try (Session s = sessionFactory.openSession()) {
            s.beginTransaction();
            Query<Attribute> query = s.createQuery("SELECT a FROM Attribute a LEFT JOIN FETCH a.subAttributes WHERE a.parent is null", Attribute.class);
            return query.getResultList().stream().map(attributeWithChildrenDTO::from).toList();
        }
    }

    public AttributeResponseDTO insertWithoutChildren(Attribute attribute) {
        try (Session s = sessionFactory.openSession()) {
            Transaction transaction = s.beginTransaction();
            Key key = keyRepository.insertOrGetKey(attribute.getKey().getName());
            Value value = valueRepository.insertOrGetValue(attribute.getValue().getName());
            attribute.setValue(value);
            attribute.setKey(key);
            s.merge(attribute);
            AttributeResponseDTO response = (AttributeResponseDTO) defaultResponse.from(attribute);
            transaction.commit();
            return response;
        }
    }

    public AttributeResponseDTO updateWithoutChildren(Attribute attribute) {
        try (Session s = sessionFactory.openSession()) {
            Transaction transaction = s.beginTransaction();
            Attribute foundAttribute = s.get(Attribute.class, attribute.getId());
            if (foundAttribute != null) {
                Key key = keyRepository.insertOrGetKey(attribute.getKey().getName());
                Value value = valueRepository.insertOrGetValue(attribute.getValue().getName());
                attribute.setValue(value);
                attribute.setKey(key);
                s.merge(attribute);
                AttributeResponseDTO response = (AttributeResponseDTO) defaultResponse.from(attribute);
                transaction.commit();
                return response;
            }
            throw new EntityNotFoundException("Entity with id: '" + attribute.getId() + "' not found");
        }
    }
}
