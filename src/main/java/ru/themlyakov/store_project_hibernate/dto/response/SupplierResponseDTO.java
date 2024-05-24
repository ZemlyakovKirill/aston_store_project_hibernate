package ru.themlyakov.store_project_hibernate.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.themlyakov.store_project_hibernate.entity.Supplier;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SupplierResponseDTO implements ResponseDTO<Supplier> {
    private Long id;
    private String name;
    private String address;
    private String contactInfo;

    @Override
    public SupplierResponseDTO from(Supplier supplier) {
        return new SupplierResponseDTO(supplier.getId(), supplier.getName(), supplier.getAddress(), supplier.getContact_info());
    }
}
