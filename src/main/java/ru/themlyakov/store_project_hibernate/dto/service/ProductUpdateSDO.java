package ru.themlyakov.store_project_hibernate.dto.service;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import ru.themlyakov.store_project_hibernate.entity.Product;

import java.math.BigDecimal;


@Setter
@Getter
public class ProductUpdateSDO extends ProductInsertSDO {

    @NotNull
    @Min(1)
    private Long id;

    public ProductUpdateSDO(Long id, String name, BigDecimal cost) {
        super(name, cost);
        this.id = id;
    }

    @Override
    public Product toRepositoryLayer() {
        return new Product(id, getName(), getCost());
    }

}
