package ru.themlyakov.store_project_hibernate.dto.service;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import ru.themlyakov.store_project_hibernate.entity.Product;

import java.math.BigDecimal;


@Getter
@Setter
@NoArgsConstructor
public class ProductUpdateSDO extends ProductInsertSDO {

    @NotNull
    @Min(1)
    private Long id;

    @Builder(builderMethodName = "childBuilder")
    public ProductUpdateSDO(Long id,String name, BigDecimal cost) {
        super(name, cost);
        this.id = id;
    }

    @Override
    public Product toRepositoryLayer() {
        return Product.builder()
                .id(id)
                .name(getName())
                .cost(getCost())
                .build();
    }

}
