package ru.themlyakov.store_project_hibernate.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
import ru.themlyakov.store_project_hibernate.utils.exception.DataManipulationException;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
@Slf4j
public class ExceptionController implements ResponseBodyAdvice<Object> {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public List<String> exceptionHandling(MethodArgumentNotValidException e) {
        BindingResult result = e.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();
        return processFieldErrors(fieldErrors);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String exceptionNotReadable(HttpMessageNotReadableException e) {
        return "Cannot read JSON";
    }

    @ExceptionHandler(DataManipulationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String dataManipullationException(DataManipulationException e) {
        return e.getMessage();
    }

    @ExceptionHandler(JsonProcessingException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String jsonParseError(JsonProcessingException e) {
        return "Json processing error";
    }

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String entitiyNotFoundException(EntityNotFoundException e) {
        return e.getMessage();
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(value = HttpStatus.METHOD_NOT_ALLOWED)
    public String methodNotAllowedException(HttpRequestMethodNotSupportedException ex) {
        return "Method '" +
               ex.getMethod() +
               "' not allowed to this path";
    }


    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public String pageNotFoundException(NoHandlerFoundException ex) {
        return "Path '" +
               ex.getRequestURL() +
               "' not found";
    }

    @ExceptionHandler(ServletRequestBindingException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public String requestBindingException(ServletRequestBindingException e) {
        return e.getBody().getDetail();
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public String requestBindingException(MethodArgumentTypeMismatchException e) {
        return "Incompatible types";
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String cvException(ConstraintViolationException e) {
        return "Поле '" + e.getConstraintName()
               + "' " + e.getMessage();
    }

    @ExceptionHandler(Throwable.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String intenralServerError(Throwable thr){
        log.error(thr.getMessage());
        thr.printStackTrace();
        return "Internal server error";
    }

    private List<String> processFieldErrors(List<FieldError> fieldErrors) {
        return fieldErrors.stream().map(e -> "Поле '" + e.getField() + "' " + e.getDefaultMessage()).collect(Collectors.toList());
    }


    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        ServletServerHttpResponse servletResponse = (ServletServerHttpResponse) response;
        int status = servletResponse.getServletResponse().getStatus();
        return new ResponseWrapper(status, body);
    }


}
