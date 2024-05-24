package ru.themlyakov.store_project_hibernate.service;

import org.springframework.stereotype.Service;
import ru.themlyakov.store_project_hibernate.dto.response.AttributeResponseDTO;
import ru.themlyakov.store_project_hibernate.dto.response.AttributeWithChildrenDTO;
import ru.themlyakov.store_project_hibernate.dto.service.ServiceLayerDTO;
import ru.themlyakov.store_project_hibernate.entity.Attribute;
import ru.themlyakov.store_project_hibernate.repository.AttributeRepository;

import java.util.List;

@Service
public class AttributeService extends ServiceSupport<Attribute> {
    protected AttributeService(AttributeRepository repository) {
        super(repository);
    }

    public List<AttributeWithChildrenDTO> selectAllWithChildren() {
        return ((AttributeRepository) repository).selectAllWithChildren();
    }

    @Override
    public AttributeWithChildrenDTO selectById(Number id) {
        return ((AttributeRepository) repository).selectById(id);
    }

    @Override
    public AttributeWithChildrenDTO delete(Number id) {
        return ((AttributeRepository) repository).delete(id);
    }

    public AttributeResponseDTO insertWithoutChildren(ServiceLayerDTO<Attribute> t) {
        return ((AttributeRepository) repository).insertWithoutChildren(t.toRepositoryLayer());
    }

    public AttributeResponseDTO updateWithoutChildren(ServiceLayerDTO<Attribute> t) {
        return ((AttributeRepository) repository).updateWithoutChildren(t.toRepositoryLayer());
    }
}
