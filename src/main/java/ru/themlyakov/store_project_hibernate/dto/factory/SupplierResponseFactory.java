package ru.themlyakov.store_project_hibernate.dto.factory;

import ru.themlyakov.store_project_hibernate.dto.response.SupplierResponseDTO;
import ru.themlyakov.store_project_hibernate.entity.Supplier;

public class SupplierResponseFactory  implements ResponseFactory<Supplier>{
    @Override
    public SupplierResponseDTO getResponseDTO() {
        return new SupplierResponseDTO();
    }
}
