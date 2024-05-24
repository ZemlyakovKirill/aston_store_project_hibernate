package ru.themlyakov.store_project_hibernate.dto.controller;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.themlyakov.store_project_hibernate.dto.service.ConsumerUpdateSDO;
import ru.themlyakov.store_project_hibernate.dto.service.ServiceLayerDTO;
import ru.themlyakov.store_project_hibernate.entity.Consumer;

@Getter
@Setter
@NoArgsConstructor
public class ConsumerUpdateDTO extends ConsumerInsertDTO{
    @Min(1)
    @NotNull
    private Long id;

    @Override
    public ServiceLayerDTO<Consumer> toServiceLay() {
        return ConsumerUpdateSDO.childBuilder()
                .id(id)
                .firstName(getFirstName())
                .lastName(getLastName())
                .middleName(getMiddleName())
                .contactInfo(getContactInfo())
                .address(getAddress()).build();
    }
}
