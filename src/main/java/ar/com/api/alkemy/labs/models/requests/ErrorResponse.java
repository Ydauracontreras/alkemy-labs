package ar.com.api.alkemy.labs.models.requests;

import java.util.ArrayList;
import java.util.List;

import org.springframework.validation.BindingResult;

public class ErrorResponse {

    public String message;
    public List<ErrorResponseItem> errors = new ArrayList<>();

    public static ErrorResponse FromBindingResults(BindingResult results) {
        ErrorResponse err = new ErrorResponse();
        err.message = "Errors occurred when validating the data model";
        results.getFieldErrors().stream().forEach(e -> {

            err.errors.add(new ErrorResponseItem(e.getField(), e.getDefaultMessage()));
        });
        return err;
    }

}
