package ru.themlyakov.store_project_hibernate.dto.controller;

import ru.themlyakov.store_project_hibernate.dto.service.ServiceLayerDTO;
import ru.themlyakov.store_project_hibernate.utils.EntitySupport;

public interface ControllerLayerDTO<T extends EntitySupport<T>>{

    ServiceLayerDTO<T> toServiceLay();
}
