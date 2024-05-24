package ru.themlyakov.store_project_hibernate.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.themlyakov.store_project_hibernate.entity.Attribute;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AttributeWithChildrenDTO implements ResponseDTO<Attribute> {
    private Long id;
    private String value;
    private String key;
    private List<AttributeWithChildrenDTO> subAtttributes =new ArrayList<>();

    public AttributeWithChildrenDTO from(Attribute attribute){
        List<Attribute> subAttributes = attribute.getSubAttributes();
        List<AttributeWithChildrenDTO> dtos = Collections.emptyList();
        if(subAttributes!=null) {
            dtos = subAttributes.stream().map(this::from).toList();
        }
        return new AttributeWithChildrenDTO((Long) attribute.getId(),attribute.getValue().getName(),attribute.getKey().getName(), dtos);
    }
}
