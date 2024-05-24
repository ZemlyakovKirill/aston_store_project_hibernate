package ru.themlyakov.store_project_hibernate.dto.service;

import lombok.*;
import ru.themlyakov.store_project_hibernate.entity.Supplier;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SupplierInsertSDO implements ServiceLayerDTO<Supplier> {
    private String name;
    private String address;
    private String contactInfo;

    @Override
    public Supplier toRepositoryLayer() {
        return Supplier.builder()
                .name(name)
                .address(address)
                .contactInfo(contactInfo)
                .build();
    }

}
