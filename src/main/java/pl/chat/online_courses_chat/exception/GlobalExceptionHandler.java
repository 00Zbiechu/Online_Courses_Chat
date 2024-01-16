package pl.chat.online_courses_chat.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import pl.chat.online_courses_chat.exception.model.ErrorDTO;
import pl.chat.online_courses_chat.exception.wrapper.ErrorList;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomErrorException.class)
    public String handleCustomErrorException(CustomErrorException ex, Model model) {
        model.addAttribute("errorStatus", ex.getHttpStatus());
        model.addAttribute("field", ex.getField());
        model.addAttribute("errorCode", ex.getErrorCode());
        return "error.html";
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorList> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        List<ErrorDTO> errorList = new ArrayList<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            var fieldName = ((FieldError) error).getField();
            var errorMessage = error.getDefaultMessage();
            errorList.add(ErrorDTO.builder().field(fieldName).errorCodes(errorMessage).build());
        });
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorList.builder().errorList(errorList).build());
    }
}