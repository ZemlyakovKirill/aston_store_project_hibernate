package ru.themlyakov.store_project_hibernate.dto.controller;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import ru.themlyakov.store_project_hibernate.entity.Consumer;
import ru.themlyakov.store_project_hibernate.entity.Order;
import ru.themlyakov.store_project_hibernate.entity.Product;

@Data
public class OrderRequestDTO {
    @NotNull
    @Min(1)
    private Long consumer;
    @NotNull
    @Min(1)
    private Long product;

    public Order toOrder(){
        Order order = new Order();
        Consumer consumer1 = new Consumer();
        Product product1=new Product();
        consumer1.setId(consumer);
        product1.setId(product);
        order.setConsumer(consumer1);
        order.setProduct(product1);
        return order;
    }
}
