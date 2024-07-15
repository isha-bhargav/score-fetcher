package com.game.score_listener.exception;

import com.game.score_listener.utils.Constants;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

public class ExceptionsHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(DbException.class)
    public final ResponseEntity<String> handleDbError(Exception e) {


        return new ResponseEntity<>("Error while saving score to db", HttpStatusCode.valueOf(Constants.REDIS_CONNECTION_FAILURE));
    }
    @ExceptionHandler(CommonException.class)
    public final ResponseEntity<String> handleInternalServerError(Exception e) {


        return new ResponseEntity<>("Error while saving score to db", INTERNAL_SERVER_ERROR);
    }


}
