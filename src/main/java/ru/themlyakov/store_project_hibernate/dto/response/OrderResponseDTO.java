package ru.themlyakov.store_project_hibernate.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.themlyakov.store_project_hibernate.entity.Attribute;
import ru.themlyakov.store_project_hibernate.entity.Order;

import java.math.BigDecimal;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponseDTO {
    private Long orderId;
    private Long productId;
    private String name;
    private BigDecimal cost;
    private Set<AttributeWithChildrenDTO> attributes;

    public static OrderResponseDTO from(Order order) {
        OrderResponseDTO ordersResponseDTO = new OrderResponseDTO();
        AttributeWithChildrenDTO attributeWithChildrenDTO = new AttributeWithChildrenDTO();
        ordersResponseDTO.orderId = order.getId();
        ordersResponseDTO.productId = order.getProduct().getId();
        ordersResponseDTO.name = order.getProduct().getName();
        ordersResponseDTO.cost = order.getProduct().getCost();
        Set<Attribute> attribute = order.getProduct().getAttributes();
        if (attribute != null)
            ordersResponseDTO.attributes = attribute.stream().map(attributeWithChildrenDTO::from).collect(Collectors.toSet());
        return ordersResponseDTO;
    }
}
