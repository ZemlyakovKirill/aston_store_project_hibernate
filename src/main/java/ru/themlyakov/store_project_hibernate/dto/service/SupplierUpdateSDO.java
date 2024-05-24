package ru.themlyakov.store_project_hibernate.dto.service;

import lombok.Getter;
import lombok.Setter;
import ru.themlyakov.store_project_hibernate.entity.Supplier;

@Getter
@Setter
public class SupplierUpdateSDO extends SupplierInsertSDO {
    private Long id;


    public SupplierUpdateSDO(Long id, String name, String address, String contactInfo) {
        super(name, address, contactInfo);
        this.id = id;
    }

    @Override
    public Supplier toRepositoryLayer() {
        return new Supplier(id, getName(), getAddress(), getContactInfo());
    }

}
