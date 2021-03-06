package pe.com.rhsistemas.mf.auth.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import pe.com.rhsistemas.mf.auth.model.ErrorMessage;

@ControllerAdvice(annotations = {RestController.class})
public class GlobalExceptionHandler {

    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<?> handleBindingErrors(HttpMessageNotReadableException ex) {
        throw ex;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ErrorMessage>> handleValidationErrors(MethodArgumentNotValidException ex) {
        List<ErrorMessage> errors = new ArrayList<>();

        for (Object object : ex.getBindingResult().getAllErrors()) {
            ErrorMessage e = null;
            if (object instanceof FieldError) {
                FieldError fieldError = (FieldError) object;
                String msg = messageSource.getMessage(fieldError, Locale.getDefault());
                e = new ErrorMessage(fieldError.getField(), msg);
            } else if (object instanceof ObjectError) {
                ObjectError objectError = (ObjectError) object;
                String msg = messageSource.getMessage(objectError, Locale.getDefault());
                e = new ErrorMessage(objectError.getObjectName(), msg);
            }
            errors.add(e);
        }
        System.out.println(errors.size());
        return new ResponseEntity<List<ErrorMessage>>(errors, HttpStatus.BAD_REQUEST);
    }
}
