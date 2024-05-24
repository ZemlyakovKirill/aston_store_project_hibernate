package ru.themlyakov.store_project_hibernate.dto.service;

import lombok.*;
import ru.themlyakov.store_project_hibernate.entity.Attribute;
import ru.themlyakov.store_project_hibernate.entity.Key;
import ru.themlyakov.store_project_hibernate.entity.Product;
import ru.themlyakov.store_project_hibernate.entity.Value;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Data
public class AttributeUpdateSDO extends AttributeInsertSDO {
    private Long id;

    @Builder(builderMethodName = "childBuilder")
    public AttributeUpdateSDO(Long id, String key, String value, Long parentId, Long productId) {
        super(key, value, parentId, productId);
        this.id = id;
    }

    @Override
    public Attribute toRepositoryLayer() {
        Attribute.AttributeBuilder builder = Attribute.builder();
        builder.key(Key.builder().name(getKey()).build());
        builder.value(Value.builder().name(getValue()).build());
        if (getProductId() != null && getParentId() == null) {
            builder.product(Product.builder().id(getProductId()).build());
        } else if (getParentId() != null && getProductId() == null) {
            builder.parent(Attribute.builder().id(getParentId()).build());
        }
        builder.id(id);
        return builder.build();
    }
}
