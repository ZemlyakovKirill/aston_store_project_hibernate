package ru.themlyakov.store_project_hibernate.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.themlyakov.store_project_hibernate.entity.Product;
import ru.themlyakov.store_project_hibernate.repository.ProductRepository;

@Service
@Slf4j
public class ProductService extends ServiceSupport<Product> {

    protected ProductService(ProductRepository repository) {
        super(repository);
    }
}
