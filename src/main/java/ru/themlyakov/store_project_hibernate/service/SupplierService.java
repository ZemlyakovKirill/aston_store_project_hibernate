package ru.themlyakov.store_project_hibernate.service;

import org.springframework.stereotype.Service;
import ru.themlyakov.store_project_hibernate.entity.Supplier;
import ru.themlyakov.store_project_hibernate.repository.SupplierRepository;

@Service
public class SupplierService extends ServiceSupport<Supplier> {

    protected SupplierService(SupplierRepository repository) {
        super(repository);
    }
}
