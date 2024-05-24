package ru.themlyakov.store_project_hibernate.dto.controller;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.themlyakov.store_project_hibernate.dto.service.ConsumerInsertSDO;
import ru.themlyakov.store_project_hibernate.dto.service.ServiceLayerDTO;
import ru.themlyakov.store_project_hibernate.entity.Consumer;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConsumerInsertDTO implements ControllerLayerDTO<Consumer>{

    @NotNull
    @Size(min = 1,max = 100)
    private String firstName;

    @NotNull
    @Size(min = 1,max = 200)
    private String lastName;

    @Size(min = 1,max = 250)
    private String middleName;

    @NotEmpty
    @NotNull
    private String address;

    @NotEmpty
    @NotNull
    private String contactInfo;


    @Override
    public ServiceLayerDTO<Consumer> toServiceLay() {
        return new ConsumerInsertSDO(firstName,lastName,middleName,address,contactInfo);
    }
}
