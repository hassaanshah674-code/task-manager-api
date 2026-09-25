package codewithhassan.example.taskmanager;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(TaskNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleTaskNotFound(TaskNotFoundException ex) {
        ErrorResponse error = new ErrorResponse(404, ex.getMessage());
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(error);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValid(MethodArgumentNotValidException ex ){
        String message = ex.getBindingResult()
                .getFieldError()
                .getDefaultMessage();
        ErrorResponse error = new ErrorResponse(400,message);
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(error);
    }
}