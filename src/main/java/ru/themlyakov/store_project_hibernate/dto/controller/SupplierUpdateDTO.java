package ru.themlyakov.store_project_hibernate.dto.controller;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.themlyakov.store_project_hibernate.dto.service.ServiceLayerDTO;
import ru.themlyakov.store_project_hibernate.dto.service.SupplierInsertSDO;
import ru.themlyakov.store_project_hibernate.dto.service.SupplierUpdateSDO;
import ru.themlyakov.store_project_hibernate.entity.Supplier;

@Getter
@Setter
@NoArgsConstructor
public class SupplierUpdateDTO extends SupplierInsertDTO {

    @NotNull
    @Min(1)
    private Long id;

    @Override
    public ServiceLayerDTO<Supplier> toServiceLay() {
        return new SupplierUpdateSDO(id, getName(), getAddress(), getContactInfo());
    }
}
