package ru.themlyakov.store_project_hibernate.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.themlyakov.store_project_hibernate.dto.controller.SupplierInsertDTO;
import ru.themlyakov.store_project_hibernate.dto.controller.SupplierUpdateDTO;
import ru.themlyakov.store_project_hibernate.dto.response.SupplierResponseDTO;
import ru.themlyakov.store_project_hibernate.service.SupplierService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/supplier")
@Validated
public class SupplierController {
    @Autowired
    private SupplierService supplierService;

    @GetMapping
    public List<SupplierResponseDTO> selectAll(){

        return supplierService.selectAll().stream().map(SupplierResponseDTO.class::cast).collect(Collectors.toList());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SupplierResponseDTO insert(@RequestBody @Valid SupplierInsertDTO supplier){
        return (SupplierResponseDTO) supplierService.insert(supplier.toServiceLay());
    }

    @PutMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SupplierResponseDTO update(@RequestBody @Valid SupplierUpdateDTO supplier){
        return (SupplierResponseDTO) supplierService.update(supplier.toServiceLay());
    }

    @DeleteMapping("{id}")
    public SupplierResponseDTO delete(@PathVariable("id") @Min(1) Long id){
        return (SupplierResponseDTO) supplierService.delete(id);
    }

    @GetMapping("{id}")
    public SupplierResponseDTO selectById(@PathVariable("id") @Min(1) Long id){
        return (SupplierResponseDTO) supplierService.selectById(id);
    }
}
