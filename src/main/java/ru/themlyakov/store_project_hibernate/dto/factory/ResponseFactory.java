package ru.themlyakov.store_project_hibernate.dto.factory;

import ru.themlyakov.store_project_hibernate.dto.response.ResponseDTO;
import ru.themlyakov.store_project_hibernate.utils.EntitySupport;

public interface ResponseFactory <T extends EntitySupport<T>>{

    ResponseDTO<T> getResponseDTO();
}
