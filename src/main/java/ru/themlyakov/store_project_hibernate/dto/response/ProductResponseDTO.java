package ru.themlyakov.store_project_hibernate.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.themlyakov.store_project_hibernate.entity.Product;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponseDTO implements ResponseDTO<Product> {
    private Long id;
    private String name;
    private BigDecimal cost;

    @Override
    public ProductResponseDTO from(Product product) {
        id = product.getId();
        name = product.getName();
        cost = product.getCost();
        return this;
    }
}
