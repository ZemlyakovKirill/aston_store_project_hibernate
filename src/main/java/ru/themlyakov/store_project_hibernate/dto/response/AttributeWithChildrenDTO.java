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
        id= (Long) attribute.getId();
        value = attribute.getValue().getName();
        key = attribute.getKey().getName();
        if(attribute.getSubAttributes()!=null) {
            this.subAtttributes = attribute.getSubAttributes().stream().map(this::from).toList();
        }
        return this;
    }
}
