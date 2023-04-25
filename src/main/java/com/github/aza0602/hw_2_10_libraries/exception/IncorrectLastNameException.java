package com.github.aza0602.hw_2_10_libraries.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class IncorrectLastNameException extends RuntimeException{
}
