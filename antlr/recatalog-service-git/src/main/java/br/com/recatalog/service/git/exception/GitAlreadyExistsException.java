package br.com.recatalog.service.git.exception;

public class GitAlreadyExistsException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
    public GitAlreadyExistsException(String message) {
        super(message);
    }
    
    public GitAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}