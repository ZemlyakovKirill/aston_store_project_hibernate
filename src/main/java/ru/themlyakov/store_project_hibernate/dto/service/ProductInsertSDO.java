package ru.themlyakov.store_project_hibernate.dto.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.themlyakov.store_project_hibernate.entity.Product;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class ProductInsertSDO implements ServiceLayerDTO<Product> {
    private String name;
    private BigDecimal cost;


    @Override
    public Product toRepositoryLayer() {
        return new Product(name, cost);
    }


}
