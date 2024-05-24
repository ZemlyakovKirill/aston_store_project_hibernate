package ru.themlyakov.store_project_hibernate.dto.controller;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import ru.themlyakov.store_project_hibernate.dto.service.ProductInsertSDO;
import ru.themlyakov.store_project_hibernate.dto.service.ProductUpdateSDO;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class ProductUpdateDTO extends ProductInsertDTO {
    @NotNull
    @Min(1)
    private Long id;

    @Override
    public ProductInsertSDO toServiceLay() {
        return ProductUpdateSDO.childBuilder()
                .id(id)
                .name(getName())
                .cost(getCost())
                .build();
    }
}
