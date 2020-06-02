package br.com.recatalog.service.git.exception;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GitExceptionHandler {
	
	@ExceptionHandler(value = {GitRequestException.class})
	public ResponseEntity<Object> handleGitException(GitRequestException e){
		
		GitException gitException = new GitException(
				e.getMessage(),
				"9999",
				HttpStatus.BAD_REQUEST,
				ZonedDateTime.now(ZoneId.of("Z"))
				);
		
		return new ResponseEntity<>(gitException, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(value = {GitAlreadyExistsException.class})
	public ResponseEntity<Object> handleGitException(GitAlreadyExistsException e){
		
		GitException gitException = new GitException(
				e.getMessage(),
				"9999",
				HttpStatus.BAD_REQUEST,
				ZonedDateTime.now(ZoneId.of("Z"))
				);
		
		return new ResponseEntity<>(gitException, HttpStatus.BAD_REQUEST);
	}	
}