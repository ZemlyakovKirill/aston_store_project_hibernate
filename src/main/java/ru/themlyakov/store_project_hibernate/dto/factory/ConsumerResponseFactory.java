package ru.themlyakov.store_project_hibernate.dto.factory;

import ru.themlyakov.store_project_hibernate.dto.response.ConsumerResponseDTO;
import ru.themlyakov.store_project_hibernate.entity.Consumer;

public class ConsumerResponseFactory implements ResponseFactory<Consumer> {
    @Override
    public ConsumerResponseDTO getResponseDTO() {
        return new ConsumerResponseDTO();
    }
}
