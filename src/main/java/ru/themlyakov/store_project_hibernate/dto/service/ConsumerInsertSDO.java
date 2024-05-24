package ru.themlyakov.store_project_hibernate.dto.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.themlyakov.store_project_hibernate.entity.Consumer;

@Data
@AllArgsConstructor
public class ConsumerInsertSDO implements ServiceLayerDTO<Consumer> {
    private String middleName;
    private String firstName;
    private String lastName;

    private String address;
    private String contactInfo;

    @Override
    public Consumer toRepositoryLayer() {
        return new Consumer(firstName, lastName, middleName, address, contactInfo);
    }

}
