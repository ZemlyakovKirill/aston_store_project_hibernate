package ru.themlyakov.store_project_hibernate.dto.factory;

import ru.themlyakov.store_project_hibernate.dto.response.ProductResponseDTO;
import ru.themlyakov.store_project_hibernate.entity.Product;

public class ProductResponseFactory implements ResponseFactory<Product>{
    @Override
    public ProductResponseDTO getResponseDTO() {
        return new ProductResponseDTO();
    }
}
