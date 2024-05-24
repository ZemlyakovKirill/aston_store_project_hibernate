package ru.themlyakov.store_project_hibernate.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.themlyakov.store_project_hibernate.dto.controller.ProductInsertDTO;
import ru.themlyakov.store_project_hibernate.dto.controller.ProductUpdateDTO;
import ru.themlyakov.store_project_hibernate.dto.response.ProductResponseDTO;
import ru.themlyakov.store_project_hibernate.service.ProductService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/product")
@Validated
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public List<ProductResponseDTO> selectAll(){
        return productService.selectAll()
                .stream()
                .map(ProductResponseDTO.class::cast).collect(Collectors.toList());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductResponseDTO insert(@RequestBody @Valid ProductInsertDTO product){
        return (ProductResponseDTO) productService.insert(product.toServiceLay());
    }

    @PutMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductResponseDTO update(@RequestBody @Valid ProductUpdateDTO product){
        return (ProductResponseDTO) productService.update(product.toServiceLay());
    }

    @DeleteMapping("{id}")
    public ProductResponseDTO delete(@PathVariable("id") @Min(1) Long id){
        return (ProductResponseDTO) productService.delete(id);
    }

    @GetMapping("{id}")
    public ProductResponseDTO selectById(@PathVariable("id") @Min(1) Long id){
        return (ProductResponseDTO) productService.selectById(id);
    }
}
