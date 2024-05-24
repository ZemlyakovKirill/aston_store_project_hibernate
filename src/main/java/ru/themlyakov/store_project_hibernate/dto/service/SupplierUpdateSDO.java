package ru.themlyakov.store_project_hibernate.dto.service;

import lombok.*;
import ru.themlyakov.store_project_hibernate.entity.Supplier;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class SupplierUpdateSDO extends SupplierInsertSDO {
    private Long id;

    @Builder(builderMethodName = "childBuilder")
    public SupplierUpdateSDO(Long id, String name, String address, String contactInfo) {
        super(name, address, contactInfo);
        this.id = id;
    }

    @Override
    public Supplier toRepositoryLayer() {
        return Supplier.builder()
                .id(id)
                .name(getName())
                .contactInfo(getContactInfo())
                .address(getAddress())
                .build();
    }

}
