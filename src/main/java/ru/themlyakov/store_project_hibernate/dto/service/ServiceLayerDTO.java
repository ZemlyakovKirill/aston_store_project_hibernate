package ru.themlyakov.store_project_hibernate.dto.service;

import ru.themlyakov.store_project_hibernate.utils.EntitySupport;

public interface ServiceLayerDTO<T extends EntitySupport<T>> {

    T toRepositoryLayer();
}
