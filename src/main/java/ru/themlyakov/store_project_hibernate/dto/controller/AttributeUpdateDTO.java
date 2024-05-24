package ru.themlyakov.store_project_hibernate.dto.controller;

import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ru.themlyakov.store_project_hibernate.dto.service.AttributeUpdateSDO;
import ru.themlyakov.store_project_hibernate.dto.service.ServiceLayerDTO;
import ru.themlyakov.store_project_hibernate.entity.Attribute;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AttributeUpdateDTO extends AttributeInsertDTO{
    @Min(1)
    private Long id;

    @Override
    public ServiceLayerDTO<Attribute> toServiceLay() {
        return new AttributeUpdateSDO(id,getKey(),getValue(),getParentId(),getProductId());
    }
}
