package ru.themlyakov.store_project_hibernate.utils.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class DataManipulationException extends RuntimeException {
    private String message;

}
