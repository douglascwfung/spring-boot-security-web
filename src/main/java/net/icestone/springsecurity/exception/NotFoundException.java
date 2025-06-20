package net.icestone.springsecurity.exception;

public class NotFoundException extends RuntimeException {

  public NotFoundException() {
    super("Not found");
  }
}
