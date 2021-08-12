package com.xenecca.api.error;

import com.xenecca.api.error.exception.ApiException;
import com.xenecca.api.error.exception.InvalidJwtAuthenticationException;
import com.xenecca.api.error.exception.ResourceNotFoundException;
import com.xenecca.api.utils.ExceptionMessageUtils;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.ElasticsearchException;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@Slf4j
@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    //    @ExceptionHandler(ResourceNotFoundException.class)
//    public final ResponseEntity<ApiErrorResponse> badRequestExceptions(Exception ex, WebRequest request) {
//        return createExceptionResponseEntity(request, ex, HttpStatus.NOT_FOUND);
//    }
//
//    @ExceptionHandler(InvalidJwtAuthenticationException.class)
//    public final ResponseEntity<ApiErrorResponse> invalidJwtAuthenticationException(Exception ex, WebRequest request) {
//        return createExceptionResponseEntity(request, ex, HttpStatus.UNAUTHORIZED);
//    }

//    @ExceptionHandler(InsufficientAuthenticationException.class)
//    public final ResponseEntity<ApiErrorResponse> insufficientAuthenticationException(Exception ex,
//                                                                                      WebRequest request) {
//        ApiErrorResponse apiErrorResponse = new ApiErrorResponse(new Date(), INVALID_JWT_OR_NON_AUTH,
//                request.getDescription(false));
//        return new ResponseEntity<>(apiErrorResponse, HttpStatus.UNAUTHORIZED);
//    }

//    @ExceptionHandler(UsernameNotFoundException.class)
//    public final ResponseEntity<ApiErrorResponse> usernameNotFoundExceptions(Exception ex, WebRequest request) {
//        ApiErrorResponse apiErrorResponse = new ApiErrorResponse(new Date(), ex.getMessage(),
//                request.getDescription(false));
//        return new ResponseEntity<>(apiErrorResponse, HttpStatus.NOT_FOUND);
//    }

//    @ExceptionHandler(BadCredentialsException.class)
//    public final ResponseEntity<ApiErrorResponse> badCredentialsException(Exception ex, WebRequest request) {
//        ApiErrorResponse apiErrorResponse = new ApiErrorResponse(new Date(), ex.getMessage(),
//                request.getDescription(false));
//        return new ResponseEntity<>(apiErrorResponse, HttpStatus.BAD_REQUEST);
//    }
//
//    @ExceptionHandler(NullPointerException.class)
//    public final ResponseEntity<ApiErrorResponse> nullPointerException(Exception ex, WebRequest request) {
//        ApiErrorResponse apiErrorResponse = new ApiErrorResponse(new Date(), NULL_POINTER_EXCEPTION_MESSAGE,
//                request.getDescription(false));
//        return new ResponseEntity<>(apiErrorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
//    }

//    @ExceptionHandler({NoSuchElementException.class, EmptyResultDataAccessException.class})
//    public final ResponseEntity<ApiErrorResponse> noSuchElementException(Exception ex, WebRequest request) {
//        ApiErrorResponse apiErrorResponse = new ApiErrorResponse(new Date(), RESOURCE_NOT_FOUND_MESSAGE,
//                request.getDescription(false));
//        return new ResponseEntity<>(apiErrorResponse, HttpStatus.NOT_FOUND);
//    }

//    @Override
//    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
//                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {
//        String errorMessage = ex.getBindingResult().getFieldErrors().get(0).getDefaultMessage();
//        ApiErrorResponse apiErrorResponse = new ApiErrorResponse(new Date(), errorMessage,
//                request.getDescription(false));
//        return new ResponseEntity<>(apiErrorResponse, HttpStatus.BAD_REQUEST);
//    }

//    @ExceptionHandler(DataIntegrityViolationException.class)
//    public final ResponseEntity<ApiErrorResponse> integrityViolationException(DataIntegrityViolationException ex,
//                                                                              WebRequest request) throws Throwable {
//        Throwable exception = ex.getRootCause();
//        String message = exception.getMessage();
//        if (message.contains("Detail")) {
//            message = message.split("Detail:")[1];
//        }
//        ApiErrorResponse apiErrorResponse = new ApiErrorResponse(new Date(), message, request.getDescription(false));
//        return new ResponseEntity<>(apiErrorResponse, HttpStatus.BAD_REQUEST);
//    }

//    @ExceptionHandler(ConstraintViolationException.class)
//    public final ResponseEntity<ApiErrorResponse> constraintViolationException(ConstraintViolationException ex,
//                                                                               WebRequest request) {
//        StringBuilder builder = new StringBuilder();
//        ex.getConstraintViolations().forEach(constraint -> {
//            String message = constraint.getMessage();
//            if (message.contains("Detail:")) {
//                message = message.split("detail: ")[1];
//            }
//            builder.append(message);
//        });
//        ApiErrorResponse apiErrorResponse = new ApiErrorResponse(new Date(), builder.toString(),
//                request.getDescription(false));
//        return new ResponseEntity<>(apiErrorResponse, HttpStatus.BAD_REQUEST);
//    }
//
//    @ExceptionHandler(ApiException.class)
//    public final ResponseEntity<ApiErrorResponse> handleAPIException(Exception ex, WebRequest request) {
//        ApiErrorResponse apiErrorResponse = new ApiErrorResponse(new Date(), ex.getMessage(),
//                request.getDescription(false));
//        return new ResponseEntity<>(apiErrorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
//    }
//
//    @ExceptionHandler({ElasticsearchException.class})
//    public final ResponseEntity<ApiErrorResponse> handleElasticsearchEException(Exception ex, WebRequest request) {
//        ApiErrorResponse apiErrorResponse = new ApiErrorResponse(new Date(),
//                "There is a problem with the search functionality. Please, try later or contact staff.",
//                request.getDescription(false));
//        return new ResponseEntity<>(apiErrorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
//    }

    @ExceptionHandler({ApiException.class, RuntimeException.class, Exception.class})
    public final ResponseEntity<ApiErrorResponse> handleApiException(Exception ex, WebRequest request) {
        return createExceptionResponseEntity(request, ex, mapExceptionToHttpStatus(ex));
    }

    private ResponseEntity<ApiErrorResponse> createExceptionResponseEntity(WebRequest request, Exception ex, HttpStatus statusCode) {
        String message = createResponseMessage(ex);
        ApiErrorResponse errorResponse = new ApiErrorResponse(Instant.now(), createResponseMessage(ex),
                request.getDescription(false));
        log.error("Handling {} due to:[{}]", ex.getClass().getSimpleName(), message);
        return new ResponseEntity<>(errorResponse, statusCode);
    }

    private String createResponseMessage(Exception ex) {
        if (ex instanceof InsufficientAuthenticationException) {
            return "You're not allowed to access this resource. Your token is invalid or expired.";
        } else if (ex instanceof NullPointerException) {
            return "An error occurred. Check again the request body and try again!";
        } else if (ex instanceof NoSuchElementException || ex instanceof EmptyResultDataAccessException) {
            return "The resource cannot be found. Wrong ID provided!";
        } else if (ex instanceof ElasticsearchException || ex instanceof DataAccessResourceFailureException) {
            return "There is a problem with the search functionality. Please, try later or contact our team.";
        } else if (ex instanceof MethodArgumentNotValidException) {
            return ExceptionMessageUtils.parseMethodArgumentNotValidException((MethodArgumentNotValidException) ex);
        } else if (ex instanceof ConstraintViolationException) {
            return ExceptionMessageUtils.parseConstraintViolationException((ConstraintViolationException) ex);
        } else if (ex instanceof DataIntegrityViolationException) {
            return ExceptionMessageUtils.parseDataIntegrityViolationException((DataIntegrityViolationException) ex);
        } else {
            return ex.getMessage();
        }
    }

    private HttpStatus mapExceptionToHttpStatus(Exception ex) {
        Map<String, HttpStatus> statusMap = new HashMap<String, HttpStatus>() {{
            put(ResourceNotFoundException.class.getSimpleName(), HttpStatus.NOT_FOUND);
            put(NoSuchElementException.class.getSimpleName(), HttpStatus.NOT_FOUND);
            put(EmptyResultDataAccessException.class.getSimpleName(), HttpStatus.NOT_FOUND);
            put(InvalidJwtAuthenticationException.class.getSimpleName(), HttpStatus.UNAUTHORIZED);
            put(InsufficientAuthenticationException.class.getSimpleName(), HttpStatus.UNAUTHORIZED);
            put(BadCredentialsException.class.getSimpleName(), HttpStatus.UNAUTHORIZED);
            put(UsernameNotFoundException.class.getSimpleName(), HttpStatus.UNAUTHORIZED);
            put(NullPointerException.class.getSimpleName(), HttpStatus.INTERNAL_SERVER_ERROR);
            put(MethodArgumentNotValidException.class.getSimpleName(), HttpStatus.BAD_REQUEST);
            put(ConstraintViolationException.class.getSimpleName(), HttpStatus.BAD_REQUEST);
            put(DataIntegrityViolationException.class.getSimpleName(), HttpStatus.BAD_REQUEST);
            put(ElasticsearchException.class.getSimpleName(), HttpStatus.INTERNAL_SERVER_ERROR);
        }};
        return statusMap.getOrDefault(ex.getClass().getSimpleName(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
