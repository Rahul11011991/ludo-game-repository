package com.ludo.play.exception;

import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class ExceptionHandlerControllerAdvice {

  
  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  public ResponseEntity<ErrorResponses> handleValidationException(
      final MethodArgumentNotValidException exception) {

    log.error("MethodArgumentNotValidException [{}]: {}", HttpStatus.BAD_REQUEST.value(),
        exception.getMessage());

    List<ErrorMessage> errorMessageList = new ArrayList<>();

    List<FieldError> fieldErrorList = exception.getBindingResult().getFieldErrors();

    fieldErrorList.forEach(error -> errorMessageList
        .add(new ErrorMessage(error.getField().toLowerCase(), error.getDefaultMessage())));

    ErrorResponses errorResponses = ErrorResponses.builder().status(HttpStatus.BAD_REQUEST.value())
        .message(HttpStatus.BAD_REQUEST.getReasonPhrase()).errors(errorMessageList).build();

    return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(errorResponses);
  }

  

  @ExceptionHandler({GameException.class})
  @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
  public ResponseEntity<ErrorResponses> handleServiceException(final Exception exception) {

    log.error("FileService Exception [{}]: {}", HttpStatus.INTERNAL_SERVER_ERROR.value(),
        exception.getMessage());

    ErrorResponses errorResponses =
        ErrorResponses.builder().status(HttpStatus.INTERNAL_SERVER_ERROR.value())
            .message(
                HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase() + " - " + exception.getMessage())
            .build();

    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponses);
  }

  
}
