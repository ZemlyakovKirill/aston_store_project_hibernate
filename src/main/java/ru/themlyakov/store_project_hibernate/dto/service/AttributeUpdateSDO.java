package ru.themlyakov.store_project_hibernate.dto.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ru.themlyakov.store_project_hibernate.entity.Attribute;
import ru.themlyakov.store_project_hibernate.entity.Key;
import ru.themlyakov.store_project_hibernate.entity.Product;
import ru.themlyakov.store_project_hibernate.entity.Value;

@Getter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AttributeUpdateSDO extends AttributeInsertSDO {
    private Long id;

    public AttributeUpdateSDO(Long id, String key, String value, Long parentId, Long productId) {
        super(key, value, parentId, productId);
        this.id = id;
    }

    @Override
    public Attribute toRepositoryLayer() {
        Attribute attribute = new Attribute(new Key(getKey()), new Value(getValue()));
        if (getProductId() != null && getParentId() == null) {
            Product product = new Product();
            product.setId(getProductId());
            attribute.setProduct(product);
        } else if (getParentId() != null && getProductId() == null) {
            Attribute parent = new Attribute();
            parent.setId(getParentId());
            attribute.setParent(parent);
        }
        attribute.setId(id);
        return attribute;
    }
}
