package ru.themlyakov.store_project_hibernate.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.themlyakov.store_project_hibernate.entity.Attribute;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class AttributeResponseDTO implements ResponseDTO<Attribute> {
    private Long id;
    private String value;
    private String key;

    @Override
    public AttributeResponseDTO from(Attribute attribute) {
        return new AttributeResponseDTO((Long) attribute.getId(), attribute.getValue().getName(), attribute.getKey().getName());
    }
}
