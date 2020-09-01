package br.com.exemplo.springboot.apiException;

import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ApiErrors {

    private String errors;

    public ApiErrors(String errors) {
        this.errors = errors;
    }

    public String getErrors() {
        return errors;
    }
}
