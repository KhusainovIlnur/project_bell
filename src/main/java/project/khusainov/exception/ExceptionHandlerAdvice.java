package project.khusainov.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.sql.SQLException;

@RestControllerAdvice
public class ExceptionHandlerAdvice extends ResponseEntityExceptionHandler {

/*    @ExceptionHandler(MyTestException.class)
    public ResponseEntity<ExceptionView> handleMyTestException(MyTestException ex, WebRequest request) {
        return new ResponseEntity<>(new ExceptionView(ex.getMessage(), HttpStatus.NOT_FOUND), HttpStatus.NOT_FOUND);
    }*/

    @ExceptionHandler(MyTestException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionView handleMyTestException(MyTestException ex) {
        return new ExceptionView(ex.getMessage());
    }

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionView handleMyTestException(BadRequestException ex) {
        return new ExceptionView(ex.getMessage());
    }

    @ExceptionHandler(SQLException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ExceptionView handleMyTestException(SQLException ex) {
        return new ExceptionView(ex.getMessage());
    }
}
