package ru.themlyakov.store_project_hibernate.service;

import org.springframework.stereotype.Service;
import ru.themlyakov.store_project_hibernate.dto.controller.OrderRequestDTO;
import ru.themlyakov.store_project_hibernate.dto.response.OrderResponseDTO;
import ru.themlyakov.store_project_hibernate.entity.Consumer;
import ru.themlyakov.store_project_hibernate.repository.ConsumerRepository;
import ru.themlyakov.store_project_hibernate.repository.OrderRepository;

import java.util.List;

@Service
public class ConsumerService extends ServiceSupport<Consumer> {

    private final OrderRepository orderRepository;

    protected ConsumerService(ConsumerRepository repository, OrderRepository orderRepository) {
        super(repository);
        this.orderRepository = orderRepository;
    }

    public List<OrderResponseDTO> getAllOrders(Long consumerId) {
        return orderRepository.getOrders(consumerId);
    }

    public OrderResponseDTO makeOrder(OrderRequestDTO order) {
        return orderRepository.makeOrder(order.toOrder());
    }
}
