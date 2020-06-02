package br.com.recatalog.service.git.exception;

public class GitRequestException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
    public GitRequestException(String message) {
        super(message);
    }
    
    public GitRequestException(String message, Throwable cause) {
        super(message, cause);
    }
}