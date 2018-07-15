package com.amitkumar.springboot.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "name, class, admissionYear shouldn’t be empty or null")
public class BadRequestException extends RuntimeException {

}
