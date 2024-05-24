package ru.themlyakov.store_project_hibernate.utils;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseWrapper {
    private int status;
    private Object response;
}
