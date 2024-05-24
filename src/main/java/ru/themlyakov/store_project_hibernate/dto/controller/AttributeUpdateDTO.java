package ru.themlyakov.store_project_hibernate.dto.controller;

import jakarta.validation.constraints.Min;
import lombok.*;
import ru.themlyakov.store_project_hibernate.dto.service.AttributeUpdateSDO;
import ru.themlyakov.store_project_hibernate.dto.service.ServiceLayerDTO;
import ru.themlyakov.store_project_hibernate.entity.Attribute;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class AttributeUpdateDTO extends AttributeInsertDTO{
    @Min(1)
    private Long id;

    @Override
    public ServiceLayerDTO<Attribute> toServiceLay() {
        return AttributeUpdateSDO.childBuilder()
                .id(id)
                .key(getKey())
                .value(getValue())
                .parentId(getParentId())
                .productId(getProductId())
                .build();
    }
}
