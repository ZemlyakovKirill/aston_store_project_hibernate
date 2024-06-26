package ru.themlyakov.store_project_hibernate.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.themlyakov.store_project_hibernate.entity.Consumer;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class ConsumerResponseDTO implements ResponseDTO<Consumer>{
    private Long id;
    private String firstName;
    private String lastName;
    private String middleName;
    private String address;
    private String contactInfo;

    @Override
    public ConsumerResponseDTO from(Consumer consumer){
        return new ConsumerResponseDTO(consumer.getId(), consumer.getFirstName(), consumer.getLastName(), consumer.getMiddleName(), consumer.getAddress(), consumer.getContactInfo() );
    }
}
