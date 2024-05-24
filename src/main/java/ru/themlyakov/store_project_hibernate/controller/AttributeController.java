package ru.themlyakov.store_project_hibernate.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.themlyakov.store_project_hibernate.dto.controller.AttributeInsertDTO;
import ru.themlyakov.store_project_hibernate.dto.controller.AttributeUpdateDTO;
import ru.themlyakov.store_project_hibernate.dto.response.AttributeResponseDTO;
import ru.themlyakov.store_project_hibernate.dto.response.AttributeWithChildrenDTO;
import ru.themlyakov.store_project_hibernate.service.AttributeService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/attribute")
@Validated
public class AttributeController{

    @Autowired
    private AttributeService attributeService;

    @GetMapping("/all")
    public List<AttributeResponseDTO> selectAllWithoutChildren(){
        return attributeService.selectAll().stream().map(AttributeResponseDTO.class::cast).toList();
    }

    @GetMapping
    public List<AttributeWithChildrenDTO> selectWithChildren(){
        return attributeService.selectAllWithChildren();
    }

    @GetMapping("{id}")
    public AttributeWithChildrenDTO selectById(@PathVariable("id") @Min(1) Long id){
        return attributeService.selectById(id);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public AttributeWithChildrenDTO deleteById(@PathVariable("id") @Min(1) Long id){
        return attributeService.delete(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AttributeResponseDTO insert(@RequestBody @Valid AttributeInsertDTO attribute) {
        return attributeService.insertWithoutChildren(attribute.toServiceLay());
    }

    @PutMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AttributeResponseDTO update(@RequestBody @Valid AttributeUpdateDTO attribute){
        return attributeService.updateWithoutChildren(attribute.toServiceLay());
    }
}
