package ru.themlyakov.store_project_hibernate.dto.service;

import lombok.Getter;
import lombok.Setter;
import ru.themlyakov.store_project_hibernate.entity.Consumer;

@Getter
@Setter
public class ConsumerUpdateSDO extends ConsumerInsertSDO {
    private Long id;

    public ConsumerUpdateSDO(Long id, String middleName, String firstName, String lastName, String address, String contactInfo) {
        super(middleName, firstName, lastName, address, contactInfo);
        this.id = id;
    }

    @Override
    public Consumer toRepositoryLayer() {
        return new Consumer(id, getFirstName(), getLastName(), getMiddleName(), getAddress(), getContactInfo());
    }
}
