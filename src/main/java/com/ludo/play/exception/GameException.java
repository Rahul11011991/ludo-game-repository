package com.ludo.play.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
public class GameException extends RuntimeException {

  
  private static final long serialVersionUID = 1L;

  public GameException(String message) {
    super(message);
  }

  public GameException(Exception e, String customMsg, Object... args) {
    super(String.format(customMsg, args), e);
  }

}
