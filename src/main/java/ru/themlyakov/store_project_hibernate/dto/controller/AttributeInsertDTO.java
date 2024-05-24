package ru.themlyakov.store_project_hibernate.dto.controller;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.themlyakov.store_project_hibernate.dto.service.AttributeInsertSDO;
import ru.themlyakov.store_project_hibernate.dto.service.ServiceLayerDTO;
import ru.themlyakov.store_project_hibernate.entity.Attribute;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AttributeInsertDTO implements ControllerLayerDTO<Attribute> {

    @NotNull
    @NotEmpty
    private String key;

    @NotEmpty
    private String value;

    @Min(1)
    private Long productId;

    @Min(1)
    private Long parentId;

    @AssertTrue(message = "Parent id или product id должно быть установлено")
    private boolean isProductIdOrParentIdIsSet() {
        return productId == null ^ parentId == null;
    }

    @Override
    public ServiceLayerDTO<Attribute> toServiceLay() {
        return AttributeInsertSDO.builder()
                .key(key)
                .parentId(parentId)
                .productId(productId)
                .value(value).build();
    }
}
