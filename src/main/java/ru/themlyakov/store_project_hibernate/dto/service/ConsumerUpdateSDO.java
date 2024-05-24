package ru.themlyakov.store_project_hibernate.dto.service;

import lombok.*;
import ru.themlyakov.store_project_hibernate.entity.Consumer;

@Data
@NoArgsConstructor
public class ConsumerUpdateSDO extends ConsumerInsertSDO {
    private Long id;

    @Builder(builderMethodName = "childBuilder")
    public ConsumerUpdateSDO(String middleName, String firstName, String lastName, String address, String contactInfo, Long id) {
        super(middleName, firstName, lastName, address, contactInfo);
        this.id = id;
    }


    @Override
    public Consumer toRepositoryLayer() {
        return Consumer.builder()
                .id(id)
                .firstName(getFirstName())
                .lastName(getLastName())
                .middleName(getMiddleName())
                .address(getAddress())
                .contactInfo(getContactInfo())
                .build();
    }
}
