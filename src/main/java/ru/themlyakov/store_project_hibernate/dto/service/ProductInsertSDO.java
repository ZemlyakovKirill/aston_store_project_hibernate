package ru.themlyakov.store_project_hibernate.dto.service;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.themlyakov.store_project_hibernate.entity.Product;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductInsertSDO implements ServiceLayerDTO<Product> {
    private String name;
    private BigDecimal cost;


    @Override
    public Product toRepositoryLayer() {
        return Product.builder()
                .name(name)
                .cost(cost)
                .build();
    }


}
