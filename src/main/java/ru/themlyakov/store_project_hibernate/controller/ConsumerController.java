package ru.themlyakov.store_project_hibernate.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.themlyakov.store_project_hibernate.dto.controller.ConsumerInsertDTO;
import ru.themlyakov.store_project_hibernate.dto.controller.ConsumerUpdateDTO;
import ru.themlyakov.store_project_hibernate.dto.controller.OrderRequestDTO;
import ru.themlyakov.store_project_hibernate.dto.response.ConsumerResponseDTO;
import ru.themlyakov.store_project_hibernate.dto.response.OrderResponseDTO;
import ru.themlyakov.store_project_hibernate.service.ConsumerService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/consumer")
@Validated
public class ConsumerController {

    @Autowired
    private ConsumerService consumerService;

    @GetMapping
    public List<ConsumerResponseDTO> selectAll(){
        return consumerService.selectAll().stream().map(r-> (ConsumerResponseDTO) r).toList();
    }

    @PostMapping
    public ConsumerResponseDTO insert(@RequestBody @Valid ConsumerInsertDTO consumer){
        return (ConsumerResponseDTO) consumerService.insert(consumer.toServiceLay());
    }

    @PutMapping
    public ConsumerResponseDTO update(@RequestBody @Valid ConsumerUpdateDTO consumer){
        return (ConsumerResponseDTO) consumerService.update(consumer.toServiceLay());
    }

    @DeleteMapping("{id}")
    public ConsumerResponseDTO delete(@PathVariable("id") Long id){
        return (ConsumerResponseDTO) consumerService.delete(id);
    }

    @GetMapping("{id}")
    public ConsumerResponseDTO selectById(@PathVariable("id") @Min(1) Long id){
        return (ConsumerResponseDTO) consumerService.selectById(id);
    }

    @GetMapping("/orders/{id}")
    public List<OrderResponseDTO> getOrders(@PathVariable("id") @Min(1) Long id){
        return consumerService.getAllOrders(id);
    }

    @PostMapping("/orders")
    public OrderResponseDTO makeOrder(@RequestBody @Valid OrderRequestDTO order){
        return consumerService.makeOrder(order);
    }
}
