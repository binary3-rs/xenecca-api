package com.xenecca.api.utils;

import javax.validation.ConstraintViolationException;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.MethodArgumentNotValidException;

public class ExceptionMessageUtils {

    public static String parseMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        String originalMessage = ex.getMessage();
        String[] messageComponents = originalMessage.split("default message");
        String responseMessage = messageComponents[messageComponents.length - 1];
        return responseMessage.replaceAll("[\\[\\](){}]", "").strip();

    }

    public static String parseConstraintViolationException(ConstraintViolationException ex) {
        StringBuilder builder = new StringBuilder();
        ex.getConstraintViolations().forEach(constraint -> {
            String message = constraint.getMessage();
            if (message.contains("Detail:")) {
                message = message.split("detail: ")[1];
            }
            builder.append(message);
        });
        return builder.toString().strip();
    }

    public static String parseDataIntegrityViolationException(DataIntegrityViolationException ex) {
        Throwable exception = ex.getRootCause();
        String message = exception.getMessage();
        if (message.contains("Detail")) {
            message = message.split("Detail:")[1];
        }
        return message.strip();
    }

}