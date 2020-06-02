package br.com.recatalog.service.git.exception;

import java.time.ZonedDateTime;

import org.springframework.http.HttpStatus;

public class GitException {
	
	private final String  message;
	private final String  code;
	private final HttpStatus httpStatus;
	private final ZonedDateTime timestamp;
	
	public GitException(String message
						, String code
						, HttpStatus httpStatus
						, ZonedDateTime timestamp) {
		this.message = message;
		this.code = code;
		this.httpStatus = httpStatus;
		this.timestamp = timestamp;
	}

	public String getMessage() {
		return message;
	}

	public String getCode() {
		return code;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public ZonedDateTime getTimestamp() {
		return timestamp;
	}
}