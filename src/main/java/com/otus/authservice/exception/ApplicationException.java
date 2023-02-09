package com.otus.authservice.exception;

import com.otus.authservice.util.enums.ApplicationError;

/**
 * The class implements exception for the application error.
 *
 */
public class ApplicationException extends RuntimeException {

  private static final long serialVersionUID = -6772091593303257397L;

  private Integer code;

  public ApplicationException() {
  }

  public ApplicationException(Integer code, String message) {
    super(message);
    this.code = code;
  }

  public ApplicationException(ApplicationError errorContent) {
   this(errorContent.getErrorCode(), errorContent.getMessage());
  }

  public Integer getCode() {
    return code;
  }

  public void setCode(Integer code) {
    this.code = code;
  }
}
