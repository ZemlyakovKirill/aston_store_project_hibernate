package ru.themlyakov.store_project_hibernate.dto.controller;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.themlyakov.store_project_hibernate.dto.service.ServiceLayerDTO;
import ru.themlyakov.store_project_hibernate.dto.service.SupplierInsertSDO;
import ru.themlyakov.store_project_hibernate.entity.Supplier;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SupplierInsertDTO implements ControllerLayerDTO<Supplier> {
    @NotEmpty
    @NotNull
    private String name;

    @NotEmpty
    @NotNull
    private String address;


    @NotEmpty
    @NotNull
    private String contactInfo;

    @Override
    public ServiceLayerDTO<Supplier> toServiceLay() {
        return new SupplierInsertSDO(name, address, contactInfo);
    }
}
