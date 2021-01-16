package br.com.vsmo.forumapi.utils.validation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ValidationErrorHandler {

  @Autowired
  private MessageSource messageSource;

  @ResponseStatus(code = HttpStatus.BAD_REQUEST)
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public List<FormErrorDTO> handle(MethodArgumentNotValidException exception) {

    List<FormErrorDTO> errorDto = new ArrayList<>();

    List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();

    fieldErrors.forEach(e -> {
      String errorMessage = messageSource.getMessage(e, LocaleContextHolder.getLocale());

      FormErrorDTO err = new FormErrorDTO(e.getField(), errorMessage);

      errorDto.add(err);
    });

    return errorDto;
  }

}
