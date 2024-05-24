package ru.themlyakov.store_project_hibernate.dto.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.themlyakov.store_project_hibernate.entity.Supplier;

@Data
@AllArgsConstructor
public class SupplierInsertSDO implements ServiceLayerDTO<Supplier> {
    private String name;
    private String address;
    private String contactInfo;

    @Override
    public Supplier toRepositoryLayer() {
        return new Supplier(name, address, contactInfo);
    }

}
