package com.gym.Membership.util;


import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import java.util.List;

public class ErrorsUtil {

    public static String returnErrorsToClient(Errors errors) {
        StringBuilder errorsMsg = new StringBuilder();

        List<FieldError> fieldErrors = errors.getFieldErrors();

        for (FieldError error: fieldErrors) {
            errorsMsg.append(error.getField())
                    .append(" — ").append(error.getDefaultMessage() == null ? error.getCode() : error.getDefaultMessage())
                    .append(";");
        }

        return errorsMsg.toString();
    }
}
