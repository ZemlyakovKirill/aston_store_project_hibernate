package ru.themlyakov.store_project_hibernate.dto.response;

import ru.themlyakov.store_project_hibernate.utils.EntitySupport;

public interface ResponseDTO<T extends EntitySupport<T>> {

    ResponseDTO<T> from(T t);
}
