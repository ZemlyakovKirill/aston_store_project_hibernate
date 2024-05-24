package ru.themlyakov.store_project_hibernate.repository;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.hibernate.query.criteria.HibernateCriteriaBuilder;
import org.springframework.data.repository.NoRepositoryBean;
import ru.themlyakov.store_project_hibernate.dto.factory.ResponseFactory;
import ru.themlyakov.store_project_hibernate.dto.response.ResponseDTO;
import ru.themlyakov.store_project_hibernate.utils.EntitySupport;

import java.util.List;

@NoRepositoryBean
@Slf4j
public abstract class RepositorySupport<T extends EntitySupport<T>> {

    protected SessionFactory sessionFactory;

    protected ResponseDTO<T> defaultResponse;

    private final Class<T> clazz;
    protected RepositorySupport(EntityManagerFactory entityManager, Class<T> tClass, ResponseFactory<T> responseFactory) {
        clazz = tClass;
        sessionFactory = entityManager.unwrap(SessionFactory.class);
        defaultResponse = responseFactory.getResponseDTO();
    }

    public List<ResponseDTO<T>> selectAll() {
        try(Session s = sessionFactory.openSession()){
            HibernateCriteriaBuilder criteriaBuilder = s.getCriteriaBuilder();
            CriteriaQuery<T> cr = criteriaBuilder.createQuery(clazz);
            Root<T> root = cr.from(clazz);
            cr.select(root);
            Query<T> query = s.createQuery(cr);
            return query.getResultList().stream().map(defaultResponse::from).toList();
        }
    }

    public ResponseDTO<T> insert(T entity){
        Transaction transaction = null;
        try (Session s = sessionFactory.openSession()) {
            transaction = s.beginTransaction();
            s.persist(entity);
            transaction.commit();
            return defaultResponse.from(entity);
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
                throw e;
            }
        }
        return defaultResponse.from(entity);
    }

    public ResponseDTO<T> delete(Number id){
        try (Session s = sessionFactory.openSession()) {
            Transaction transaction = s.beginTransaction();
            T entity = s.get(clazz, id);
            if(entity!=null){
                s.remove(entity);
            }else{
                throw new EntityNotFoundException("Entity with id: '" + id + "' not found");
            }
            transaction.commit();
            return defaultResponse.from(entity);
        }
    }

    public ResponseDTO<T> update(T entity){
        try(Session s = sessionFactory.openSession()){
            Transaction transaction = s.beginTransaction();
            T searchedEntity = s.get(clazz,entity.getId());
            if(searchedEntity!=null){
                log.info(String.valueOf(searchedEntity));
                s.merge(entity);
            }else{
                throw new EntityNotFoundException("Entity with id: '"+entity.getId()+"' not found");
            }
            transaction.commit();
            return defaultResponse.from(entity);
        }
    }

    public ResponseDTO<T> selectById(Number id){
        try(Session s = sessionFactory.openSession()){
            T t = s.get(clazz, id);
            if(t!=null) {
                return defaultResponse.from(t);
            }
            throw new EntityNotFoundException("Entity with id: '"+id+"' not found");
        }
    }
}
