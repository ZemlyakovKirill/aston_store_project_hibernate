package ru.themlyakov.store_project_hibernate.dto.service;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.themlyakov.store_project_hibernate.entity.Attribute;
import ru.themlyakov.store_project_hibernate.entity.Key;
import ru.themlyakov.store_project_hibernate.entity.Product;
import ru.themlyakov.store_project_hibernate.entity.Value;


@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class AttributeInsertSDO implements ServiceLayerDTO<Attribute> {
    private String key;
    private String value;
    private Long parentId;
    private Long productId;
    @Override
    public Attribute toRepositoryLayer() {
        Attribute.AttributeBuilder builder = Attribute.builder();
        builder.key(Key.builder().name(key).build());
        builder.value(Value.builder().name(value).build());
        if (productId != null && parentId == null) {
            builder.product(Product.builder().id(productId).build());
        } else if (parentId != null && productId == null) {
            builder.parent(Attribute.builder().id(parentId).build());
        }
        return builder.build();
    }
}
