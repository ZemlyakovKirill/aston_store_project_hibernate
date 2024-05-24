package ru.themlyakov.store_project_hibernate.repository;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityNotFoundException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import ru.themlyakov.store_project_hibernate.dto.response.OrderResponseDTO;
import ru.themlyakov.store_project_hibernate.entity.Consumer;
import ru.themlyakov.store_project_hibernate.entity.Order;
import ru.themlyakov.store_project_hibernate.entity.Product;
import ru.themlyakov.store_project_hibernate.utils.exception.DataManipulationException;

import java.util.List;
import java.util.Optional;

@Repository
public class OrderRepository {
    private SessionFactory sessionFactory;

    public OrderRepository(EntityManagerFactory managerFactory) {
        this.sessionFactory = managerFactory.unwrap(SessionFactory.class);
    }

    public List<OrderResponseDTO> getOrders(Long consumerId) {
        try (Session s = sessionFactory.openSession()) {
            Query<Order> query = s.createQuery("SELECT o FROM Order o WHERE o.consumer.id=:consumerId", Order.class);
            query.setParameter("consumerId", consumerId);
            List<Order> list = query.list();
            if (list != null) {
                return list.stream().map(OrderResponseDTO::from).toList();
            }
            throw new EntityNotFoundException("No orders found");
        }
    }

    public OrderResponseDTO makeOrder(Order order) {
        try (Session s = sessionFactory.openSession()) {
            Query<Consumer> consumerQuery = s.createQuery("SELECT c FROM Consumer c WHERE c.id=:consumerId", Consumer.class);
            consumerQuery.setParameter("consumerId", order.getConsumer().getId());
            Optional<Consumer> consumer = consumerQuery.uniqueResultOptional();
            Query<Product> productQuery = s.createQuery("SELECT p FROM Product p WHERE p.id=:productId", Product.class);
            productQuery.setParameter("productId", order.getProduct().getId());
            Optional<Product> product = productQuery.uniqueResultOptional();
            if (product.isPresent() && consumer.isPresent()) {
                s.beginTransaction();
                order.setProduct(product.get());
                order.setConsumer(consumer.get());
                s.persist(order);
                OrderResponseDTO from = OrderResponseDTO.from(order);
                s.getTransaction().commit();
                return from;
            }
            if (product.isEmpty()) {
                throw new EntityNotFoundException("Product with id: '" + order.getProduct().getId() + "' not found");
            }
            if (consumer.isEmpty()) {
                throw new EntityNotFoundException("Consumer with id: '" + order.getConsumer().getId() + "' not found");
            }
            throw new DataManipulationException("Data manipulation error");
        }
    }
}
