package ru.themlyakov.store_project_hibernate.service;

import ru.themlyakov.store_project_hibernate.dto.response.ResponseDTO;
import ru.themlyakov.store_project_hibernate.dto.service.ServiceLayerDTO;
import ru.themlyakov.store_project_hibernate.repository.RepositorySupport;
import ru.themlyakov.store_project_hibernate.utils.EntitySupport;

import java.util.List;

public abstract class ServiceSupport<T extends EntitySupport<T>> {

    protected RepositorySupport<T> repository;

    protected ServiceSupport(RepositorySupport<T> repository) {
        this.repository = repository;
    }

    public List<ResponseDTO<T>> selectAll() {
        return repository.selectAll();
    }

    public ResponseDTO<T> insert(ServiceLayerDTO<T> t) {
        return repository.insert(t.toRepositoryLayer());
    }

    public ResponseDTO<T> delete(Number id) {
        return repository.delete(id);
    }

    public ResponseDTO<T> update(ServiceLayerDTO<T> t) {
        return repository.update(t.toRepositoryLayer());
    }

    public ResponseDTO<T> selectById(Number id) {
        return repository.selectById(id);
    }
}
