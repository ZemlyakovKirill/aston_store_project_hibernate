package ru.themlyakov.store_project_hibernate.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.themlyakov.store_project_hibernate.entity.Attribute;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Builder
public class AttributeResponseDTO implements ResponseDTO<Attribute> {
    private Long id;
    private String value;
    private String key;

    @Override
    public AttributeResponseDTO from(Attribute attribute) {
        this.id = (Long) attribute.getId();
        this.key = attribute.getKey().getName();
        this.value = attribute.getValue().getName();
        return this;
    }
}
