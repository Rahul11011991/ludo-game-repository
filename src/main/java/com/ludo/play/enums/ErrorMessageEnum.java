package com.ludo.play.enums;
public enum ErrorMessageEnum {

  EXC_EVENT_NOT_FOUND("No events found for the participant %s and game %s ");

  private String errorMessage;

  private ErrorMessageEnum(String errorMessage) {
    this.errorMessage = errorMessage;
  }

  public String getValue() {
    return this.errorMessage;
  }

}
