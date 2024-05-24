package ru.themlyakov.store_project_hibernate.dto.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.themlyakov.store_project_hibernate.entity.Attribute;
import ru.themlyakov.store_project_hibernate.entity.Key;
import ru.themlyakov.store_project_hibernate.entity.Product;
import ru.themlyakov.store_project_hibernate.entity.Value;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class AttributeInsertSDO implements ServiceLayerDTO<Attribute> {
    private String key;
    private String value;
    private Long parentId;
    private Long productId;

    @Override
    public Attribute toRepositoryLayer() {
        Attribute attribute = new Attribute(new Key(key), new Value(value));
        if (productId != null && parentId == null) {
            Product product = new Product();
            product.setId(productId);
            attribute.setProduct(product);
        } else if (parentId != null && productId == null) {
            Attribute parent = new Attribute();
            parent.setId(parentId);
            attribute.setParent(parent);
        }
        return attribute;
    }
}
