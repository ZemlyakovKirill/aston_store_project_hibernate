package ru.themlyakov.store_project_hibernate.dto.controller;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.themlyakov.store_project_hibernate.dto.service.ProductInsertSDO;
import ru.themlyakov.store_project_hibernate.dto.service.ServiceLayerDTO;
import ru.themlyakov.store_project_hibernate.entity.Product;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductInsertDTO implements ControllerLayerDTO<Product> {
    @NotNull
    @NotEmpty
    private String name;

    @NotNull
    @Min(1)
    private BigDecimal cost;

    @Override
    public ServiceLayerDTO<Product> toServiceLay() {
        return new ProductInsertSDO(name, cost);
    }
}
