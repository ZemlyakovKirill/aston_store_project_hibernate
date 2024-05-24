package ru.themlyakov.store_project_hibernate.dto.service;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.themlyakov.store_project_hibernate.entity.Consumer;


@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class ConsumerInsertSDO implements ServiceLayerDTO<Consumer> {
    private String middleName;
    private String firstName;
    private String lastName;

    private String address;
    private String contactInfo;

    @Override
    public Consumer toRepositoryLayer() {
        return Consumer.builder()
                .firstName(firstName)
                .lastName(lastName)
                .middleName(middleName)
                .address(address)
                .contactInfo(contactInfo)
                .build();
    }

}
