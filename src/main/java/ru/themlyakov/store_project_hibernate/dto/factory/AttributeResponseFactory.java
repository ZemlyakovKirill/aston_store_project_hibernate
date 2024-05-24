package ru.themlyakov.store_project_hibernate.dto.factory;

import ru.themlyakov.store_project_hibernate.dto.response.AttributeResponseDTO;
import ru.themlyakov.store_project_hibernate.dto.response.ResponseDTO;
import ru.themlyakov.store_project_hibernate.entity.Attribute;

public class AttributeResponseFactory implements ResponseFactory<Attribute>{
    @Override
    public AttributeResponseDTO getResponseDTO() {
        return new AttributeResponseDTO();
    }
}
