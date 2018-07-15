package com.amitkumar.springboot.exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "id not found")
public class NotFoundException extends RuntimeException{
	
}
